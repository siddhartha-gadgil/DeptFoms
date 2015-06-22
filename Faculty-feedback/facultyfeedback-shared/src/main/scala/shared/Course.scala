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
  
  implicit val aug2015 : List[Course]  = List(
      (212,  "ALGEBRA I", "ABHISHEK BANERJEE"),
      (219,   "LINEAR ALGEBRA", "HARISH SESHADRI"),
      (221,   "ANALYSIS I", "S. THANGAVELU"),
      (223,   "FUNCTIONAL  ANALYSIS", "T. BHATTACHARYYA"),
      (231,   "TOPOLOGY I", "SIDDHARTHA GADGIL"),
      (242,   "PDE", "MRINAL. K. GHOSH"),
      (261,   "PROBABILITY MODELS", "ARVIND AYYER"),
      (232,   "INTRO TO ALGEBRAIC TOPOPLOGY", "BASUDEB DATTA"),
      (361,   "PROBABILITY THEORY", "MANJUNATH KRISHNAPUR")
      ) map ((a) => Course(a._1, a._2, a._3))
      
  
  def getOpt(url: String)(implicit l : List[Course]) = (l find (_.url == url))
  
  def get(id: Int)(implicit l : List[Course]) = (l find (_.id == id)).get
}