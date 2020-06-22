package org.nodumo.bankapp.domain.definitions

final case class DomainValueError private[domain] (message: String) extends Throwable
