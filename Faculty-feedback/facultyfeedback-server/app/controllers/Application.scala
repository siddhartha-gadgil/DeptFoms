package controllers

import play.api.mvc._
import shared.SharedMessages

import shared.Course

import shared.Course._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

  def preferencesForm(id : String) = Action{
    val course = get(id)
    Ok(id)
  }
}
