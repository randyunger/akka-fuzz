package com.ungersoft.fuzz.actors

import akka.actor.Actor

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/15/13
 * Time: 3:37 PM
 */

class PrintActor extends Actor {
  val doPrint = false

  def receive = {
    case m => if (doPrint) println(m)
  }
}
