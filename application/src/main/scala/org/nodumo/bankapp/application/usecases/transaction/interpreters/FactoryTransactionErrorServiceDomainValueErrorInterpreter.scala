package org.nodumo.bankapp.application.usecases.transaction.interpreters

import org.nodumo.bankapp.application.definitions.TransactionError
import org.nodumo.bankapp.application.usecases.transaction.FactoryTransactionErrorServiceAlgebra
import org.nodumo.bankapp.domain.definitions.DomainValueError

class FactoryTransactionErrorServiceDomainValueErrorInterpreter
    extends FactoryTransactionErrorServiceAlgebra[DomainValueError, TransactionError] {

  override def create(from: DomainValueError): TransactionError =
    TransactionError("Domain value error:" ++ from.message)

}

object FactoryTransactionErrorServiceDomainValueErrorInterpreter {

  def apply: FactoryTransactionErrorServiceTransactionErrorInterpreter =
    new FactoryTransactionErrorServiceTransactionErrorInterpreter()

}
