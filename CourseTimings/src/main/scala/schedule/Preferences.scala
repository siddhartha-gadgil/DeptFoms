package schedule

import Timing._

import Faculty._

import scala.util._

case class Preferences(person: Faculty, prefs: List[Timing]){
  def weight(t: Timing) = prefs.zipWithIndex.find(_._1 == t) map (_._2)

  lazy val maxWeight = (prefs map (weight)).max

  def maxWeightCount = (prefs filter ((x) => weight(x) == maxWeight)).size

  def get(n: Int) = Try(prefs(n)).toOption

  def firstOpt = prefs.headOption

  def take(n: Int) = prefs take (n)

  def firstPair = firstOpt map ((person -> _))
}

object Preferences{
  val revealed = List(
    (arvind, List(t200, t1100, m11)),
    (somu, List(t1100, t330, m10)),
    (subhojoy, List(m11, t330, t1100)),
    (gudi, List(t330, t1100, m3)),
    (krishna, List(t330, t1100, m11)),
    (bharali, List(m11, m2, t200)),
    (patil, List(t930, t1100, m10)),
    (nands, List(t930, t1100, m9)),
    (naru, List(m9, m10, m11, m12, t930, t1100)),
    (mousumi, List(t1100, t200, m11)),
    (umesh, List(m2, m3, t330)),
    (gm, List(t330, m3, t200))
  ) map {case (f, l) => Preferences(f, l)}

  def get(fac: Faculty) = revealed find(_.person == fac)

  def all = (Faculty.all map (get)).flatten

  implicit val  instructors : List[Faculty]
    = revealed map (_.person)
}
