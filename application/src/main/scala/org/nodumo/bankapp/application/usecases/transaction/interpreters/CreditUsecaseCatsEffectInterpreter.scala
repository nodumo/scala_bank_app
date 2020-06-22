package org.nodumo.bankapp.application.usecases.transaction.interpreters

import cats.Monad
import cats.effect.IO
import cats.implicits._
import org.bankapp.nodumo.common.customisationother.joda.interpreters.{
  dateTimeGetPreviousDaysService,
  dateTimeGetPreviousWeekService
}
import org.joda.time.{ DateTime, Days, Weeks }
import org.nodumo.bankapp.application.definitions.{ TransactionError, UseCaseAlgebra }
import org.nodumo.bankapp.application.usecases.transaction.dto.{ CreditTransactionCommand, CreditTransactionResponse }
import org.bankapp.nodumo.common.id
import org.nodumo.bankapp.application.usecases.transaction.FactoryCreditTransactionServiceReprAlgebra
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{
  CreditTransaction,
  CustomerId,
  TimeStamp,
  TransactionId
}
import org.nodumo.bankapp.domain.transaction.repository.CreditTransactionRepositoryAlgebra
import org.nodumo.bankapp.application.usecases.transaction.dto.CreditTransactionResponse.{
  failedTransaction,
  succeedTransaction
}
import org.nodumo.bankapp.domain.transaction.service.service.{
  EitherCreditTransactionLimitError,
  EitherDomainValueError
}
import org.nodumo.bankapp.domain.transaction.service.{
  FactoryCreditTransactionServiceAlgebra,
  ValidateTransactionServiceAlgebra
}

/**
 * Credit user account usecase interpreter.
 *
 * " In finance, it's common for accounts to have so-called "velocity limits".
 *   In this task, you'll write a program that accepts or declines attempts to load funds into customers'
 *   accounts in real-time. " - Domain expert
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @param factoryCreditTransactionService Factory service for **Credit-transaction domain-representation**
 * @param factoryCreditTransactionReprService Factory service for **Credit-transaction raw-representation**
 * @param validateTransactionService Validation service the the transaction
 * @param transactionRepository Repository solution for the domain model
 */
class CreditUsecaseCatsEffectInterpreter(
    factoryCreditTransactionService: FactoryCreditTransactionServiceAlgebra[EitherDomainValueError,
                                                                            CreditTransaction.Repr,
                                                                            CreditTransaction],
    factoryCreditTransactionReprService: FactoryCreditTransactionServiceReprAlgebra[CreditTransactionCommand,
                                                                                    CreditTransaction.Repr],
    validateTransactionService: ValidateTransactionServiceAlgebra[EitherCreditTransactionLimitError, CreditTransaction],
    transactionRepository: CreditTransactionRepositoryAlgebra[IO,
                                                              CreditTransaction,
                                                              TransactionId,
                                                              CustomerId,
                                                              TimeStamp,
                                                              DateTime])(implicit monad: Monad[IO])
    extends UseCaseAlgebra[IO, CreditTransactionCommand, CreditTransactionResponse] {
  /* DateTime ->  Previous date (1 day ago) */
  private def previousDay(dateTime: DateTime) =
    dateTimeGetPreviousDaysService.getPreviousDayStart(Days.days(1))(dateTime)

  /* DateTime ->  Previous week (1 week ago) */
  private def previousWeek(dateTime: DateTime) =
    dateTimeGetPreviousWeekService.getPreviousWeeksStart(Weeks.weeks(1))(dateTime)

  /* Raw domain object -> (TransactionError \/ Domain object) */
  private def createCreditTransaction(
      creditTransaction: CreditTransaction.Repr): Either[TransactionError, CreditTransaction] =
    factoryCreditTransactionService
      .create(creditTransaction)
      .bimap(factoryTransactionErrorServiceDomainValueError.create, id)

  /* Raw domain object -> (TransactionError \/ Domain credit transaction) */
  private def validateTransaction(
      creditTransaction: CreditTransaction,
      dailyTransactionFrame: List[CreditTransaction],
      weeklyTransactionFrame: List[CreditTransaction]): Either[TransactionError, CreditTransaction] =
    validateTransactionService
      .validate(creditTransaction, dailyTransactionFrame, weeklyTransactionFrame)
      .bimap(factoryTransactionErrorServiceTransactionError.create, id)

  /* Raw domain object -> (TransactionError \/ Domain credit transaction */
  override def execute(event: CreditTransactionCommand): IO[CreditTransactionResponse] =
    createCreditTransaction(factoryCreditTransactionReprService.create(event)) match {
      // If left then the input data is garbage
      case Left(_) => monad.point(failedTransaction(event.id, event.customer_id))
      // If right then the application data is valid
      case Right(creditTransaction @ CreditTransaction(_, customerId, _, timeStamp)) =>
        for {
          // Repository: Retrieve of the transactions after 1 day ago
          oneDayTransactionsFrame <- transactionRepository.getCreditTransactionsAfterCertainDate(
            customerId,
            previousDay(timeStamp.value))
          // Repository: Retrieve all of the transactions since 1 week ago
          oneWeekTransactionSample <- transactionRepository.getCreditTransactionsAfterCertainDate(
            customerId,
            previousWeek(timeStamp.value))
          response <- validateTransaction(creditTransaction, oneDayTransactionsFrame, oneWeekTransactionSample) match {
            // If Left(error) then we have encountered a validation error return an error
            case Left(_) => monad.pure(failedTransaction(event.id, event.customer_id))
            // If right then create a new record of transaction and then return a response
            case Right(_) =>
              transactionRepository.createCreditTransaction(creditTransaction) >> monad.pure(
                succeedTransaction(event.id, event.customer_id))
          }
        } yield response
    }

}
