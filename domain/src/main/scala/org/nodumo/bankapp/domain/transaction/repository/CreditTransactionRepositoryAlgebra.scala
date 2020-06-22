package org.nodumo.bankapp.domain.transaction.repository

import org.nodumo.bankapp.domain.definitions.DomainEntity

/**
 * Credit transaction repository.
 *
 * Data requirement:
 * - "Insert credit transaction."
 * - "Retrieve transaction."
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @tparam F             Effect
 * @tparam CreditTransaction   Transaction
 * @tparam CreditTransactionId Transaction identifier
 * @tparam CustomerId    Customer id
 * @tparam CreditTimeStamp Time stamp
 * @tparam RawTimeStamp Time stamp
 */
trait CreditTransactionRepositoryAlgebra[F[_],
                                         CreditTransaction <: DomainEntity[CreditTransactionId],
                                         CreditTransactionId,
                                         CustomerId,
                                         CreditTimeStamp,
                                         RawTimeStamp] {

  /**
   * Creates credit transaction event record.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @param transaction New transaction
   */
  def createCreditTransaction(transaction: CreditTransaction): F[CreditTransaction]

  /**
   * Retrieve transactions after certain datetime.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @usecase Retrieving transactions after a particular datetime.
   * @param customerId Customer id
   * @param dateTimeStamp Timestamp
   */
  def getCreditTransactionsAfterCertainDate(customerId: CustomerId,
                                            dateTimeStamp: RawTimeStamp): F[List[CreditTransaction]]

}
