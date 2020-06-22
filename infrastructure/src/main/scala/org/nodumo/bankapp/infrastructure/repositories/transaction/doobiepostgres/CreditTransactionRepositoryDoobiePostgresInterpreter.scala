package org.nodumo.bankapp.infrastructure.repositories.transaction.doobiepostgres

import java.sql.{ Timestamp => JavaSQlTimeStamp }
import java.time.LocalDateTime

import org.bankapp.nodumo.common.customisationjava.sqltimestamp.interpreters.sqlTimeStampFactoryServiceJodaDateTime
import org.bankapp.nodumo.common.customisationother.joda.interpreters.dateTimeFactoryServiceFromJavaSqlDateTime
// Implicits for writing and reading timestamps
import doobie.implicits.javasql._
import doobie._
import doobie.implicits._
import cats.effect.Bracket
import org.joda.time.DateTime
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{
  CreditTransaction,
  CustomerId,
  LoadAmount,
  TimeStamp,
  TransactionId
}
import org.nodumo.bankapp.domain.transaction.repository.CreditTransactionRepositoryAlgebra
import org.nodumo.bankapp.infrastructure.repositories.transaction.doobiepostgres.CreditTransactionRepositoryDoobiePostgresInterpreter._

/**
 * Credit transaction Doobie Postgres repository.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @see
 *    See this (Database driver):
 *    https://tpolecat.github.io/doobie/docs/07-Updating.html
 *    See this link (D):
 *    https://tapoueh.org/blog/2018/04/postgresql-data-types-date-timestamp-and-time-zones/
 * @tparam F             Effect
 * @param transactor Transactor
 * @param bracket Bracket
 */
final class CreditTransactionRepositoryDoobiePostgresInterpreter[F[_]](transactor: Transactor[F])(
    implicit bracket: Bracket[F, Throwable])
    extends CreditTransactionRepositoryAlgebra[F, CreditTransaction, TransactionId, CustomerId, TimeStamp, DateTime] {

  private val logger = LogHandler.jdkLogHandler

  // -- Privates: Query utilities

  private val insertCreditTransactionClause =
    fr"INSERT INTO public.accountevents (id, customerid, loadamount, timestamp) "

  private val selectFromCreditTransactionClause =
    fr" SELECT id, customerid, loadAmount, timeStamp FROM public.accountevents "

  private def whereCustomerIdEqualsAndAfterTimeStampClause(customerId: CustomerId, dateTimeStamp: DateTime) =
    sql" WHERE customerid = $customerId AND  timestamp > $dateTimeStamp  "

  private def valuesClause(transaction: CreditTransaction) =
    sql" VALUES (${transaction.id},${transaction.customerId},${transaction.loadAmount}, ${transaction.timeStamp}) "

  // -- Overrides

  override def createCreditTransaction(transaction: CreditTransaction): F[CreditTransaction] =
    (insertCreditTransactionClause ++ valuesClause(transaction))
      .updateWithLogHandler(logger)
      .run
      .map(_ => transaction) transact transactor

  override def getCreditTransactionsAfterCertainDate(customerId: CustomerId,
                                                     dateTimeStamp: DateTime): F[List[CreditTransaction]] =
    (selectFromCreditTransactionClause ++ whereCustomerIdEqualsAndAfterTimeStampClause(customerId, dateTimeStamp))
      .queryWithLogHandler[CreditTransaction](logger)
      .to[List]
      .transact(transactor)

}

object CreditTransactionRepositoryDoobiePostgresInterpreter {

  def apply[F[_]](transactor: Transactor[F])(
      implicit bracket: Bracket[F, Throwable]): CreditTransactionRepositoryDoobiePostgresInterpreter[F] =
    new CreditTransactionRepositoryDoobiePostgresInterpreter(transactor)

  //  --- Parsers and encoders for (Domain instances)

  implicit val transactionIdGet: Get[TransactionId] = Get[TransactionId.Repr].tmap(TransactionId.apply)
  implicit val transactionIdPut: Put[TransactionId] = Put[TransactionId.Repr].tcontramap(_.value)

  implicit val customerIdGet: Get[CustomerId] = Get[CustomerId.Repr].tmap(CustomerId.apply)
  implicit val customerIdPut: Put[CustomerId] = Put[CustomerId.Repr].tcontramap(_.value)

  implicit val loadAmountGet: Get[LoadAmount] = Get[LoadAmount.Repr].tmap(LoadAmount.apply)
  implicit val loadAmountPut: Put[LoadAmount] = Put[LoadAmount.Repr].tcontramap(_.value)

  implicit val timeStampGet: Get[TimeStamp] =
    Get[JavaSQlTimeStamp].tmap(sqlTimeStamp =>
      TimeStamp(dateTimeFactoryServiceFromJavaSqlDateTime.create(sqlTimeStamp)))

  implicit val timeStampPut: Put[TimeStamp] =
    Put[JavaSQlTimeStamp].tcontramap(time => sqlTimeStampFactoryServiceJodaDateTime.create(time.value))

  //  --- Parsers and encoders (Java and 3rd party instances)

  implicit val jodaDateTimeGet: Get[DateTime] =
    Get[JavaSQlTimeStamp].tmap(sqlTimeStamp => dateTimeFactoryServiceFromJavaSqlDateTime.create(sqlTimeStamp))

  implicit val jodaDateTimePut: Put[DateTime] =
    Put[JavaSQlTimeStamp].tcontramap(sqlTimeStampFactoryServiceJodaDateTime.create)

}
