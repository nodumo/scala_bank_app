package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransactionLimitError.DailyCreditAmountLimitError
import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransaction
import org.nodumo.bankapp.domain.transaction.service.interpreters.ValidateDailyTransactionCountLimitServiceEitherInterpreter.DAILY_MAXIMUM_TRANSACTION_COUNT
import org.nodumo.bankapp.domain.transaction.service.service.EitherCreditTransactionLimitError
import org.nodumo.bankapp.domain.transaction.service.ValidateDailyTransactionCountLimitServiceAlgebra

class ValidateDailyTransactionCountLimitServiceEitherInterpreter
    extends ValidateDailyTransactionCountLimitServiceAlgebra[EitherCreditTransactionLimitError, CreditTransaction] {

  // Will one more transaction exceed the limit
  private def isTransactionCountSurpassedWithOneMore(oneDayTransactionsFrame: List[CreditTransaction]): Boolean =
    (oneDayTransactionsFrame.length + 1) > DAILY_MAXIMUM_TRANSACTION_COUNT

  override def validate(
      referenceTransaction: CreditTransaction,
      oneDayTransactionsFrame: List[CreditTransaction]): EitherCreditTransactionLimitError[CreditTransaction] =
    if (isTransactionCountSurpassedWithOneMore(oneDayTransactionsFrame))
      Left(DailyCreditAmountLimitError(DAILY_MAXIMUM_TRANSACTION_COUNT))
    else
      Right(referenceTransaction)
}

object ValidateDailyTransactionCountLimitServiceEitherInterpreter {

  private[domain] val DAILY_MAXIMUM_TRANSACTION_COUNT: Int = 3

  def apply: ValidateDailyTransactionCountLimitServiceEitherInterpreter =
    new ValidateDailyTransactionCountLimitServiceEitherInterpreter()

}
