package org.nodumo.bankapp.domain.transaction.service

trait ValidateTransactionServiceAlgebra[F[_], Transaction] {

  def validate(transaction: Transaction,
               oneDayTransactionsFrame: List[Transaction],
               oneWeekTransactionsFrame: List[Transaction]): F[Transaction]

}
