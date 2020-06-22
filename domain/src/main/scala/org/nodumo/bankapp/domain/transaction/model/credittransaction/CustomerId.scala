package org.nodumo.bankapp.domain.transaction.model.credittransaction

import org.nodumo.bankapp.domain.definitions.DomainValue
import org.nodumo.bankapp.domain.transaction.model.credittransaction.CustomerId.Repr

final case class CustomerId private[transaction] (value: Repr) extends DomainValue

object CustomerId {

  type Repr = String

}
