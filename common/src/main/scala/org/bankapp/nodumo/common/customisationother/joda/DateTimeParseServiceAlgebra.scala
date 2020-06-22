package org.bankapp.nodumo.common.customisationother.joda

trait DateTimeParseServiceAlgebra[DateTime, From, Format] {

  def parseFrom(format: Format)(from: From): DateTime

}
