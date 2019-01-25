package http

import play.api.libs.json.{JsError, JsSuccess, JsValue, Reads}

object Validate {

  def validateJson[T](jsValue: JsValue)(implicit reads: Reads[T]): T = {
    jsValue.validate[T] match {
      case value: JsSuccess[T] => value.get
      case error: JsError => throw new BadRequestException(JsError.toJson(error).toString)
    }
  }

  //
  // exceptions
  class BadRequestException(message: String) extends Exception(message) {
    def this(message: String, cause: Throwable) {
      this(message)
      initCause(cause)
    }

    def this(cause: Throwable) {
      this(Option(cause).map(_.toString).orNull, cause)
    }

    def this() {
      this(null: String)
    }
  }


}
