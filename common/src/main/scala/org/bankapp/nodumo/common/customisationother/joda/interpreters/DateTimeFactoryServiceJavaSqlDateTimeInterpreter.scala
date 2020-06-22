package org.bankapp.nodumo.common.customisationother.joda.interpreters

import java.sql.{ Timestamp => JavaSQlTimeStamp }
import org.bankapp.nodumo.common.customisationother.joda.DateTimeFactoryServiceAlgebra
import org.joda.time.DateTime

class DateTimeFactoryServiceJavaSqlDateTimeInterpreter
    extends DateTimeFactoryServiceAlgebra[JavaSQlTimeStamp, DateTime] {

  override def create(from: JavaSQlTimeStamp): DateTime =
    new DateTime(from)

}

object DateTimeFactoryServiceJavaSqlDateTimeInterpreter {

  def apply: DateTimeFactoryServiceJavaSqlDateTimeInterpreter =
    new DateTimeFactoryServiceJavaSqlDateTimeInterpreter()

}
