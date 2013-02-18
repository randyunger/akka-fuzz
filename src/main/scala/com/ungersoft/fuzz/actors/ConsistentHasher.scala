package com.ungersoft.fuzz.actors

import collection.immutable.TreeMap

/**
 * Created with IntelliJ IDEA.
 * User: randy
 * Date: 2/18/13
 * Time: 11:19 AM
 */

class ConsistentHasher[T](nodeMap: TreeMap[Int, T] = TreeMap.empty[Int, T])(implicit numberOfReplicas: Int = 3) {
//  (numberOfReplicas: Int = 3)
//  val numberOfReplicas = 3
  import scala.util.hashing.MurmurHash3
  private def hashInt(i :Int) = MurmurHash3.stringHash(i.toString)

  def add(node: T) = {
    val newNodes = (1 to numberOfReplicas) map (i => (hashInt(i + node.hashCode) -> node))
    new ConsistentHasher(numberOfReplicas)(nodeMap ++ newNodes)
  }

  def remove(node: T) = {

    @annotation.tailrec
    def remove0(m: TreeMap[Int, T], toRemove: Seq[Int]): TreeMap[Int, T] = toRemove match {
      case Seq(a, rest @ _ *) => remove0(nodeMap - a, rest)
      case Seq() => nodeMap
    }

    val toRemove = (1 to numberOfReplicas) map ((i: Int) => hashInt(i + node.hashCode))
    new ConsistentHasher(numberOfReplicas)(remove0(nodeMap, toRemove))
  }

  def get(a: Any): T = {
    nodeMap.from(hashInt(a.hashCode)).headOption match {
      case Some((key, value)) => value
      case None => nodeMap.headOption.get._2
    }
  }

  override def toString = "ConsistentHasher(" + nodeMap.toString + ")"
}
