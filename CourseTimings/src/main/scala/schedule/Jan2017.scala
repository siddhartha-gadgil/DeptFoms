package schedule

import Course.{jan2016 => _, _}

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Jan2017{
  implicit val jan2017 : List[Course] =
    List(
    (213, "Algebra II", abhishek),
    (222, "Analysis II", bharali),
    (224, "Complex Analysis", naru),
    (229, "Calculus on Manifolds", gm),
    (241, "ODE", rangarajan),
    (313, "Algebraic Number Theory", patil),
    (326, "Fourier Analysis", veluma),
    (327, "Topics in Analysis", manju),
    (334, "Homotopy type theory", gadgil),
    (350, "Analytical Number Theory", somu),
    (332, "Algebraic Topology", subhojoy),
    (371, "Control and Homogenization", nands),
    (383, "Intro to Minimal Surfaces", rukmini),
    (390, "Percolation and Random Graphs", skiyer)
  ) map ((a) => Course(a._1, a._2, a._3))

  val prefs =
    List(
      TP(rukmini, t1100, t200),
      TP.sets(manju, 1 -> Set(t200), 2 -> Set(t930, t1100), 3 -> Set(m10)),
      TP.sets(naru, 1 ->  Set(t930, t1100)),
      TP.sets(kverma, (1, Set(m11, m12, m3, t1100, t330))),
      TP(rangarajan, t330, t200, m3),
      TP(mkg, m10, m11, m12),
      TP(skiyer, t330, t1100, t200),
      TP(gudi, t1100, t930, m9),
      TP(gadgil, m9, t930, m10, t1100, m11, m12),
      TP(veluma, t1100, t330, m10),
      TP(patil, t1100, t930, m11)
      // TP(subhojoy, m10, m11, m12),
      // TP(somu, t1100, t330, m11),
      // TP(nands, t930, t1100),
      // TP(bharali, t1100, m11, t200),
      // TP(janaki, t1100)
    )

  val core1 : Set[Course] = Set(213, 222, 224, 229, 241) map (get)

  val ug3 : Set[Course] = Set(212, 219, 221, 231, 200) map (get)

  val core2 : Set[Course] = Set(223, 232, 242) map (get)

  val intPhd2= Set(223, 232, 242, 361, 351) map (get)

  val advanced = Set(380, 340, 351) map (get)

  val bhar = Set(380, 361) map (get)

  def inSameSet(groups: Set[Course]*)(c1: Course, c2: Course) =
    groups.map ((set) => ((set contains(c1)) && (set contains c2) && (c1 != c2))).fold(false)(_||_)

  // def avoidClash(c1: Course, c2: Course) =
  //   ((core1 contains(c1)) && (core1 contains c2) && (c1 != c2)) ||
  //   ((core2 contains(c1)) && (core2 contains c2) && (c1 != c2))

}

import Jan2017._

object Jan2017Scheduler extends Scheduler(prefs, inSameSet(core1, intPhd2, ug3, advanced, bhar))
