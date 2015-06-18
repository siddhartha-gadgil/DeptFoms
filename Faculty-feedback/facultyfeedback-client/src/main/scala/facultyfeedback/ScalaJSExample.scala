package facultyfeedback

import scala.scalajs.js
import org.scalajs.dom
import shared.SharedMessages
import dom.html
import scalajs.js.annotation.JSExport
import scalatags.JsDom._

object ScalaJSfacultyfeedback extends js.JSApp {
  def main(): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    def message = dom.document.getElementById("message").textContent

    dom.document.getElementById("jsdiv").textContent = "Hello from Scala-js: " + message

  }
}
