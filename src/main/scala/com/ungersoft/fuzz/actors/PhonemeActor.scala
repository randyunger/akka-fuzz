package com.ungersoft.fuzz.actors

import akka.actor.{ActorRef, Actor}
import com.ungersoft.fuzz.actors.PhonemeProtocol.{Sentence, RawSentence}

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/15/13
 * Time: 2:29 PM
 */
class PhonemeActor(receiver: ActorRef) extends Actor {

  def phonemes(strippedSentence: String, smallestPhonemeSize: Int, largestPhonemeSize: Int) = {
    val maxSize = math.min(largestPhonemeSize, strippedSentence.size)
    val minSize = math.max(smallestPhonemeSize, 1)

    //Find all subsequences from minimum size to word.length
    @annotation.tailrec
    def findPhonemes(phonemeList: Seq[String], sizeToFind: Int): Seq[String] = {
      if(strippedSentence.size <= minSize) Seq(strippedSentence)              //If sentence is below minimum size, just use sentence as only phoneme
      else if(sizeToFind == maxSize) phonemeList                               //Done processing, return accumulator
      else findPhonemes(phonemeList ++ phonemesOfSize(sizeToFind), sizeToFind + 1)        //continue processing with a size one greater
    }

    //Iterate via a sliding window of size n to find subsequences of size n
    def phonemesOfSize(n: Int):Set[String] = strippedSentence.iterator.sliding(n, 1) map (_.mkString) toSet

    findPhonemes(Seq.empty, minSize)
  }

  def receive = {
    case RawSentence(words, minSize, maxSize) => {
      val strippedSentence = words.toLowerCase.replaceAll("[^a-z]","")
      val phon = phonemes(words, minSize, maxSize)
      receiver ! Sentence(words, strippedSentence, phon.toSet)
    }
  }
}

object PhonemeProtocol {

  case class RawSentence(rawSentence: String, smallestPhonemeSize: Int = 1, largestPhonemeSize: Int = Integer.MAX_VALUE)
  case class Sentence(rawSentence: String, strippedSentence: String, phonemes: Set[String])

}

