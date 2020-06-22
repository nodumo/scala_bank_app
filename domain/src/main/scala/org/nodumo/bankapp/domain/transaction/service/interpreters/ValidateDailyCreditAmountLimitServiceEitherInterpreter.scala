package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransactionLimitError.DailyCreditAmountLimitError
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{ CreditTransaction, LoadAmount }
import org.nodumo.bankapp.domain.transaction.service.interpreters.ValidateDailyCreditAmountLimitServiceEitherInterpreter.DAILY_MAXIMUM_CREDIT_AMOUNT
import org.nodumo.bankapp.domain.transaction.service.service.EitherCreditTransactionLimitError
import org.nodumo.bankapp.domain.transaction.service.ValidateDailyCreditAmountLimitServiceAlgebra

/**
 * Validate the day's transaction limit for a given day-frame.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 */
class ValidateDailyCreditAmountLimitServiceEitherInterpreter
    extends ValidateDailyCreditAmountLimitServiceAlgebra[EitherCreditTransactionLimitError, CreditTransaction] {

  /**
   * Checking the sum of the day's transactions plus the pending transaction
   * against the maximum which represents the limit in cents a manner appropriate for storage in the database.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @param pendingTransaction Pending transaction
   * @param oneDayTransactionsFrame One day's worth of transaction
   */
  private def isSumLimitSurpassed(pendingTransaction: CreditTransaction,
                                  oneDayTransactionsFrame: List[CreditTransaction]): Boolean =
    oneDayTransactionsFrame
      .map(_.loadAmount.value)
      .sum + pendingTransaction.loadAmount.value > DAILY_MAXIMUM_CREDIT_AMOUNT

  /**
   * Validate pending transaction.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @param pendingTransaction Transaction
   * @param oneDayTransactionsFrame Day transaction frame
   */
  override def validate(
      pendingTransaction: CreditTransaction,
      oneDayTransactionsFrame: List[CreditTransaction]): EitherCreditTransactionLimitError[CreditTransaction] =
    if (isSumLimitSurpassed(pendingTransaction, oneDayTransactionsFrame))
      Left(DailyCreditAmountLimitError(DAILY_MAXIMUM_CREDIT_AMOUNT))
    else Right(pendingTransaction)

}

object ValidateDailyCreditAmountLimitServiceEitherInterpreter {

  // Scoped to this domain do not leak this, however we can expose this value through the error type
  private[transaction] val DAILY_MAXIMUM_CREDIT_AMOUNT: LoadAmount.Repr = 50000 // cents

  def apply: ValidateDailyCreditAmountLimitServiceEitherInterpreter =
    new ValidateDailyCreditAmountLimitServiceEitherInterpreter()

}
