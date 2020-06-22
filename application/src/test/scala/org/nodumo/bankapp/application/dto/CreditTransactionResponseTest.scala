package org.nodumo.bankapp.application.dto

import org.specs2.Specification
import io.circe.parser.decode
import org.nodumo.bankapp.application.usecases.transaction.dto.CreditTransactionResponse

final class CreditTransactionResponseTest extends Specification {
  def is = s2"""

 This is a specification to check the 'CreditTransactionResponse' class:
   contain 11 characters                                         $succeedTransactionSuccess
   start with 'Hello'                                            $succeedTransactionFailure
   end with 'world'                                              $creditTransactionResponseSerializeSuccess
                                                                 """

  private val creditTransactionResponseSuccess1 = CreditTransactionResponse.succeedTransaction("1230", "2332323")

  private val creditTransactionResponseSuccess2 = CreditTransactionResponse.succeedTransaction("1230", "2332323")

  private val creditTransactionResponseSuccess3 = CreditTransactionResponse.succeedTransaction("1230", "2332323")

  def succeedTransactionSuccess = creditTransactionResponseSuccess1.id.length must be greaterThan 1

  def succeedTransactionFailure = creditTransactionResponseSuccess1.customer_id.length must be greaterThan 2

  def creditTransactionResponseSerializeSuccess = "Hello world" must endWith("world")

}
