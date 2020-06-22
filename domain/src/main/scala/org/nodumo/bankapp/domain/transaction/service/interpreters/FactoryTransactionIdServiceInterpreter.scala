package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.TransactionId
import org.nodumo.bankapp.domain.transaction.service.FactoryTransactionIdServiceAlgebra
import org.nodumo.bankapp.domain.transaction.service.service.EitherDomainValueError

class FactoryTransactionIdServiceInterpreter
    extends FactoryTransactionIdServiceAlgebra[EitherDomainValueError, TransactionId.Repr, TransactionId] {

  override def create(from: TransactionId.Repr): EitherDomainValueError[TransactionId] =
    Right(TransactionId(from))

}

object FactoryTransactionIdServiceInterpreter {

  def apply: FactoryTransactionIdServiceInterpreter =
    new FactoryTransactionIdServiceInterpreter()

}
