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

    private val AKKA_TIMEOUT: Timeout = 5.seconds

    implicit private val timeout: Timeout = AKKA_TIMEOUT

    private val voteActor = system.actorOf(Props[VoteActor])

    private val REBASE_TASK_INTERVAL = 15.seconds

    // Rebase Task
    system.scheduler.schedule(
      initialDelay = 0.microseconds,
      interval = REBASE_TASK_INTERVAL,
      receiver = voteActor,
      message = Rebase()
    )

    def prints(): Unit = { voteActor ! Print() }

    def countsById: Future[mutable.Map[String, Long]] = { (voteActor ? Maps()).mapTo[mutable.Map[String, Long]] }

    def vote(vote: Vote): Future[Option[Long]] = {
      if (validVote(vote)) {
        addVote(vote)
      } else {
        Future(Option.empty[Long])
      }
    }

    private def addVote(vote: Vote): Future[Option[Long]] = vote.dir match {
      case UP => (voteActor ? Increment(vote.postId)).mapTo[Option[Long]]
      case DOWN => (voteActor ? Decrement(vote.postId)).mapTo[Option[Long]]
    }

    private def validVote(vote: Vote): Boolean = vote.dir == UP || vote.dir == DOWN
  }
}