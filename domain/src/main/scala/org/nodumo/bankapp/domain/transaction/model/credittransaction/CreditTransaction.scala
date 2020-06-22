package org.nodumo.bankapp.domain.transaction.model.credittransaction

import org.nodumo.bankapp.domain.definitions.DomainEntity

/**
 * Credit transaction.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @usecase Transactiom/model/credit transaction
 */
final case class CreditTransaction private[transaction] (
    id: TransactionId,
    customerId: CustomerId,
    loadAmount: LoadAmount,
    timeStamp: TimeStamp
) extends DomainEntity[TransactionId]

object CreditTransaction {

  /**
   * Credit transaction raw representation
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @usecase Raw representation of the domain entity
   */
  final case class Repr(
      id: TransactionId.Repr,
      customerId: CustomerId.Repr,
      loadAmount: LoadAmount.Repr,
      timeStamp: TimeStamp.Repr
  )
}
