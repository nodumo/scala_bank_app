package org.bankapp.nodumo.common.customisationother.joda.interpreters

import org.bankapp.nodumo.common.customisationother.joda.DateTimeGetPreviousDaysServiceAlgebra
import org.joda.time.{ DateTime, Days }

class DateTimeGetPreviousDaysServiceInterpreter extends DateTimeGetPreviousDaysServiceAlgebra[DateTime, Days] {

  override def getPreviousDayStart(daysUnit: Days)(dateTime: DateTime): DateTime =
    dateTime.minusDays(daysUnit.getDays)

}

object DateTimeGetPreviousDaysServiceInterpreter {

  def apply: DateTimeGetPreviousDaysServiceInterpreter =
    new DateTimeGetPreviousDaysServiceInterpreter()

}
