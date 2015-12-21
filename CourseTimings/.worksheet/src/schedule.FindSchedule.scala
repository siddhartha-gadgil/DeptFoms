package schedule

import Faculty._

import Jan2016._

object FindSchedule {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(119); 
  println("Welcome to the Scala worksheet");$skip(34); 
  
  val rawbase = level(1, 1, 4);System.out.println("""rawbase  : List[schedule.Schedule] = """ + $show(rawbase ));$skip(20); val res$0 = 
   
   rawbase.size;System.out.println("""res0: Int = """ + $show(res$0));$skip(30); 

	val better = level(1, 1, 3);System.out.println("""better  : List[schedule.Schedule] = """ + $show(better ));$skip(56); 

	val base = rawbase filter (clashesToAvoid(_).isEmpty);System.out.println("""base  : List[schedule.Schedule] = """ + $show(base ));$skip(12); val res$1 = 

	base.size;System.out.println("""res1: Int = """ + $show(res$1));$skip(21); 
	
	val c = base.head;System.out.println("""c  : schedule.Schedule = """ + $show(c ));$skip(63); val res$2 = 
                                                  
  c.clashes;System.out.println("""res2: List[(schedule.Course, schedule.Course)] = """ + $show(res$2));$skip(17); val res$3 = 
 
	clashNames(c);System.out.println("""res3: List[(String, String)] = """ + $show(res$3));$skip(68); val res$4 = 
                                                  
  c.notFirstPref;System.out.println("""res4: scala.collection.immutable.Map[schedule.Faculty,Option[Int]] = """ + $show(res$4));$skip(56); 

	val first = Preferences.revealed map (_.firstOpt.get);System.out.println("""first  : List[schedule.Timing] = """ + $show(first ));$skip(14); val res$5 = 
	
	first.size;System.out.println("""res5: Int = """ + $show(res$5));$skip(20); val res$6 = 
	
	first.toSet.size;System.out.println("""res6: Int = """ + $show(res$6))}
}
