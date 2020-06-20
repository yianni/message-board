package actors

import java.util.concurrent.ConcurrentHashMap

import akka.actor.Actor

import scala.collection.JavaConverters._

// Write ops
case class Increment(id: String)
case class Decrement(id: String)
case class Rebase()
case class Delete(id: String)
case class Add(id: String, text: String)

// Read ops
case class Print()
case class Get(id: String)
case class Get2(ids: List[String])
case class All()
case class Maps()

// Actors
case object VoteActor
case object PostActor

class VoteActor extends Actor {
  //  msgId -> count
  private var msgIdCountLookup = new ConcurrentHashMap[String, Long]().asScala.withDefaultValue(0L)

  def receive: PartialFunction[Any, Unit] = {
    // write ops
    case Increment(id) => sender() ! (msgIdCountLookup += (id -> (msgIdCountLookup(id) + 1))).get(id)
    case Decrement(id) => sender() ! (msgIdCountLookup += (id -> (msgIdCountLookup(id) - 1))).get(id)
    case _: Rebase => if (msgIdCountLookup.nonEmpty) msgIdCountLookup = msgIdCountLookup.map { case (k, v) => k -> (v - 1) }
    // read ops
    case _: Maps => sender() ! msgIdCountLookup
    case _: Print => println(msgIdCountLookup)
  }
}

class PostActor extends Actor {
  // msgId -> text
  private var msgIdTextLookup = new ConcurrentHashMap[String, String]().asScala

  def receive: PartialFunction[Any, Unit] = {
    // write ops
    case Add(id, text) => sender() ! (msgIdTextLookup += (id -> text)).get(id)
    case Delete(id) => sender() ! (msgIdTextLookup -= id).get(id)
    // read ops
    case Get(id) => sender() ! msgIdTextLookup.get(id)
    case _: All => sender() ! msgIdTextLookup.toList.map(row => (row._1, row._2))
    case _: Maps => sender() ! msgIdTextLookup
    case _: Print => println(msgIdTextLookup.toSeq.sortBy(_._2))
  }
}
