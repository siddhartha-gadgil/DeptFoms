package schedule

object Jan2016 {
  import Course._

  implicit val v = jan2016

  //import Faculty._

  import Preferences._

  import Schedule._

  def level(maxWeight: Int, maxWeightCount: Int): List[Schedule] =
    atLevel(maxWeight, maxWeightCount)

  implicit val  noClash: Set[Set[Int]] = core ++ arvindClash

  def level(maxWeight: Int,
            maxWeightCount: Int,
            clashes: Int): List[Schedule] =
    (atLevel(maxWeight, maxWeightCount)) filter (_.clashes.size <= clashes)

  def clashValues(l: List[Schedule]) = (l map (_.clashes)).toSet.size

  def clashesToAvoid(sch: Schedule) = sch.avoidClashes

  def clashNames(sch: Schedule) = sch.clashes map {
    case (x, y) => (x.name, y.name)
  }
}
