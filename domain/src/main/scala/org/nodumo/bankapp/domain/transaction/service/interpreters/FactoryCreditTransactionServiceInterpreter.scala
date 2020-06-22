package org.nodumo.bankapp.domain.transaction.service.interpreters

import org.nodumo.bankapp.domain.transaction.model.credittransaction.CustomerId.Repr
import org.nodumo.bankapp.domain.transaction.model.credittransaction.LoadAmount.Repr
import org.nodumo.bankapp.domain.transaction.model.credittransaction.TimeStamp.Repr
import org.nodumo.bankapp.domain.transaction.model.credittransaction.TransactionId.Repr
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{
  CreditTransaction,
  CustomerId,
  LoadAmount,
  TimeStamp,
  TransactionId
}
import org.nodumo.bankapp.domain.transaction.service.{
  FactoryCreditTransactionServiceAlgebra,
  FactoryCustomerIdServiceAlgebra,
  FactoryLoadAmountServiceAlgebra,
  FactoryTimeStampServiceAlgebra,
  FactoryTransactionIdServiceAlgebra
}
import org.nodumo.bankapp.domain.transaction.service.service.EitherDomainValueError

/**
 * Factory for credit transaction.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @usecase Composing "Domain Value"  smart constructors into smart constructor for "Domain Aggregate"
 * @param factoryTransactionIdService Factory for Transaction Id
 * @param factoryCustomerIdService Factory for CustomerId
 * @param factoryLoadAmountService Factory for Load Amount
 * @param factoryTimeStampService Factory for TimeStamp
 */
class FactoryCreditTransactionServiceInterpreter(
    factoryTransactionIdService: FactoryTransactionIdServiceAlgebra[EitherDomainValueError,
                                                                    TransactionId.Repr,
                                                                    TransactionId],
    factoryCustomerIdService: FactoryCustomerIdServiceAlgebra[EitherDomainValueError, CustomerId.Repr, CustomerId],
    factoryLoadAmountService: FactoryLoadAmountServiceAlgebra[EitherDomainValueError, LoadAmount.Repr, LoadAmount],
    factoryTimeStampService: FactoryTimeStampServiceAlgebra[EitherDomainValueError, TimeStamp.Repr, TimeStamp]
) extends FactoryCreditTransactionServiceAlgebra[EitherDomainValueError, CreditTransaction.Repr, CreditTransaction] {

  override def create(from: CreditTransaction.Repr): EitherDomainValueError[CreditTransaction] =
    for {
      id         <- factoryTransactionIdService.create(from.id)
      customerId <- factoryCustomerIdService.create(from.customerId)
      loadAmount <- factoryLoadAmountService.create(from.loadAmount)
      timeStamp  <- factoryTimeStampService.create(from.timeStamp)
    } yield CreditTransaction(id, customerId, loadAmount, timeStamp)

}

object FactoryCreditTransactionServiceInterpreter {

  def apply(
      factoryTransactionIdService: FactoryTransactionIdServiceAlgebra[EitherDomainValueError,
                                                                      TransactionId.Repr,
                                                                      TransactionId],
      factoryCustomerIdService: FactoryCustomerIdServiceAlgebra[EitherDomainValueError, CustomerId.Repr, CustomerId],
      factoryLoadAmountService: FactoryLoadAmountServiceAlgebra[EitherDomainValueError, LoadAmount.Repr, LoadAmount],
      factoryTimeStampService: FactoryTimeStampServiceAlgebra[EitherDomainValueError, TimeStamp.Repr, TimeStamp]
  ): FactoryCreditTransactionServiceInterpreter =
    new FactoryCreditTransactionServiceInterpreter(factoryTransactionIdService,
                                                   factoryCustomerIdService,
                                                   factoryLoadAmountService,
                                                   factoryTimeStampService)

}
