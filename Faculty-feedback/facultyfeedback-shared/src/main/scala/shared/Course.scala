package shared

import Faculty._

/**
 * @author gadgil
 */
case class Course(id: Int, name: String, instructor: Faculty) {
  override def toString = s"""Ma $id : $name (Instructor: $instructor)"""
  
  def url = s"""$id|$hashCode"""
  
  def filename = s"${Course.dir}/${instructor.username}$id"
  
  def mailcmd = s"""cat ${Course.dir + filename}.txt | mail ${instructor.username} -s "Course timining preferences" """
  
  def link = s"timing-preferences/$url"
  
  def mailfile(server: String = Course.server) = s"""
    Dear Colleague,
    
    Please give your timing preferences for the following course at the web link below:
    
    Course: Ma $id
    
    Instructor: ${instructor.name}
  
    link: $server/$link
    """
}

object Course{
  def desc(id: Int, name: String, username: String) : Course = {
    val instructor = Faculty.all.find(_.username == username).get
    Course(id, name, instructor)
  }
  
  def apply(id: Int, name: String, username: String) = desc(id, name, username)
  
  implicit val aug2015 : List[Course]  = List(
      (212,  "ALGEBRA I", "abhishek"),
      (219,   "LINEAR ALGEBRA", "harish"),
      (221,   "ANALYSIS I", "veluma"),
      (223,   "FUNCTIONAL  ANALYSIS", "tirtha"),
      (231,   "TOPOLOGY I", "gadgil"),
      (242,   "PDE", "mkg"),
      (261,   "PROBABILITY MODELS", "arvind"),
      (232,   "INTRO TO ALGEBRAIC TOPOPLOGY", "dattab"),
      (361,   "PROBABILITY THEORY", "manju"),
      (368,  "TOPICS IN PROBABILITY AND STOCHASTIC PROCESSES", "manju"), 
      (277,"NONLINEAR DYNAMICS", "gudi"), 
      (215, "INTRO. TO MODULAR FORMS", "jaban")
      ) map ((a) => Course(a._1, a._2, a._3))
      
  
  def getOpt(url: String)(implicit l : List[Course]) = (l find (_.url == url))
  
  def get(id: Int)(implicit l : List[Course]) = (l find (_.id == id)).get
  
  val dir = "mails/"
  
  val mailscript = "mails/sendmails.sh"
  
  val server = "localhost:9000"
}