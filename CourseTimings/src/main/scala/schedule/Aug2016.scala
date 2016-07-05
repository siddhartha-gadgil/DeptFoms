package schedule

import Course.{jan2016 => _, _}

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Aug2016{
  implicit val aug2016 : List[Course] =
    List(
        (200, "Multivariable Calculus", kverma),
      (212,  "ALGEBRA I", abhishek),
      (219,   "LINEAR ALGEBRA",umesh),
      (221,   "ANALYSIS I", naru),
      (223,   "FUNCTIONAL  ANALYSIS", veluma),
      (231,   "TOPOLOGY I", subhojoy),
      (242,   "PDE", gudi),
      (261,   "PROBABILITY MODELS", mkg),
      (232,   "INTRO TO ALGEBRAIC TOPOPLOGY", gadgil),
      (361,   "PROBABILITY THEORY", skiyer),
      (321,  "Analysis III", nands),
      (380, "Introduction to Complex Dynamics", bharali),
      (351, "Modular forms", somu),
      (340, "Advanced Functional Analysis", tirtha),
      (278, "Introduction to dynamical systems theory", janaki)
      ) map ((a) => Course(a._1, a._2, a._3))
  
  val prefs =
    List(
      TP.sets(naru, (1,  morning)),
      TP.sets(kverma, (1, Set(m11, m12, m3, t1100, t330))),
      TP(tirtha, m9, m10, m11, m12),
      TP(veluma, m10, m11, m12),
      TP(mkg, m10, m11, m12),
      TP(skiyer, t1100, t330, t930),
      TP(gudi, t1100, t930, m9),
      TP(abhishek, t330, t200, m3, m2, t1100),
      TP.sets(umesh, (1 -> Set(t200, t330)), (2 -> Set(m2, m3)), (3 -> Set(t1100, m11, m12))),
      TP(gadgil, m9, t930, m10, t1100, m11, m12)
    )

  val core1 : Set[Course] = Set(212, 219, 221, 231, 261) map (get)

  val ug3 : Set[Course] = Set(212, 219, 221, 231, 200) map (get) 
  
  val core2 : Set[Course] = Set(223, 232, 242) map (get)

  def inSameSet(groups: Set[Course]*)(c1: Course, c2: Course) = 
    groups.map ((set) => ((set contains(c1)) && (set contains c2) && (c1 != c2))).fold(false)(_||_)
  
  def avoidClash(c1: Course, c2: Course) =
    ((core1 contains(c1)) && (core1 contains c2) && (c1 != c2)) ||
    ((core2 contains(c1)) && (core2 contains c2) && (c1 != c2))

}

import Aug2016._

object Aug2016Scheduler extends Scheduler(prefs, inSameSet(core1, core2, ug3))
