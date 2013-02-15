package com.ungersoft.fuzz

import org.specs2.mutable.Specification

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/15/13
 * Time: 2:09 PM
 */
class FuzzTest extends Specification {
  "Fuzz" should {
    "exist" in {
      new Fuzz must not beNull
    }
  }
}
