package schedule

import Faculty._

/**
 * @author gadgil
 */
case class Course(id: Int, name: String, instructor: Faculty) {
  override def toString = s"""Ma $id : $name (Instructor: $instructor)"""

  def url = s"""$id|$hashCode"""

  def filename = s"${instructor.username}$id"

  def mailcmd = s"""cat $filename | mail ${instructor.username}@math.iisc.ernet.in -s "Course timing preferences" """

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

  val aug2015 : List[Course]  = List(
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

  import Faculty._

  implicit val jan2016 : List[Course] = List(
    (213, "Algebra II", mousumi),
    (222, "Analysis II", nands),
    (224, "Complex Analysis", bharali),
    (229, "Calculus on Manifolds", gm),
    (241, "ODE", gudi),
    (320, "Representation Theory of Compact Lie Groups", veluma),
    (313, "Algebraic Number Theory", patil),
    (317, "Intro to Analytical Number Theory", somu),
    (319, "Algebraic Combinatorics", arvind),
    (315, "Lie Algebras and their Representations", naru),
    (314, "Intro to Algebraic Geometry", umesh),
    (332, "Algebraic Topology", subhojoy),
    (369, "Quantum Mechanics", krishna)
  ) map ((a) => Course(a._1, a._2, a._3))



  def getOpt(url: String)(implicit l : List[Course]) = (l find (_.url == url))

  def get(id: Int)(implicit l : List[Course]) = (l find (_.id == id)).get

  def get(fac: Faculty)(implicit l : List[Course]) = (l find (_.instructor == fac)).get
  
  val dir = "mails/"

//  def instructors(implicit l : List[Course]): List[Faculty]
//    = l map (_.instructor)
  
  val mailscript = "mails/sendmails.sh"

  val server = "localhost:9000"

  val prefFile = "data/pref.txt"
}
