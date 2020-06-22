package org.bankapp.nodumo.common.customisationother.joda

/**
 * Get previous week.
 * " Each day is considered to end at midnight UTC,
 *   and weeks start on Monday (i.e. one second after 23:59:59 on Sunday)."
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @usecase Transaction producer application
 * @tparam DateTime DateTimeStamp
 * @tparam DaysCount Days unit
 */
trait DateTimeGetPreviousDaysServiceAlgebra[DateTime, DaysCount] {

  def getPreviousDayStart(daysUnit: DaysCount)(dateTime: DateTime): DateTime

}
