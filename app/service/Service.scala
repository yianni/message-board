package service

import com.google.inject.Inject
import etc.MessageBoardExecutionContext

import scala.concurrent.ExecutionContext

class Service @Inject()(ec: MessageBoardExecutionContext)(implicit ex: ExecutionContext, system: akka.actor.ActorSystem) extends
  VoteServiceImpl with PostServiceImpl {
  val voteService = new VoteService
  val postService = new PostService
}
