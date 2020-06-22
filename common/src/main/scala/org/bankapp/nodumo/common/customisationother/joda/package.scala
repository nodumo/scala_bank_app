package org.bankapp.nodumo.common.customisationother

import org.joda.time.format.{ DateTimeFormat, DateTimeFormatter }

package object joda {

  // Example: "2000-02-12T02:30:16Z"
  // See: http://cdsportal.u-strasbg.fr/taptuto/javadoc/uws/ISO8601Format.html
  val formatISO8601: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

}
