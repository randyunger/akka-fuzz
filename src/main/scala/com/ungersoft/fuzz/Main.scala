package com.ungersoft.fuzz

import actors.{PrintActor, PhonemeActor}
import actors.PhonemeProtocol.RawSentence
import akka.actor.{Props, ActorSystem}

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/15/13
 * Time: 3:33 PM
 */

object SentenceTimer {
  def main(args: Array[String]) {
    val system = ActorSystem("SentenceTimer")
    val printActor = system.actorOf(Props[PrintActor], "print")
    val phonemeActor = system.actorOf(Props(new PhonemeActor(printActor)), "phoneme1")

    val startTime: Long = System.currentTimeMillis

        val lines = scala.io.Source.fromFile("subset.txt")("UTF-8").getLines().toSet
//    val lines = scala.io.Source.fromFile("input.csv")("UTF-8").getLines().toSet
    lines foreach ( l => phonemeActor ! RawSentence(l, 5))

    val endTime: Long = System.currentTimeMillis
    System.out.println("Runtime: " + (endTime - startTime) + "ms")
  }
}