package org.nodumo.bankapp.domain.transaction.model.credittransaction

import java.text.SimpleDateFormat

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.nodumo.bankapp.domain.definitions.DomainValue
import org.nodumo.bankapp.domain.transaction.model.credittransaction.TimeStamp.Repr

/**
 * Time stamp with time.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @example {{{
 *   val rawTime1 =  "2000-01-01T00:00:00Z"
 *   val rawTime2 =  "2000-01-01T23:31:26Z"
 * }}}
 * @see See this explanation of the format:
 *      http://cdsportal.u-strasbg.fr/taptuto/javadoc/uws/ISO8601Format.html
 *      https://stackoverflow.com/questions/49752149/how-do-i-convert-2018-04-10t040000-000z-string-to-datetime/49752361
 * @param value
 */
final case class TimeStamp private[transaction] (value: Repr) extends DomainValue

object TimeStamp {

  type Repr = DateTime

  private[domain] val format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

}
