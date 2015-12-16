package schedule

import Faculty._

object FindSchedule {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(101); 
  println("Welcome to the Scala worksheet");$skip(28); 
  
  val s = Schedule.first;System.out.println("""s  : schedule.Schedule = """ + $show(s ));$skip(24); val res$0 = 

	Preferences.get(somu);System.out.println("""res0: Option[schedule.Preferences] = """ + $show(res$0));$skip(16); val res$1 = 
	
	s.map.keySet;System.out.println("""res1: scala.collection.immutable.Set[schedule.Faculty] = """ + $show(res$1));$skip(18); val res$2 = 
	
	s.notFirstPref;System.out.println("""res2: scala.collection.immutable.Map[schedule.Faculty,Option[Int]] = """ + $show(res$2));$skip(59); 

	val teachers = (Course.jan2016 map (_.instructor)).toSet;System.out.println("""teachers  : scala.collection.immutable.Set[schedule.Faculty] = """ + $show(teachers ));$skip(27); val res$3 = 

	teachers -- s.map.keySet;System.out.println("""res3: scala.collection.immutable.Set[schedule.Faculty] = """ + $show(res$3));$skip(12); val res$4 = 

	s.clashes;System.out.println("""res4: List[(schedule.Course, schedule.Course)] = """ + $show(res$4));$skip(18); val res$5 = 
	
	s.clashes.size;System.out.println("""res5: Int = """ + $show(res$5));$skip(16); val res$6 = 
	
	
	s.clashSet;System.out.println("""res6: scala.collection.immutable.Map[schedule.Timing,List[schedule.Course]] = """ + $show(res$6))}
}
