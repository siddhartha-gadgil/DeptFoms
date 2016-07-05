package schedule

import Faculty._

import Aug2016._

import Aug2016Scheduler._

object FindSchedule {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  bestKeys                                        //> res0: (Int, Int) = (1,10)
	
	bestSchedules.size                        //> res1: Int = 17
	
	bestSchedules.map((t) => clashes(t).size) //> res2: List[Int] = List(10, 6, 6, 14, 10, 10, 10, 10, 10, 10, 10, 6, 6, 10, 6
                                                  //| , 6, 6)|
	
}