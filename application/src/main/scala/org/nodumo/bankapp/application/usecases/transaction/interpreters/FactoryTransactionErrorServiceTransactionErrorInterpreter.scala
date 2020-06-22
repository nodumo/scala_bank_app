package org.nodumo.bankapp.application.usecases.transaction.interpreters

import org.nodumo.bankapp.application.definitions.TransactionError
import org.nodumo.bankapp.application.usecases.transaction.FactoryTransactionErrorServiceAlgebra
import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransactionLimitError.{
  DailyCreditAmountLimitError,
  DailyTransactionCountLimitError,
  WeeklyAmountLimitError
}
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{ CreditTransactionLimitError, LoadAmount }

class FactoryTransactionErrorServiceTransactionErrorInterpreter
    extends FactoryTransactionErrorServiceAlgebra[CreditTransactionLimitError, TransactionError] {

  override def create(from: CreditTransactionLimitError): TransactionError = from match {

    case DailyCreditAmountLimitError(limit) =>
      TransactionError(String.format("Transaction credit amount limit %s.", limit.toString))

    case DailyTransactionCountLimitError(limit) =>
      TransactionError(String.format("Daily transaction count limit %s.", limit.toString))

    case WeeklyAmountLimitError(limit: LoadAmount.Repr) =>
      TransactionError(String.format("Domain amount limit error %s.", limit.toString))

  }

}

object FactoryTransactionErrorServiceTransactionErrorInterpreter {

  def apply: FactoryTransactionErrorServiceTransactionErrorInterpreter =
    new FactoryTransactionErrorServiceTransactionErrorInterpreter()

}
