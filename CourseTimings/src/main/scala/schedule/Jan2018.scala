package schedule

import Course.{jan2016 => _, _}

import Timing._

import Faculty.{all => _, _}

import schedule.{TimingPreference => TP}

object Jan2018 {
  implicit val jan2017: List[Course] =
    List(
      (213, "Algebra II", abhishek),
      (222, "Measure and Integration", manju),
      (224, "Complex Analysis", harish),
      (229, "Calculus on Manifolds", dattab),
      (241, "ODE", nands),
      (315, "Lie Algebras and their Representations", rvenkat),
      (335, "Geometric Analysis", vamsi),
      (340, "Advanced Functional Analysis", tirtha),
      (386, "Coxeter Groups", arvind),
      (392, "Random Graphs and interacting particle systems", skiyer)
    ) map ((a) => Course(a._1, a._2, a._3))

  lazy val prefs =
    List(
      TP(tirtha, m10, m11, m12),
      TP(manju, t200, t330, m10, t1100),
      TP(vamsi, t200, m2, t330, m3),
      TP(dattab, m11, m10, m12),
      TP(nands, t930, t1100, m10, m3),
      TP(skiyer, t1100, t200, m11),
      TP(abhishek, t330, t200, m3),
      TP(rvenkat, t1100, t200, t330),
      TP(harish, t200, t1100, m12),
      TP.sets(arvind, 1 -> Set(t930, t1100, t200), 2 -> Set(t330), 3 -> Set(m10, m11, m2, m3))
    )

  lazy val core1: Set[Course] = Set(213, 222, 224, 229, 241) map (get)

  lazy val ug3: Set[Course] = Set(213, 222, 224, 229) map (get)

  lazy val phd: Set[Course] = Set(335) map (get)

  lazy val reps = Set(315, 386) map (get)


  def inSameSet(groups: Set[Course]*)(c1: Course, c2: Course) =
    groups
      .map((set) => ((set contains (c1)) && (set contains c2) && (c1 != c2)))
      .fold(false)(_ || _)


}

import Jan2018._

object Jan2018Scheduler extends Scheduler(prefs, inSameSet(core1, ug3, phd, reps))
