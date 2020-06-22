package org.nodumo.bankapp.infrastructure

import cats.effect.IO
import doobie.util.log.LogHandler
import doobie.util.transactor.Transactor
import org.joda.time.{ DateTime, DateTimeZone }
import org.joda.time.format.DateTimeFormat
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{
  CreditTransaction,
  CustomerId,
  LoadAmount,
  TimeStamp,
  TransactionId
}
import org.nodumo.bankapp.infrastructure.repositories.transaction.doobiepostgres.CreditTransactionRepositoryDoobiePostgresInterpreter

import scala.concurrent.ExecutionContext

object Main extends App {

  implicit val han = LogHandler.jdkLogHandler

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val transactor = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql://salt.db.elephantsql.com:5432/aqhfdksj",
    "aqhfdksj",
    "A9m5B9BF4KS3BlLinFMkbW1mBfnNGEPF"
  )

  val repo = CreditTransactionRepositoryDoobiePostgresInterpreter.apply(transactor)

  val creditTransaction =
    CreditTransaction(TransactionId("demo"), CustomerId("demo"), LoadAmount(1), TimeStamp(DateTime.now()))

  repo.createCreditTransaction(creditTransaction).unsafeRunSync()

  val format      = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  val utcDateTime = DateTime.parse("2000-02-12T02:30:16Z", format).withZone(DateTimeZone.UTC)
  println(utcDateTime.toDateTimeISO)

}
