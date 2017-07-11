package schedule

/**
  * @author gadgil
  */
case class Faculty(name: String, username: String) {
  val email = username + "@math.iisc.ernet.in"

  override def toString = name

  def course(implicit courses: List[Course]) =
    courses.find(_.name == name).map(_.id).get
}

object Faculty {
  val members = List(
      ("Arvind Ayyer", "arvind"),
      ("Abhishek Banerjee", "abhishek"),
      ("Gautam Bharali", "bharali"),
      ("Tirthankar Bhattacharya", "tirtha"),
      ("Soumya Das", "somu"),
      ("Basudeb Datta", "dattab"),
      ("Siddhartha Gadgil", "gadgil"),
      ("Mrinal K. Ghosh", "mkg"),
      ("Thirupathi Gudi", "gudi"),
      ("Subhojoy Gupta", "subhojoy"),
      ("Srikanth K. Iyer", "skiyer"),
      ("Apoorva Khare", "khare"),
      ("Manjunath Krishnapur", "manju"),
      ("Gadadhar Misra", "gm"),
      ("A. K. Nandakumaran", "nands"),
      ("E. K. Narayanan", "naru"),
      ("Dilip P. Patil", "patil"),
      ("Vamsi Pingali", "vamsipingali"),
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
    ) map ((ab) => Faculty(ab._1, ab._2))

  val krishna = Faculty("Krishna Madali", "krishna")

  val janaki = Faculty("Janaki Balakrishnan", "janaki")

  val rukmini = Faculty("Rukmini Dey", "rukmini")

  val visiting = List(
    krishna,
    rukmini
  )

  val all = members ++ inspire ++ visiting

  val List(arvind,
           abhishek,
           bharali,
           tirtha,
           somu,
           dattab,
           gadgil,
           mkg,
           gudi,
           subhojoy) = members.take(10)
  val List(skiyer,
           khare,
           manju,
           gm,
           nands,
           naru,
           patil,
           vamsi,
           rangarajan,
           harish,
           pooja,
           veluma,
           kverma) = members.drop(10)

  val List(jaban, mousumi, umesh) = inspire
}
