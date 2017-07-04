package schedule

import Course.get

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Aug2017{
  implicit val aug2017 : List[Course] =
    List(
        (200, "Multivariable Calculus", gudi),
      (212,  "ALGEBRA I", abhishek),
      (213, "Representation Theory", pooja),
      (219,   "LINEAR ALGEBRA", somu),
      (221,   "ANALYSIS I", gm),
      (223,   "FUNCTIONAL  ANALYSIS", tirtha),
      (231,   "TOPOLOGY", harish),
      (242,   "PDE", veluma),
      (261,   "PROBABILITY MODELS", skiyer),
      (232,   "INTRO TO ALGEBRAIC TOPOPLOGY", subhojoy),
      (338, "Differentiable Manifolds and Lie Groups", vamsi),
      (361,   "PROBABILITY THEORY", skiyer),
      (369, "Random Matrix Theory", manju)
    ). map ((a) => Course(a._1, a._2, a._3))

  val prefs =
    List(
      TP(somu, t1100, m11, m12),
      TP(skiyer, t1100, m11, m12),
      TP(vamsi, m2, m11, m3)
    )

  val core1 : Set[Course] = Set(212, 219, 221, 231, 261) map (get)

  val ug3 : Set[Course] = Set(212, 219, 221, 231, 200) map (get)

  val core2 : Set[Course] = Set(223, 232, 242) map (get)

  // val intPhd2= Set(223, 232, 242, 361, 351) map (get)
  //
  // val advanced = Set(380, 340, 351) map (get)
  //
  // val bhar = Set(380, 361) map (get)

  def inSameSet(groups: Set[Course]*)(c1: Course, c2: Course) =
    groups.map ((set) => ((set contains(c1)) && (set contains c2) && (c1 != c2))).fold(false)(_||_)

  // def avoidClash(c1: Course, c2: Course) =
  //   ((core1 contains(c1)) && (core1 contains c2) && (c1 != c2)) ||
  //   ((core2 contains(c1)) && (core2 contains c2) && (c1 != c2))

}

import Aug2017._

object Aug2017Scheduler extends Scheduler(prefs, inSameSet(core1, core2, ug3))
