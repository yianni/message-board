package service

import actors._
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.collection.mutable
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

case class Vote(postId: String, dir: Int, time: Long = java.time.Instant.now.toEpochMilli)

trait VoteServiceImpl {
  val voteService: VoteService

  protected class VoteService(implicit ec: ExecutionContext, system: ActorSystem) {
    private val DOWN: Int = -1
    private val UP: Int = 1

    implicit private val timeout: Timeout = 5.seconds

    private val voteActor = system.actorOf(Props[VoteActor])

    def prints() = {
      voteActor ! Print()
    }

    def votes: Future[Seq[(String, Long)]] = {
      (voteActor ? All()).mapTo[Seq[(String, Long)]]
    }

    def countsById: Future[mutable.Map[String, Long]] = {
      (voteActor ? Maps()).mapTo[mutable.Map[String, Long]]
    }

    def vote(vote: Vote): Future[String] = {
      if (validVote(vote)) {
        addVote(vote).map {
          case None => "Bad"
          case Some(value) => value.toString
        }
      } else {
        Future("bad")
      }
    }

    def addVote(vote: Vote): Future[Option[Long]] = vote.dir match {
      case UP => (voteActor ? Increment(vote.postId)).mapTo[Option[Long]]
      case DOWN => (voteActor ? Decrement(vote.postId)).mapTo[Option[Long]]
    }

    private def validVote(vote: Vote): Boolean = {
      vote.dir == UP || vote.dir == DOWN
    }
  }
}