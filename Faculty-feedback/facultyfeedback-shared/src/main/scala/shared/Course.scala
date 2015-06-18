package shared

import Faculty._

/**
 * @author gadgil
 */
case class Course(id: Int, name: String, instructor: Faculty) {
  override def toString = s"""Ma $id : name (Instructor: $instructor)"""
  
  def url = s"""$id|$hashCode"""
}

object Course{
  def desc(id: Int, name: String, username: String) : Course = {
    val instructor = Faculty.all.find(_.username == username).get
    Course(id, name, instructor)
  }
  
  def apply(id: Int, name: String, username: String) = desc(id, name, username)
  
  implicit val aug2015 : List[Course] = ???
  
  def get(url: String)(implicit l : List[Course]) = (l find (_.url == url)).get
  
  def get(id: Int)(implicit l : List[Course]) = (l find (_.id == id)).get
}