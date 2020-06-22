package org.nodumo.bankapp.application

import io.circe.syntax._
import scala.io.Source
import io.circe.parser
import org.nodumo.bankapp.application.usecases.transaction.dto.CreditTransactionCommand
import org.nodumo.bankapp.application.usecases.transaction.interpreters.creditAccuntUsecaseCatsEffect

/**
 * Main application.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 */
object Main {

  private val creditAccountUsecase = creditAccuntUsecaseCatsEffect

  private def iteratorLinesOfFiles: Iterator[String] = Source.fromResource("transactions.txt").getLines()

  def main(args: Array[String]): Unit =
    for (line <- iteratorLinesOfFiles) {
      // See: https://medium.com/@djoepramono/how-to-parse-json-in-scala-c024cb44f66b
      parser.decode[CreditTransactionCommand](line) match {
        // Run blocking operation
        case Right(creditTransactionCommand) =>
          val transactionResult = creditAccountUsecase.execute(creditTransactionCommand).unsafeRunSync()
          println(transactionResult.asJson) // {"id":"15887","customer_id":"528","accepted":true}
        // Left issue parsing the command from source file
        case Left(error) =>
          println(error.getMessage)
      }
    }

}
