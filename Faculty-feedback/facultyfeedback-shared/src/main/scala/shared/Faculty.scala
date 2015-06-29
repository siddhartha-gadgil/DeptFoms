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
      ("Arvind Ayyer", "arvind"),
      ("Abhishek Banerjee", "abhishek"),
      ("Gautam Bharali", "bharali"),
      ("Tirthankar Bhattacharya", "tirtha"),
      ("Sowmya Das", "somu"),
      ("Basudeb Datta", "dattab"),
      ("Siddhartha Gadgil", "gadgil"),
      ("Mrinal K. Ghosh", "mkg"),
      ("Thirupathi Gudi", "gudi"),
      ("Srikanth K. Iyer", "skiyer"),
      ("Manjunath Krishnapur", "manju"),
      ("Gadadhar Misra", "gm"),
      ("A. K. Nandakumaran", "nands"),
      ("E. K. Narayanan", "naru"),
      ("Dilip P. Patil", "patil"),
      ("Govindan Rangarajan", "rangarajan"),
      ("Harish Seshadri", "harish"),
      ("Pooja Singla", "pooja"),
      ("S. Thangavelu", "veluma"),
      (" Kaushal Verma", "kverma")
      ) map ((ab) => Faculty(ab._1, ab._2))
 
  val inspire = List(
      ("Jaban Meher", "jaban"),
      ("Mousumi Mandal", "mousumi"),
      ("Umesh Dubey", "umesh")
      )map ((ab) => Faculty(ab._1, ab._2))
  
  val all = members ++ inspire
}