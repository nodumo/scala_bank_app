package org.nodumo.bankapp.infrastructure.repositories.transaction

import cats.effect.IO
import doobie.util.log.LogHandler
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext

package object doobiepostgres {

  private implicit val loggingHandler = LogHandler.jdkLogHandler

  private implicit val contextShift = IO.contextShift(ExecutionContext.global)

  private val transactor = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql://raja.db.elephantsql.com:5432/aqhfdksj",
    "aqhfdksj",
    "A9m5B9BF4KS3BlLinFMkbW1mBfnNGEPF"
  )
  val creditTransactionRepository = CreditTransactionRepositoryDoobiePostgresInterpreter.apply(transactor)
}
