package schedule

import Course._

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Aug2018 {
  implicit val aug2018: List[Course] =
    List(
      (200, "Multivariable Calculus", gudi),
      (212, "Algebra I", abhishek),
      (213, "Representation Theory", pooja),
      (219, "Linear Algebra", somu),
      (221, "Analysis I", khare),
      (223, "Functional Analysis", tirtha),
      (231, "Topology", harish),
      (242, "PDE", veluma),
      (261, "Probablity Models", manju),
      (232, "Intro to Algebraic Topology", dattab),
      (338, "Differentiable Manifolds and Lie Groups", vamsi),
      (361, "Probability Theory", skiyer),
      (369, "Random Matrix Theory", manju)
    ).map((a) => Course(a._1, a._2, a._3))

  val prefs =
    List(
      TP(somu, t1100, m11, m12),
      TP(skiyer, t1100, m11, m12),
      TP(vamsi, m2, m11, m3),
      TP(subhojoy, t330, t1100, m3),
      TP(harish, t1100, m12, m11),
      TP(veluma, m10, m11, m12),
      TP(tirtha, m10, m11, m12),
      TP.sets(khare,
              1 -> List(t1100, t200),
              2 -> List(t330, t930),
              3 -> List(m2, m3, m11)),
      TP.sets(gudi,
              1 -> List(t1100),
              2 -> List(t330),
              3 -> List(m9, m10),
              4 -> List(t930)),
      TP.sets(pooja, 1 -> List(t200, t330), 2 -> List(m2, m3)),
      TP(abhishek, t330, t200, m3)
    )

  val core1: Set[Course] = Set(212, 219, 221, 231, 261) map (get)

  val ug3: Set[Course] = Set(212, 219, 221, 231, 200) map (get)

  val core2: Set[Course] = Set(223, 232, 242) map (get)

  val intPhd2 = Set(223, 232, 242, 213) map (get)


}

import Aug2018._

object Aug2018Scheduler
    extends Scheduler(prefs, inSameSet(core1, core2, ug3, intPhd2))
