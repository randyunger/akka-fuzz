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
 * Time: 2:14 PM
 */

class PrintActorTest extends TestKit(ActorSystem("testsystem"))
with WordSpec
with MustMatchers
with StopSystemAfterAll {
  "A Print Actor" must {

    "Print a message" in {

      val printActor = system.actorOf(Props(new PhonemeActor(testActor)), "s3")
      printActor ! "test message"

      true
    }

  }
}