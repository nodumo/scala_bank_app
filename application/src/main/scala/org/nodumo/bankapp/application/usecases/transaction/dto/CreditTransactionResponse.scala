package org.nodumo.bankapp.application.usecases.transaction.dto

import io.circe._
import io.circe.generic.semiauto._
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{ CustomerId, TransactionId }

/**
 * Transaction response.
 *
 * "JSON response indicating whether the fund
 *  load was accepted based on the user's activity, with the structure"
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @param id Transaction id
 * @param customer_id Customer id
 * @param accepted Boolean indication whether or not the transaction has been accepted.
 */
final case class CreditTransactionResponse private[dto] (
    id: TransactionId.Repr,
    customer_id: CustomerId.Repr,
    accepted: Boolean
)

object CreditTransactionResponse {

  // JSON serializer
  implicit val creditTransactionResponseEncoder: Encoder.AsRoot[CreditTransactionResponse] =
    deriveEncoder[CreditTransactionResponse]

  /**
   * Success transaction response.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @param id Transaction id
   * @param customer_id Customer id
   */
  def succeedTransaction(id: TransactionId.Repr, customer_id: CustomerId.Repr) = CreditTransactionResponse(
    id = id,
    customer_id = customer_id,
    accepted = true
  )

  /**
   * Failed transaction response.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @param id Transaction id
   * @param customer_id Customer id
   */
  def failedTransaction(id: TransactionId.Repr, customer_id: CustomerId.Repr) = CreditTransactionResponse(
    id = id,
    customer_id = customer_id,
    accepted = false
  )

}
