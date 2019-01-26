package service

import actors._
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.duration._

case class IncomingPost(id: String = java.util.UUID.randomUUID.toString, text: String, time: Long = java.time.Instant.now.toEpochMilli)

case class Post(id: String)

trait PostServiceImpl {
  val postService: PostService

  protected class PostService(implicit system: ActorSystem) {
    private val postActor = system.actorOf(Props[PostActor])
    implicit private val timeout: Timeout = 5.seconds

    def add(post: IncomingPost): Future[Option[String]] = (postActor ? Add(post.id, post.text)).mapTo[Option[String]]

    def delete(post: Post): Future[Option[String]] = (postActor ? Delete(post.id)).mapTo[Option[String]]

    def get(id: String): Future[Option[String]] = (postActor ? Get(id)).mapTo[Option[String]]

    def get: Future[List[(String, String)]] = (postActor ? All()).mapTo[List[(String,String)]]

    def textById: Future[mutable.Map[String, String]] = (postActor ? Maps()).mapTo[mutable.Map[String,String]]
  }
}
