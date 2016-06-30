package schedule

import Course._

case class TimingPreference(course: Course, choices : List[(Int, Set[Timing])])

object TimingPreference{
    def apply(fac: Faculty, ordered : Timing*) : TimingPreference = {
      val course = getOpt(fac).get
      val choices = ordered.toList.zipWithIndex.map {
        case (t, n) => (n+1, Set(t))
      }
      TimingPreference(course, choices)
    }

    def sets(fac: Faculty, choices : (Int, Traversable[Timing])*) : TimingPreference =
      TimingPreference(getOpt(fac).get, choices.toList map {case (n, t) => (n, t.toSet)})

}

class Scheduler(prefs: List[TimingPreference], avoidClash : (Course, Course) => Boolean){
  val courses = prefs map (_.course)

  def prefWeight(c: Course, t: Timing): Option[Int] =
    prefs find (_.course == c) flatMap((p) => p.choices find (_._2 contains t) map (_._1))

  def worstCase(t: Map[Course, Timing]) = (courses map ((c) => prefWeight(c, t(c)))).flatten.max

  def badChoices(t: Map[Course, Timing], rank: Int)= (courses filter ((c) => prefWeight(c, t(c)) == Some(rank)))

  def clashes(t: Map[Course, Timing]) = {
    for ((c1, t1) <- t.toList; (c2, t2) <- t.toList if c1 != c2 && t1 == t2) yield (c1, c2)
  }

  def bannedClashes(t: Map[Course, Timing]) =
    !((clashes(t) map {case (t1, t2) => avoidClash(t1, t2)}).contains(true))

  def recAllSchedules(
    ps : List[TimingPreference]): List[Map[Course, Timing]] = ps match {
      case List() => List(Map())
      case a :: bs =>
        val ts = a.choices.map(_._2).flatten.toSet
        val heads = (ts map ((t) => (a.course, t))).toList
        val tails = recAllSchedules(bs)
        for (head<- heads; tail <- tails) yield (tail + head)
  }

  lazy val allSchedules = recAllSchedules(prefs) filter(bannedClashes)

  lazy val groupedSchedules =
    allSchedules.groupBy(worstCase).map {
      case (rank, group) => (rank, group.groupBy((s) => badChoices(s, rank).size))
    }

  lazy val bestKeys = {
    val head = groupedSchedules.keys.min
    val tail = groupedSchedules(head).keys.min
    (head, tail)
  }

  lazy val bestSchedules = groupedSchedules(bestKeys._1)(bestKeys._2)

}
