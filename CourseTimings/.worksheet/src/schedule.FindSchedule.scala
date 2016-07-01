package schedule

import Faculty._

import Aug2016._

import Aug2016Scheduler._

object FindSchedule {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(146); 
  println("Welcome to the Scala worksheet");$skip(12); val res$0 = 

  bestKeys;System.out.println("""res0: (Int, Int) = """ + $show(res$0));$skip(22); val res$1 = 
	
	bestSchedules.size;System.out.println("""res1: Int = """ + $show(res$1));$skip(45); val res$2 = 
	
	bestSchedules.map((t) => clashes(t).size);System.out.println("""res2: List[Int] = """ + $show(res$2))}
	
}
