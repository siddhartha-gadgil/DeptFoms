package controllers

import play.api.mvc._
import shared.SharedMessages

import shared.Course

import shared.Course._

import shared._

import upickle._

import models.FileIO._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

  def preferencesForm(id : String) = Action{
    val course = getOpt(id)
    val resp = (course map ((c) => Ok(views.html.coursetimings(c))))
    resp.getOrElse(NotFound("Course not found or incorrect url"))
  }
  
  def preferences = Action (parse.text){request =>
    val inp = request.body
    println(inp)
    writeln("data/pref.txt", inp)
    val pref = read[(Int, List[(Int, Timing)])](inp)
    println(pref)
    Ok(write(pref))}
}
