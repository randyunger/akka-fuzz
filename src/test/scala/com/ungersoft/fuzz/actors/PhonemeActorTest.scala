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

class PhonemeActorTest extends TestKit(ActorSystem("testsystem"))
with WordSpec
with MustMatchers
with StopSystemAfterAll {
  "A Phoneme Actor" must {

    "Respond with a processed sentence" in {
      import PhonemeProtocol._

      val phonemeActor = system.actorOf(Props(new PhonemeActor(testActor)), "s3")
      phonemeActor ! RawSentence("whisper", 5)

      expectMsg(Sentence("whisper", "whisper", Set("whisp", "hispe", "isper", "whispe", "hisper")))
    }

  }
}