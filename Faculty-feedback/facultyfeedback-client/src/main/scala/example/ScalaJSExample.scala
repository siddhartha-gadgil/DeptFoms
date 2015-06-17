package facultyfeedback

import scala.scalajs.js
import org.scalajs.dom
import shared.SharedMessages

object ScalaJSfacultyfeedback extends js.JSApp {
  def main(): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
  }
}
