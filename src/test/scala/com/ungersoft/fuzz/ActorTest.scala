package com.ungersoft.fuzz

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/15/13
 * Time: 2:24 PM
 */

import org.scalatest.{ Suite, BeforeAndAfterAll }
import akka.testkit.TestKit

trait StopSystemAfterAll extends BeforeAndAfterAll {
  this: TestKit with Suite =>
  override protected def afterAll() {
    super.afterAll()
    system.shutdown()
  }
}
