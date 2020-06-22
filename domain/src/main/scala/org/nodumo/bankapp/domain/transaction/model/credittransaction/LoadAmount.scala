package org.nodumo.bankapp.domain.transaction.model.credittransaction

import org.nodumo.bankapp.domain.definitions.DomainValue
import org.nodumo.bankapp.domain.transaction.model.credittransaction.LoadAmount.Repr

/**
 * Amount in USD cents.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @see Detailed explanation of how money should be stored in a database.
 *      https://rietta.com/blog/postgresql-currency-types/
 * @param value Underlying value
 */
final case class LoadAmount private[transaction] (value: Repr) extends DomainValue

object LoadAmount {

  type Repr = Long

}
