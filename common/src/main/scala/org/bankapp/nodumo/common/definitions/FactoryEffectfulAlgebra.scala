package org.bankapp.nodumo.common.definitions

trait FactoryEffectfulAlgebra[F[_], From, To] {

  def create(from: From): F[To]

}
