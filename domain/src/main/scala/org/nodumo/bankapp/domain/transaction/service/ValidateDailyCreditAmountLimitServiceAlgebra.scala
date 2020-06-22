package org.nodumo.bankapp.domain.transaction.service

/**
 * Validate daily upload amount.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @tparam F Effect
 * @tparam Transaction Transaction
 */
trait ValidateDailyCreditAmountLimitServiceAlgebra[F[_], Transaction] {

  def validate(transaction: Transaction, list: List[Transaction]): F[Transaction]

}
