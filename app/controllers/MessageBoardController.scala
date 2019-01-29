package controllers

import akka.actor.ActorSystem
import controllers.Models.{IdResult, PostResult, VoteResult}
import http.Responses.okJsonResult
import http.Validate.validateJson
import javax.inject.Inject
import play.api.libs.json._
import play.api.mvc._
import service._

import scala.concurrent.{ExecutionContext, Future}

//@Singleton
class MessageBoardController @Inject()(
  service: Service,
  system: ActorSystem,
  cc: ControllerComponents,
  implicit val ec: ExecutionContext
) extends AbstractController(cc) {

  implicit lazy val postReader = Json.reads[Post]
  implicit lazy val idResultWriter = Json.writes[IdResult]
  implicit lazy val imcomingPostReader = Json.using[Json.WithDefaultValues].reads[IncomingPost]
  implicit lazy val imcomingPostWriter = Json.writes[IdResult]
  implicit lazy val voteReader = Json.using[Json.WithDefaultValues].reads[Vote]
  implicit lazy val voteWriter = Json.writes[VoteResult]
  implicit lazy val postResultWriter = Json.writes[PostResult]

  // Write ops
  def addPost: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val post = validateJson[IncomingPost](request.body)

    service.postService.add(post).map(res => if (res.isDefined) okJsonResult(IdResult(post.id)) else InternalServerError)
  }

  def removePost: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val post = validateJson[Post](request.body)

    for {
      text <- service.postService.get(post.id)
      textFromDelete <- if (text.isDefined) service.postService.delete(post) else Future(Some(""))
    } yield {
      if (textFromDelete.isEmpty) okJsonResult(IdResult(post.id)) else BadRequest
    }
  }

  def vote: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val vote = validateJson[Vote](request.body)

    for {
      text <- service.postService.get(vote.postId)
      count <- if (text.isDefined) service.voteService.vote(vote) else Future(Option.empty[Long])
    } yield {
      if (count.isEmpty || text.isEmpty) BadRequest else okJsonResult(VoteResult(vote.postId, count.get))
    }
  }

  // Read ops
  def prints: Action[AnyContent] = Action.async {
    // debug
    service.voteService.prints()
    Future(Ok)
  }

  def getPosts: Action[AnyContent] = Action.async { request =>
    val merged = for {
      text <- service.postService.textById
      count <- service.voteService.countsById
    } yield {
      text.map { case (k, v) => PostResult(k, v, count.getOrElse(k, 0L)) }
    }

    merged.map(msgs => okJsonResult(msgs.toList.sortBy(-_.count)))
  }
}

