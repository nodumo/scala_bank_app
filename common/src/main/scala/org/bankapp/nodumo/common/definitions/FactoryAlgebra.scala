package org.bankapp.nodumo.common.definitions

trait FactoryAlgebra[From, To] {

  def create(from: From): To

}
