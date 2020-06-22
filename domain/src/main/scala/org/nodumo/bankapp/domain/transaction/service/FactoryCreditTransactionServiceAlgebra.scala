package org.nodumo.bankapp.domain.transaction.service

import org.bankapp.nodumo.common.definitions.FactoryEffectfulAlgebra

trait FactoryCreditTransactionServiceAlgebra[F[_], From, To] extends FactoryEffectfulAlgebra[F, From, To]
