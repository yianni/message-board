// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/johngerassimou/dev/Test/message-board/conf/routes
// @DATE:Fri Jan 25 00:05:24 EST 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers.javascript {

  // @LINE:1
  class ReverseMessageBoardController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def getPosts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MessageBoardController.getPosts",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "posts"})
        }
      """
    )
  
    // @LINE:3
    def addPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MessageBoardController.addPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "posts"})
        }
      """
    )
  
    // @LINE:4
    def removePost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MessageBoardController.removePost",
      """
        function() {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "posts"})
        }
      """
    )
  
    // @LINE:1
    def vote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MessageBoardController.vote",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "vote"})
        }
      """
    )
  
    // @LINE:7
    def prints: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MessageBoardController.prints",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "test"})
        }
      """
    )
  
  }


}
