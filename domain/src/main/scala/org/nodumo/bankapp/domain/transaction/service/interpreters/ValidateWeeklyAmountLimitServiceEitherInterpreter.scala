package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.{ CreditTransaction, LoadAmount }
import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransactionLimitError.WeeklyAmountLimitError
import org.nodumo.bankapp.domain.transaction.service.interpreters.ValidateWeeklyAmountLimitServiceEitherInterpreter.WEEKLY_MAXIMUM_CREDIT_AMOUNT
import org.nodumo.bankapp.domain.transaction.service.service.EitherCreditTransactionLimitError
import org.nodumo.bankapp.domain.transaction.service.ValidateWeeklyAmountLimitServiceAlgebra

class ValidateWeeklyAmountLimitServiceEitherInterpreter
    extends ValidateWeeklyAmountLimitServiceAlgebra[EitherCreditTransactionLimitError, CreditTransaction] {

  private def isSumLimitSurpassed(pendingTransaction: CreditTransaction,
                                  oneWeekTransactionsFrame: List[CreditTransaction]): Boolean =
    oneWeekTransactionsFrame
      .map(_.loadAmount.value)
      .sum + pendingTransaction.loadAmount.value > WEEKLY_MAXIMUM_CREDIT_AMOUNT

  override def validate(
      referenceTransaction: CreditTransaction,
      oneWeekTransactionsFrame: List[CreditTransaction]): EitherCreditTransactionLimitError[CreditTransaction] =
    if (isSumLimitSurpassed(referenceTransaction, oneWeekTransactionsFrame))
      Left(WeeklyAmountLimitError(WEEKLY_MAXIMUM_CREDIT_AMOUNT))
    else
      Right(referenceTransaction)

}

object ValidateWeeklyAmountLimitServiceEitherInterpreter {

  // Scoped to this domain do not leak this, however we can expose this value through the error type
  private[transaction] val WEEKLY_MAXIMUM_CREDIT_AMOUNT: LoadAmount.Repr = 2000000 // cents

  def apply: ValidateWeeklyAmountLimitServiceEitherInterpreter =
    new ValidateWeeklyAmountLimitServiceEitherInterpreter()

}
