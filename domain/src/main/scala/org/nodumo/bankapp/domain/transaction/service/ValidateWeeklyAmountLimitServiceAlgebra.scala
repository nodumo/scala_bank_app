package org.nodumo.bankapp.domain.transaction.service

/**
 * Validate weekly upload amount.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @tparam F Effect
 * @tparam Transaction Transaction
 */
trait ValidateWeeklyAmountLimitServiceAlgebra[F[_], Transaction] {

  def validate(transaction: Transaction, list: List[Transaction]): F[Transaction]

}
