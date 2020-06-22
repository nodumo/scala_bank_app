package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.LoadAmount
import org.nodumo.bankapp.domain.transaction.service.FactoryLoadAmountServiceAlgebra
import org.nodumo.bankapp.domain.transaction.service.service.EitherDomainValueError

class FactoryLoadAmountServiceInterpreter
    extends FactoryLoadAmountServiceAlgebra[EitherDomainValueError, LoadAmount.Repr, LoadAmount] {

  override def create(from: LoadAmount.Repr): EitherDomainValueError[LoadAmount] =
    Right(LoadAmount(from))

}

object FactoryLoadAmountServiceInterpreter {

  def apply: FactoryLoadAmountServiceInterpreter =
    new FactoryLoadAmountServiceInterpreter()

}
