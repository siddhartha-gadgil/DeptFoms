package facultyfeedback

import scala.scalajs.js
import org.scalajs.dom
import shared._
import dom.html
import scalajs.js.annotation.JSExport
import scalatags.JsDom._
import scalatags.JsDom.all._
import scala.util.Try
import upickle._


object ScalaJSfacultyfeedback extends js.JSApp {
  def main(): Unit = {
    val page = dom.document.getElementById("page").textContent 
    
    page match {
      case "index" => shoutOut()
      case "query" => query()
    }

  }
  
  def shoutOut() = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    def message = dom.document.getElementById("message").textContent

    dom.document.getElementById("jsdiv").textContent = "Hello from Scala-js: " + message
  }
  
  def jsdiv = dom.document.getElementById("jsdiv")
  
  def update(): Unit = {
    submitButton.value = submitText
  }
  
  case class TimingChoice(t: Timing){
    val inp = input(`type` := "number", size := 4, width:= 30).render
    
    inp.onchange = (e: dom.Event) => update()
    
    inp.oninput = (e: dom.Event) => update()
    
    def choice = Try(inp.value.toInt).toOption filter (_ > 0)
    
    def choiceData = choice map ((_, t))
    
    val queryDiv = div(overflow.auto, clear.both)(
        div(float.left, width := 300)(t.days),
        div(float.left, width := 200)(t.times),
        div(float.left, width:= 6)(inp)
        )
  }
  
  val timingChoiceCases = Timing.all map (TimingChoice)
  
  val timingChoiceDiv = 
    div((timingChoiceCases map (_.queryDiv)) : _*)
  
  def allChoices = timingChoiceCases map (_.choice)
  
  def choiceData = (timingChoiceCases map (_.choiceData)).flatten
  
  def course = dom.document.getElementById("course-id").textContent.toInt
  
  def postData = write((course, choiceData))
  
  def choicesAbove(k: Int) = (allChoices.flatten.filter ( _ <= k)).size
  
  def enoughChoices = 
    (choicesAbove(1) > 0) &&
    (choicesAbove(2) > 1) &&
    (choicesAbove(3) > 2)
  
  def submitText = 
    if (enoughChoices) "Submit (more preferences will be appreciated)" 
    else "More preferences please"
  
  val submitButton = input(`type` := "submit", value := "More preferences please").render    
  
  import dom.ext._
  
  import scala.scalajs
                .concurrent
                .JSExecutionContext
                .Implicits
                .runNow
  
  submitButton.onclick = (e : dom.Event) => {
    update()
    if (enoughChoices) Ajax.post("/preferences", postData).onSuccess{
      case xhr =>
        {
          jsdiv.innerHTML = """<h2> Thank you for your feedback</h2>"""
        }
    }
  }
  
  val queryDiv = div(timingChoiceDiv, div(clear.both)(p("")), div(clear.both)(submitButton)).render
  
  def query() = {
    jsdiv.appendChild(queryDiv)
  }
}

