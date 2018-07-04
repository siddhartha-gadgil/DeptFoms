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
      (219, "Linear Algebra", rvenkat),
      (221, "Analysis I", bharali),
      (223, "Functional Analysis", tirtha),
      (231, "Topology", harish),
      (232, "Intro to Algebraic Topology", dattab),
      (242, "PDE", nands),
      (261, "Probablity Models", gadgil),
      (262, "Mathematical Finance I", mkg),
      (310, "Algebraic Geometry I", abhishek),
      (361, "Probability Theory", skiyer),
      (335, "Introduction to Hyperbolic Manifolds", subhojoy),
      (370, "Hermetian Analysis", gm),
      (394, "Techniques in discrete probability", riddhi)
    ).map((a) => Course(a._1, a._2, a._3))

  val prefs =
    List(
      TP(dattab, m11, m10, m12),
      TP(tirtha, m10, m11, m12),
      TP(nands, t930, t1100, m10),
      TP.sets(
        skiyer,
        1 -> List(t1100),
        2 -> List(m11),
        3 -> List(m12, m10)
      ),
      TP(abhishek, t330, t200, m3),
      TP(riddhi, t200, t1100, t330),
      TP.sets(pooja, 1 -> List(m11, m12), 2 -> List(t1100), 3 -> List(m3)),
      TP.sets(gadgil, 1 -> List(t930), 2 -> List(m9, t1100), 3 -> List(m10, m11), 4 -> List(m12)),
      TP(rvenkat, t930, m9, m8),
      TP(mkg, t330, t1100, t200),
      TP.sets(kverma, 1 -> List(m3, t330)),
      TP.sets(gm, 1 -> (all.toSet - m8)),
      TP(subhojoy, t200, t330, m2)
    )

  val core1: Set[Course] = Set(212, 219, 221, 231, 261) map (get)

  val ug3: Set[Course] = Set(212, 219, 221, 231, 200) map (get)

  val core2: Set[Course] = Set(223, 232, 242) map (get)

  val intPhd2 = Set(223, 232, 242) map (get)

  val probAdvanced = Set(262, 361, 394) map (get)


}

import Aug2018._

object Aug2018Scheduler
    extends Scheduler(prefs, inSameSet(core1, core2, ug3, intPhd2, probAdvanced))
