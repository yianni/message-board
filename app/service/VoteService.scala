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

    private object Direction {
      val DOWN: Int = -1
      val UP: Int = 1
    }

    private object Settings {
      val REBASE_TASK_INTERVAL = 15.seconds
      val AKKA_TIMEOUT = 5.seconds
    }

    // Actor Ref
    private val voteActor = system.actorOf(Props[VoteActor])
    implicit val timeout: Timeout = Settings.AKKA_TIMEOUT

    // read ops
    system.scheduler.schedule(
      initialDelay = 0.microseconds,
      interval = Settings.REBASE_TASK_INTERVAL,
      receiver = voteActor,
      message = Rebase()
    )

    def prints(): Unit = voteActor ! Print()

    def countsById: Future[mutable.Map[String, Long]] = (voteActor ? Maps()).mapTo[mutable.Map[String, Long]]

    // write ops
    def vote(vote: Vote): Future[Option[Long]] = if (validVote(vote)) addVote(vote) else Future(Option.empty[Long])

    private def addVote(vote: Vote): Future[Option[Long]] = vote.dir match {
      case Direction.UP => (voteActor ? Increment(vote.postId)).mapTo[Option[Long]]
      case Direction.DOWN => (voteActor ? Decrement(vote.postId)).mapTo[Option[Long]]
    }

    private def validVote(vote: Vote): Boolean = vote.dir == Direction.UP || vote.dir == Direction.DOWN
  }
}