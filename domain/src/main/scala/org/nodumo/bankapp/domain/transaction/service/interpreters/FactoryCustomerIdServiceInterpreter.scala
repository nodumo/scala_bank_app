package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.CustomerId
import org.nodumo.bankapp.domain.transaction.service.FactoryCustomerIdServiceAlgebra
import org.nodumo.bankapp.domain.transaction.service.service.EitherDomainValueError

class FactoryCustomerIdServiceInterpreter
    extends FactoryCustomerIdServiceAlgebra[EitherDomainValueError, CustomerId.Repr, CustomerId] {

  override def create(from: CustomerId.Repr): EitherDomainValueError[CustomerId] =
    Right(CustomerId(from))

}

object FactoryCustomerIdServiceInterpreter {

  def apply: FactoryCustomerIdServiceInterpreter =
    new FactoryCustomerIdServiceInterpreter()

}
