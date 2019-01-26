package controllers

object Models {
  trait HttpResult
  case class VoteResult(postId: String, count: Long) extends HttpResult
  case class PostResult(postId:String, text:String, count: Long) extends HttpResult
  case class IdResult(postId: String) extends HttpResult
}
