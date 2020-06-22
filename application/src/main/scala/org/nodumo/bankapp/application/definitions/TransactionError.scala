package org.nodumo.bankapp.application.definitions

import org.nodumo.bankapp.application.definitions.TransactionError.Message

final case class TransactionError(value: Message) extends AnyVal

object TransactionError {

  type Message = String

}
