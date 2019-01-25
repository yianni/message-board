// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/johngerassimou/dev/Test/message-board/conf/routes
// @DATE:Fri Jan 25 00:05:24 EST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:1
  class ReverseMessageBoardController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def getPosts(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "posts")
    }
  
    // @LINE:3
    def addPost(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "posts")
    }
  
    // @LINE:4
    def removePost(): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "posts")
    }
  
    // @LINE:1
    def vote(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "vote")
    }
  
    // @LINE:7
    def prints(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "test")
    }
  
  }


}
