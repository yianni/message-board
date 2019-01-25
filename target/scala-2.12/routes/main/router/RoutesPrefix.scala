// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/johngerassimou/dev/Test/message-board/conf/routes
// @DATE:Fri Jan 25 00:05:24 EST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
