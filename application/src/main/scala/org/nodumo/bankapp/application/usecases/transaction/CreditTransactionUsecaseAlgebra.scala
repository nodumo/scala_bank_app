package org.nodumo.bankapp.application.usecases.transaction

import org.nodumo.bankapp.application.definitions.UseCaseAlgebra

trait CreditTransactionUsecaseAlgebra[F[_], Input, Output] extends UseCaseAlgebra[F, Input, Output]
