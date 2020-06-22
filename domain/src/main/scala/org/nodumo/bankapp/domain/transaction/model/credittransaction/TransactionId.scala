package org.nodumo.bankapp.domain.transaction.model.credittransaction

import org.nodumo.bankapp.domain.definitions.DomainValue
import org.nodumo.bankapp.domain.transaction.model.credittransaction.TransactionId.Repr

final case class TransactionId private[transaction] (value: Repr) extends DomainValue

object TransactionId {

  type Repr = String

}
