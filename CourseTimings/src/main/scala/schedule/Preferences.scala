package schedule

import Timing._

import Faculty._

case class Preferences(person: Faculty, prefs: List[Timing])

object Preferences{
  val revealed = List(
    (somu, List(t1100, t330, m10)),
    (subhojoy, List(m11, t330, t1100)),
    (gudi, List(t330, t1100, m3)),
    (krishna, List(t330, t1100, m11)),
    (bharali, List(m11, m2, t200)),
    (patil, List(t930, t1100, m10)),
    (nands, List(t930, t1100, m9))
  ) map {case (f, l) => Preferences(f, l)}
}
