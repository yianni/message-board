package controllers

import akka.actor.ActorSystem
import controllers.Models.Msgs
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

  def prints: Action[AnyContent] = Action.async {
    service.voteService.prints()

    Future(Ok(""))
  }

  def getPosts: Action[AnyContent] = Action.async { implicit request =>
    implicit lazy val postWriter = Json.writes[Msgs]

    val merged = for {
      text <- service.postService.textById
      count <- service.voteService.countsById
    } yield {
      text.map { case (k: String, v: String) => Msgs(k, v, count.getOrElse(k, 0L)) }
    }

    merged.map(msgses => okJsonResult(msgses.toList.sortBy(-_.count)))
  }

  def addPost: Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit lazy val imcomingPostReader = Json.using[Json.WithDefaultValues].reads[IncomingPost]
    implicit lazy val imcomingPostWriter = Json.writes[IncomingPost]

    val post = validateJson[IncomingPost](request.body)
    service.postService.add(post).map(okJsonResult(_))
  }

  def removePost: Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit lazy val postReader = Json.reads[Post]
    implicit lazy val postWriter = Json.writes[Post]

    val post = validateJson[Post](request.body)
    service.postService.delete(post).map {
      case None => BadRequest
      case Some(r) => okJsonResult(r)
    }
  }

  def vote = Action.async(parse.json) {
    implicit request =>
      implicit lazy val voteReader = Json.using[Json.WithDefaultValues].reads[Vote]
      implicit lazy val voteWriter = Json.writes[Vote]

      val vote = validateJson[Vote](request.body)

      for {
        opt <- service.postService.get(vote.postId)
        str <- if (opt.isDefined) service.voteService.vote(vote) else Future("bad")
      } yield {
        if (str == "bad") BadRequest("") else okJsonResult(str)
      }
  }
}

