package org.bankapp.nodumo.common.customisationjava.sqltimestamp.interpreters

import java.sql.{ Timestamp => JavaSQLTimeStamp }
import org.bankapp.nodumo.common.customisationjava.sqltimestamp.SQLTimeStampFactoryServiceAlgebra
import org.joda.time.DateTime

class SQLTimeStampFactoryServiceJodaDateTimeInterpreter
    extends SQLTimeStampFactoryServiceAlgebra[DateTime, JavaSQLTimeStamp] {

  override def create(from: DateTime): JavaSQLTimeStamp = new JavaSQLTimeStamp(from.getMillis)

}

object SQLTimeStampFactoryServiceJodaDateTimeInterpreter {

  def apply: SQLTimeStampFactoryServiceJodaDateTimeInterpreter =
    new SQLTimeStampFactoryServiceJodaDateTimeInterpreter()

}
