package schedule

import Course._

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Aug2018 {
  implicit val aug2018: List[Course] =
    List(
      (200, "Multivariable Calculus", kverma),
      (212, "Algebra I", pooja),
      (213, "Representation Theory", pooja),
      (219, "Linear Algebra", rvenkat),
      (221, "Analysis I", bharali),
      (223, "Functional Analysis", tirtha),
      (231, "Topology", harish),
      (242, "PDE", veluma),
      (261, "Probablity Models", gadgil),
      (232, "Intro to Algebraic Topology", dattab),
      (338, "Differentiable Manifolds and Lie Groups", vamsi),
      (361, "Probability Theory", skiyer),
      (369, "Random Matrix Theory", manju)
    ).map((a) => Course(a._1, a._2, a._3))

  val prefs =
    List(
      TP(dattab, m11, m10, m12)
    )

  val core1: Set[Course] = Set(212, 219, 221, 231, 261) map (get)

  val ug3: Set[Course] = Set(212, 219, 221, 231, 200) map (get)

  val core2: Set[Course] = Set(223, 232, 242) map (get)

  val intPhd2 = Set(223, 232, 242, 213) map (get)


}

import Aug2018._

object Aug2018Scheduler
    extends Scheduler(prefs, inSameSet(core1, core2, ug3, intPhd2))
