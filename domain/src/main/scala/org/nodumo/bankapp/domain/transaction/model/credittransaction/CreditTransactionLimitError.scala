package org.nodumo.bankapp.domain.transaction.model.credittransaction

/**
 * Transaction forbidden error
 *
 * - A maximum of "x" can be loaded per day
 * - A maximum of "x" can be loaded per week
 * - A maximum of "x" loads can be performed per day, regardless of amount
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 */
sealed abstract class CreditTransactionLimitError

object CreditTransactionLimitError {

  final case class DailyCreditAmountLimitError(limit: LoadAmount.Repr) extends CreditTransactionLimitError

  final case class DailyTransactionCountLimitError(limit: Int) extends CreditTransactionLimitError

  final case class WeeklyAmountLimitError(limit: LoadAmount.Repr) extends CreditTransactionLimitError

}
