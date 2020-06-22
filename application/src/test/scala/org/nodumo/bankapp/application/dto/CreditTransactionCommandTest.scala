package org.nodumo.bankapp.application.dto

import org.specs2.Specification
import io.circe.parser.decode
import org.nodumo.bankapp.application.usecases.transaction.dto.CreditTransactionCommand

/**
 * Current credit transaction command test.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 */
final class CreditTransactionCommandTest extends Specification {

  def is = s2"""
 This is a specification to check the 'CreditTransactionCommandTest' class :
   Valid example 1                                         $validExample1
   Valid example 2                                         $validExample2
   Valid example 3                                         $validExample3

   Invalid example (MissingId)                             $invalidExampleMissingId
   Invalid example (Missing customer id)                   $invalidExampleMissingCustomerId
   Invalid example (Missing loading amount)                $invalidExampleMissingCustomerId
   Invalid example (Missing time)                          $invalidExampleMissingTime
                                                                 """

  // Example valid string

  val exampleValidString1 =
    """{"id":"17214","customer_id":"273","load_amount":"$4809.32","time":"2000-01-17T12:50:16Z"}"""
  val exampleValidString2 =
    """{"id":"18615","customer_id":"579","load_amount":"$4062.96","time":"2000-01-18T05:12:08Z"}"""
  val exampleValidString3 =
    """{"id":"17214","customer_id":"273","load_amount":"$4809.32","time":"2000-01-17T12:50:16Z"}"""

  // -- Invalid entities: Check if every property that is required is being caught by the string parser

  private val exampleInValidMissingId =
    """{ "customer_id":"273","load_amount":"$4809.32","time":"2000-01-17T12:50:16Z"}"""

  private val exampleInValidCustomerId =
    """{"id":"18615", "load_amount":"$4062.96","time":"2000-01-18T05:12:08Z"}"""

  private val exampleInValidMissingLoadingAmount =
    """{"id":"17214","customer_id":"273", time":"2000-01-17T12:50:16Z"}"""

  private val exampleInValidMissingTime =
    """{"id":"17214","customer_id":"273","load_amount":"$4809.32" }"""

  private def validExample1 = decode[CreditTransactionCommand](exampleValidString1) must not be left

  private def validExample2 = decode[CreditTransactionCommand](exampleValidString2) must not be left

  private def validExample3 = decode[CreditTransactionCommand](exampleValidString3) must not be left

  private def invalidExampleMissingId = decode[CreditTransactionCommand](exampleInValidMissingId) must not be right

  private def invalidExampleMissingCustomerId =
    decode[CreditTransactionCommand](exampleInValidCustomerId) must not be right

  private def invalidExampleMissingLoadingAmount =
    decode[CreditTransactionCommand](exampleInValidCustomerId) must not be right

  private def invalidExampleMissingTime = decode[CreditTransactionCommand](exampleInValidMissingTime) must not be right

}
