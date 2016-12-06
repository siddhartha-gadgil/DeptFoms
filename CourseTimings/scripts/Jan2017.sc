import schedule._
import Jan2017Scheduler._
val best = iterSchedules(2).toVector filter (badChoices(_, 2).size == 2)
val cl = best map (clashes(_)).flatten.toSet
