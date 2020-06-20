package etc

import javax.inject.{Inject, Singleton}
import play.api.libs.concurrent.CustomExecutionContext

@Singleton
class MessageBoardExecutionContext @Inject()(actorSystem: akka.actor.ActorSystem)
  extends CustomExecutionContext(actorSystem, "contexts.message-board")

