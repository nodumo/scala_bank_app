package org.nodumo.bankapp.domain.transaction.service

import org.nodumo.bankapp.domain.definitions.DomainValueError
import org.nodumo.bankapp.domain.transaction.model.credittransaction.CreditTransactionLimitError

package object service {

  type EitherCreditTransactionLimitError[A] = Either[CreditTransactionLimitError, A]

  type EitherDomainValueError[A] = Either[DomainValueError, A]

}
