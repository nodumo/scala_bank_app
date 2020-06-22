package org.nodumo.bankapp.application.usecases.transaction.dto

import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto.{ deriveDecoder, deriveEncoder }

/**
 * Credit transaction attempt response.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @param id         Transaction id
 * @param customer_id Customer id
 * @param load_amount Amount to load
 * @param time        Datetime stamp
 */
final case class CreditTransactionCommand(
    id: String,
    customer_id: String,
    load_amount: String,
    time: String
)

object CreditTransactionCommand {

  implicit val transactionCommandDecoder: Decoder[CreditTransactionCommand] = deriveDecoder

  implicit val transactionCommandEncoder: Encoder[CreditTransactionCommand] = deriveEncoder

}
