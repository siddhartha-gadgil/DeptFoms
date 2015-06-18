package shared

/**
 * @author gadgil
 */
case class Faculty(name: String, username: String) {
  val email = username + "@math.iisc.ernet.in"
  
  override def toString = name
}

object Faculty{
  val members = List(
      ("Siddhartha Gadgil", "gadgil")
      ) map ((ab) => Faculty(ab._1, ab._2))
 
  val all = members
}