import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  "MessageBoardController" should {

    "render the index page" in {
      val request = FakeRequest(GET, "/").withHeaders(HOST -> "localhost:9000").withCSRFToken
      val home = route(app, request).get

      contentAsString(home) must include ("This is id placeholder page to show you the REST API.")
    }

  }

}