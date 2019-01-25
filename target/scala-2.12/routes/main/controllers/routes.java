// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/johngerassimou/dev/Test/message-board/conf/routes
// @DATE:Fri Jan 25 00:05:24 EST 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseMessageBoardController MessageBoardController = new controllers.ReverseMessageBoardController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseMessageBoardController MessageBoardController = new controllers.javascript.ReverseMessageBoardController(RoutesPrefix.byNamePrefix());
  }

}
