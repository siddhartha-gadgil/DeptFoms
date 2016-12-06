package schedule

// import scala.util.Sorting

import Schedule._


case class Schedule(map: Map[Faculty, Timing]) {

  def clashes(implicit l : List[Course])
    = (for ((a, x) <- map; (b, y) <- map if x == y && Course.get(a).id < Course.get(b).id) yield (Course.get(a), Course.get(b))).toList

  def avoidClashes(implicit noClash: Set[Set[Int]], l : List[Course]) = {
    clashes(l) filter ((xy) => bothInSame(noClash)(xy._1.id, xy._2.id))
  }

  def clashSet(implicit l : List[Course]) =
    (map.toList).groupBy(_._2) filter (_._2.size >1) mapValues((l) => for ((a, _) <- l) yield Course.get(a)(Course.jan2016))

  def notFirstPref =
    for ((fac, t) <- map; pref <- Preferences.get(fac) if pref.weight(t) != Some(0)) yield (fac -> pref.weight(t))

  def at(t: Timing) = for ((fac, timing) <- map if timing == t) yield (Course.get(fac)(Course.jan2016))

  def notClashAt(first: Int, second: Int) = !((clashes(Course.jan2016) map {case (x, y) => (x.id, y.id)}) contains ((first, second)))
}



object Schedule{
  def bothInSet(s: Set[Int])(x: Int, y: Int) = (s contains x) && (s contains y)

  def bothInSame(ss: Set[Set[Int]])(x: Int, y: Int) =
    ss exists ((s) => bothInSet(s)(x, y))

  val arvindClash = for (x<- Set(222, 224, 241, 317, 332)) yield Set(x, 319)

  val core = Set(
      Set(213, 222, 224, 229, 241)
      )



  implicit val noClash = core ++ arvindClash

  def firstMap = ((Preferences.all map (_.firstPair)).flatten).toMap

  def first = Schedule(firstMap)

  def withBounds(maxWeight: Int, maxWeightCount: Int): List[Faculty] => List[Map[Faculty, Timing]] = {
    case List() =>  if (maxWeightCount ==0) List(Map.empty) else List()
    case x :: ys =>
      val prefs = Preferences.get(x).get
      val bigPrev =
        if (maxWeightCount == 1 && !ys.isEmpty)
          (for (w <- 0 to maxWeight - 1; c <- 0 to ys.size) yield (withBounds(w, c)(ys))
              ).foldLeft(List(): List[Map[Faculty, Timing]])(_++_)
        else withBounds(maxWeight, maxWeightCount -1)(ys)
      val big = for(t<- prefs.get(maxWeight).toList; big <- bigPrev) yield (big + (x-> t))
      val smallPrev =
        withBounds(maxWeight, maxWeightCount)(ys)
      val small = for (t <- prefs.take(maxWeight); sm <- smallPrev) yield sm + (x -> t)
      big ++ small
  }

  def atLevel(maxWeight: Int, maxWeightCount: Int)(implicit l: List[Faculty]) =
    (withBounds(maxWeight, maxWeightCount)(l)) map (Schedule(_))

}
