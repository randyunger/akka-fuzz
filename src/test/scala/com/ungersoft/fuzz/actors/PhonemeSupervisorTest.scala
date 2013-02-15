package com.ungersoft.fuzz.actors

import akka.testkit.TestKit
import akka.actor.{Props, ActorSystem}
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import com.ungersoft.fuzz.StopSystemAfterAll

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/15/13
 * Time: 3:43 PM
 */

class PhonemeSupervisorTest extends TestKit(ActorSystem("testsystem"))
with WordSpec
with MustMatchers
with StopSystemAfterAll {
  "A Phoneme Supervisor" must {

    "Forward a sentence" in {

    }

  }
}