package org.nodumo.bankapp.application.usecases.transaction.interpreters

import org.nodumo.bankapp.application.usecases.transaction.FactoryCreditTransactionServiceReprAlgebra
import org.nodumo.bankapp.application.usecases.transaction.dto.CreditTransactionCommand
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{ CreditTransaction, LoadAmount }
import org.bankapp.nodumo.common.customisationother.joda.formatISO8601
import org.bankapp.nodumo.common.customisationother.joda.interpreters.dateTimeParseServiceNormalizedUTCInterpreter

/**
 * Factory credit transaction service.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @usecase Parsing the external command into the internal representation of the data.
 */
class FactoryCreditTransactionServiceReprDomainReprInterpreter
    extends FactoryCreditTransactionServiceReprAlgebra[CreditTransactionCommand, CreditTransaction.Repr] {

  /* Unsafe there are better ways of encoding the values incoming */
  private def parseMoneyAsLong(moneyString: String): Long = {
    val CENTS_IN_A_DOLLAR: LoadAmount.Repr                = 100L
    val moneyRegexPattern                                 = """\$([0-9]+).([0-9]+)""".r
    val moneyRegexPattern(dollarsAsString, centsAsString) = moneyString
    (dollarsAsString.toLong * CENTS_IN_A_DOLLAR) + centsAsString.toLong
  }

  override def create(from: CreditTransactionCommand): CreditTransaction.Repr =
    CreditTransaction.Repr(from.id,
                           from.customer_id,
                           parseMoneyAsLong(from.load_amount),
                           dateTimeParseServiceNormalizedUTCInterpreter.parseFrom(formatISO8601)(from.time))

}

object FactoryCreditTransactionServiceReprDomainReprInterpreter {

  def apply: FactoryCreditTransactionServiceReprDomainReprInterpreter =
    new FactoryCreditTransactionServiceReprDomainReprInterpreter()

}
