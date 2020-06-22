package org.bankapp.nodumo.common.customisationother.joda.interpreters

import org.bankapp.nodumo.common.customisationother.joda.DateTimeParseServiceAlgebra
import org.joda.time.{ DateTime, DateTimeZone }
import org.joda.time.format.DateTimeFormatter

class DateTimeParseServiceNormalizedUTCInterpreter
    extends DateTimeParseServiceAlgebra[DateTime, String, DateTimeFormatter] {

  override def parseFrom(format: DateTimeFormatter)(from: String): DateTime =
    DateTime.parse(from, format).withZone(DateTimeZone.UTC)

}

object DateTimeParseServiceNormalizedUTCInterpreter {

  def apply: DateTimeParseServiceNormalizedUTCInterpreter =
    new DateTimeParseServiceNormalizedUTCInterpreter()

}
