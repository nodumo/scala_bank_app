package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.TimeStamp
import org.nodumo.bankapp.domain.transaction.service.FactoryTimeStampServiceAlgebra
import org.nodumo.bankapp.domain.transaction.service.service.EitherDomainValueError

class FactoryTimeStampServiceInterpreter
    extends FactoryTimeStampServiceAlgebra[EitherDomainValueError, TimeStamp.Repr, TimeStamp] {

  override def create(from: TimeStamp.Repr): EitherDomainValueError[TimeStamp] =
    Right(TimeStamp(from))

}

object FactoryTimeStampServiceInterpreter {

  def apply: FactoryTimeStampServiceInterpreter =
    new FactoryTimeStampServiceInterpreter()

}
