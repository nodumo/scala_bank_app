package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransaction
import org.nodumo.bankapp.domain.transaction.service.service.EitherCreditTransactionLimitError
import org.nodumo.bankapp.domain.transaction.service.{
  ValidateDailyCreditAmountLimitServiceAlgebra,
  ValidateDailyTransactionCountLimitServiceAlgebra,
  ValidateTransactionServiceAlgebra,
  ValidateWeeklyAmountLimitServiceAlgebra
}

/**
 * Validator for credit transaction.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @usecase Composing "Domain Value" validators for for "Domain Aggregate" validator
 * @param validateDailyCreditAmountLimitService Validator daily limit amount
 * @param validateDailyTransactionCountLimitService Validate transaction count
 * @param validateWeeklyAmountLimitService Validator weekly limit amount
 */
class ValidateTransactionServiceEitherInterpreter(
    validateDailyCreditAmountLimitService: ValidateDailyCreditAmountLimitServiceAlgebra[
      EitherCreditTransactionLimitError,
      CreditTransaction],
    validateDailyTransactionCountLimitService: ValidateDailyTransactionCountLimitServiceAlgebra[
      EitherCreditTransactionLimitError,
      CreditTransaction],
    validateWeeklyAmountLimitService: ValidateWeeklyAmountLimitServiceAlgebra[EitherCreditTransactionLimitError,
                                                                              CreditTransaction]
) extends ValidateTransactionServiceAlgebra[EitherCreditTransactionLimitError, CreditTransaction] {

  override def validate(
      creditTransaction: CreditTransaction,
      oneDayTransactionsFrame: List[CreditTransaction],
      oneWeekTransactionsFrame: List[CreditTransaction]): EitherCreditTransactionLimitError[CreditTransaction] =
    for {
      _ <- validateDailyCreditAmountLimitService.validate(creditTransaction, oneDayTransactionsFrame)
      _ <- validateDailyTransactionCountLimitService.validate(creditTransaction, oneDayTransactionsFrame)
      _ <- validateWeeklyAmountLimitService.validate(creditTransaction, oneWeekTransactionsFrame)
    } yield creditTransaction

}

object ValidateTransactionServiceEitherInterpreter {

  def apply(
      validateDailyCreditAmountLimitService: ValidateDailyCreditAmountLimitServiceAlgebra[
        EitherCreditTransactionLimitError,
        CreditTransaction],
      validateDailyTransactionCountLimitService: ValidateDailyTransactionCountLimitServiceAlgebra[
        EitherCreditTransactionLimitError,
        CreditTransaction],
      validateWeeklyAmountLimitService: ValidateWeeklyAmountLimitServiceAlgebra[EitherCreditTransactionLimitError,
                                                                                CreditTransaction]
  ): ValidateTransactionServiceEitherInterpreter =
    new ValidateTransactionServiceEitherInterpreter(validateDailyCreditAmountLimitService,
                                                    validateDailyTransactionCountLimitService,
                                                    validateWeeklyAmountLimitService)
}
