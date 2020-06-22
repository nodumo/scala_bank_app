package org.bankapp.nodumo.common.customisationother.joda

import org.bankapp.nodumo.common.customisationother.joda.interpreters
import org.specs2.Specification

class JodaUtilitiesTest extends Specification {
  def is         = s2"""

  This is a specification for the Joda utilities package

  The 'Todo Write Test' string should
    contain 11 characters                             $e1
    start with 'Hello'                                $e2
    end with 'world'                                  $e3
                                                      """
  private def e1 = "Todo Write Test" must haveSize(11)

  private def e2 = "Todo Write Test" must startWith("Hello")

  private def e3 = "Todo Write Test" must endWith("world")

}
