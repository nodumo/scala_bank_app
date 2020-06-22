package org.nodumo.bankapp.application.usecases.transaction

import org.nodumo.bankapp.domain.transaction.service.interpreters.{
  factoryCreditTransactionService,
  validateTransactionServiceEitherInterpreter
}
import org.nodumo.bankapp.infrastructure.repositories.transaction.doobiepostgres
import cats.instances.all._

package object interpreters {

  object creditTransactionReprFactoryService$DomainRepr$
      extends FactoryCreditTransactionServiceReprDomainReprInterpreter

  object factoryTransactionErrorServiceTransactionError
      extends FactoryTransactionErrorServiceTransactionErrorInterpreter

  object factoryTransactionErrorServiceDomainValueError
      extends FactoryTransactionErrorServiceDomainValueErrorInterpreter

  object creditAccuntUsecaseCatsEffect
      extends CreditUsecaseCatsEffectInterpreter(
        factoryCreditTransactionService,
        creditTransactionReprFactoryService$DomainRepr$,
        validateTransactionServiceEitherInterpreter,
        doobiepostgres.creditTransactionRepository
      )

}
