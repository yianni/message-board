package http

import play.api.libs.json.{Json, Writes}
import play.api.mvc.Result
import play.api.mvc.Results.Ok

object Responses {

  def okJsonResult[A](obj: A)(implicit writes: Writes[A]): Result = Ok(Json.toJson(obj))

}
