package org.nodumo.bankapp.domain.transaction.service

/**
 * Validate weekly credit limit.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @tparam F Effect
 * @tparam Transaction Transaction
 */
trait ValidateDailyTransactionCountLimitServiceAlgebra[F[_], Transaction] {

  def validate(transaction: Transaction, list: List[Transaction]): F[Transaction]

}
