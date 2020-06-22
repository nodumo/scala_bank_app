package org.bankapp.nodumo.common.customisationother.joda

/**
 * Get previous week(s).
 *
 * " Each day is considered to end at midnight UTC,
 *   and weeks start on Monday (i.e. one second after 23:59:59 on Sunday)." - Domain Expert
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @usecase Transaction producer application
 * @tparam DateTime DateTimeStamp
 * @tparam WeeksUnit Weeks unit
 */
trait DateTimeGetPreviousWeeksServiceAlgebra[DateTime, WeeksUnit] {

  def getPreviousWeeksStart(weeksUnit: WeeksUnit)(dateTime: DateTime): DateTime

}
