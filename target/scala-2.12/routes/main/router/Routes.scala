// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/johngerassimou/dev/Test/message-board/conf/routes
// @DATE:Fri Jan 25 00:05:24 EST 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  MessageBoardController_0: controllers.MessageBoardController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    MessageBoardController_0: controllers.MessageBoardController
  ) = this(errorHandler, MessageBoardController_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, MessageBoardController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """vote""", """controllers.MessageBoardController.vote"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts""", """controllers.MessageBoardController.getPosts"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts""", """controllers.MessageBoardController.addPost"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts""", """controllers.MessageBoardController.removePost"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """test""", """controllers.MessageBoardController.prints"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_MessageBoardController_vote0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vote")))
  )
  private[this] lazy val controllers_MessageBoardController_vote0_invoker = createInvoker(
    MessageBoardController_0.vote,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MessageBoardController",
      "vote",
      Nil,
      "POST",
      this.prefix + """vote""",
      """""",
      Seq()
    )
  )

  // @LINE:2
  private[this] lazy val controllers_MessageBoardController_getPosts1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts")))
  )
  private[this] lazy val controllers_MessageBoardController_getPosts1_invoker = createInvoker(
    MessageBoardController_0.getPosts,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MessageBoardController",
      "getPosts",
      Nil,
      "GET",
      this.prefix + """posts""",
      """""",
      Seq()
    )
  )

  // @LINE:3
  private[this] lazy val controllers_MessageBoardController_addPost2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts")))
  )
  private[this] lazy val controllers_MessageBoardController_addPost2_invoker = createInvoker(
    MessageBoardController_0.addPost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MessageBoardController",
      "addPost",
      Nil,
      "POST",
      this.prefix + """posts""",
      """""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_MessageBoardController_removePost3_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts")))
  )
  private[this] lazy val controllers_MessageBoardController_removePost3_invoker = createInvoker(
    MessageBoardController_0.removePost,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MessageBoardController",
      "removePost",
      Nil,
      "DELETE",
      this.prefix + """posts""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_MessageBoardController_prints4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("test")))
  )
  private[this] lazy val controllers_MessageBoardController_prints4_invoker = createInvoker(
    MessageBoardController_0.prints,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MessageBoardController",
      "prints",
      Nil,
      "GET",
      this.prefix + """test""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_MessageBoardController_vote0_route(params@_) =>
      call { 
        controllers_MessageBoardController_vote0_invoker.call(MessageBoardController_0.vote)
      }
  
    // @LINE:2
    case controllers_MessageBoardController_getPosts1_route(params@_) =>
      call { 
        controllers_MessageBoardController_getPosts1_invoker.call(MessageBoardController_0.getPosts)
      }
  
    // @LINE:3
    case controllers_MessageBoardController_addPost2_route(params@_) =>
      call { 
        controllers_MessageBoardController_addPost2_invoker.call(MessageBoardController_0.addPost)
      }
  
    // @LINE:4
    case controllers_MessageBoardController_removePost3_route(params@_) =>
      call { 
        controllers_MessageBoardController_removePost3_invoker.call(MessageBoardController_0.removePost)
      }
  
    // @LINE:7
    case controllers_MessageBoardController_prints4_route(params@_) =>
      call { 
        controllers_MessageBoardController_prints4_invoker.call(MessageBoardController_0.prints)
      }
  }
}
