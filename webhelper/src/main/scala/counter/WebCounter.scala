package counter

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import scala.concurrent.Future
import akka.Done

object WebServer  extends CorsSupport {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  def logVisit(s: String) =
    Future{
      println(s"Should save $s")
      Done
    }

  def main(args: Array[String]) {



    val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      } ~
        post {
          path("log-visit") {
            entity(as[String]) { order =>
              val saved: Future[Done] = logVisit(order)
              onComplete(saved) { done =>
                complete("visit logged")
              }
            }
          }
        }

    val bindingFuture = Http().bindAndHandle(corsHandler(route), "127.0.0.1", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
