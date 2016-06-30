package schedule

import Course._

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Aug2016{
  val prefs =
    List(
      TP.sets(naru, (1,  morning)),
      TP(tirtha, m9, m10, m11, m12),
      TP(veluma, m10, m11, m12),
      TP(mkg, m10, m11, m12),
      TP(gudi, t1100, t930, m9)
    )

  val core1 : Set[Course] = Set(212, 219, 221, 231, 261) map (get)

  val core2 : Set[Course] = Set(223, 232, 242) map (get)

  def avoidClash(c1: Course, c2: Course) =
    ((core1 contains(c1)) && (core1 contains c2) && (c1 != c2)) ||
    ((core2 contains(c1)) && (core2 contains c2) && (c1 != c2))

}

import Aug2016._

object Aug2016Scheduler extends Scheduler(prefs, avoidClash)
