package schedule

import Course.{jan2016 => _, _}

case class TimingPreference(course: Course, choices: List[(Int, Set[Timing])]) {
  def choicesBelow(k: Int) = choices filter (_._1 <= k)
}

object TimingPreference {
  def apply(fac: Faculty, ordered: Timing*)(
      implicit courses: List[Course]): TimingPreference = {
    val course = getOpt(fac).get
    val choices = ordered.toList.zipWithIndex.map {
      case (t, n) => (n + 1, Set(t))
    }
    TimingPreference(course, choices)
  }

  def sets(fac: Faculty, choices: (Int, Traversable[Timing])*)(
      implicit courses: List[Course]): TimingPreference =
    TimingPreference(getOpt(fac).get, choices.toList map {
      case (n, t) => (n, t.toSet)
    })

}

class Scheduler(
    prefs: List[TimingPreference],
    clashToAvoid: (Course, Course) => Boolean)(implicit cs: List[Course]) {
  val courses = prefs map (_.course)

  def prefWeight(c: Course, t: Timing): Option[Int] =
    prefs find (_.course == c) flatMap ((p) =>
                                          p.choices find (_._2 contains t) map (_._1))

  def worstCase(t: Map[Course, Timing]) =
    (courses map ((c) => prefWeight(c, t(c)))).flatten.max

  def badChoices(t: Map[Course, Timing], rank: Int) =
    (courses filter ((c) => prefWeight(c, t(c)) == Some(rank)))

  def costPair(t: Map[Course, Timing]) = {
    val rank = worstCase(t)
    (rank, badChoices(t, rank).size)
  }

  def clashes(t: Map[Course, Timing]) = {
    for ((c1, t1) <- t.toList; (c2, t2) <- t.toList
         if c1.id < c2.id && t1 == t2) yield (c1, c2)
  }

  def noBannedClashes(t: Map[Course, Timing]) =
    !((clashes(t) map { case (t1, t2) => clashToAvoid(t1, t2) })
      .contains(true))

  def recAllSchedules(ps: List[TimingPreference]): List[Map[Course, Timing]] =
    ps match {
      case List() => List(Map())
      case a :: bs =>
        val ts = a.choices.map(_._2).flatten.toSet
        val heads = (ts map ((t) => (a.course, t))).toList
        val tails = recAllSchedules(bs)
        (for (head <- heads; tail <- tails)
          yield (tail + head)).filter(noBannedClashes)
    }

  def iterSchedules(
      bound: Int,
      ps: List[TimingPreference] = prefs): Iterator[Map[Course, Timing]] =
    ps match {
      case List() => List(Map[Course, Timing]()).toIterator
      case a :: bs =>
        val ts = a.choicesBelow(bound).map(_._2).flatten.toSet
        val heads = (ts map ((t) => (a.course, t)))
        val tails = iterSchedules(bound, bs)
        (tails flatMap { (tail) =>
          heads map ((head) => tail + head)
        }).filter(noBannedClashes)
    }

  def recLowBoundSchedules(bound: Int = 1, ps: List[TimingPreference] = prefs)
    : (Int, Iterator[Map[Course, Timing]]) = {
    val iter = iterSchedules(bound, ps)
    if (!iter.isEmpty) (bound, iter) else recLowBoundSchedules(bound + 1, ps)
  }

  lazy val allSchedules = recAllSchedules(prefs)

  lazy val groupedSchedules =
    allSchedules.groupBy(worstCase).map {
      case (rank, group) =>
        (rank, group.groupBy((s) => badChoices(s, rank).size))
    }

  lazy val bestKeys = {
    val head = groupedSchedules.keys.min
    val tail = groupedSchedules(head).keys.min
    (head, tail)
  }

  lazy val bestSchedulesCrude = groupedSchedules(bestKeys._1)(bestKeys._2)

  lazy val bestSchedules = {
    val (rank, iter) = recLowBoundSchedules()
    val vec = iter.toVector
    val min = vec.map((s) => badChoices(s, rank).size).min
    (rank, min, vec.filter(badChoices(_, rank).size == min))
  }

}
