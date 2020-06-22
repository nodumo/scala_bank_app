package org.bankapp.nodumo.common.customisationother.joda.interpreters

import org.bankapp.nodumo.common.customisationother.joda.DateTimeGetPreviousWeeksServiceAlgebra
import org.joda.time.{ DateTime, Weeks }

class DateTimeGetPreviousWeeksServiceInterpreter extends DateTimeGetPreviousWeeksServiceAlgebra[DateTime, Weeks] {

  override def getPreviousWeeksStart(weeksUnit: Weeks)(dateTime: DateTime): DateTime =
    dateTime.minusWeeks(weeksUnit.getWeeks)

}

object DateTimeGetPreviousWeeksServiceInterpreter {

  def apply: DateTimeGetPreviousWeeksServiceInterpreter =
    new DateTimeGetPreviousWeeksServiceInterpreter()

}
