package schedule

import Faculty._

import Jan2016._

object FindSchedule {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val rawbase = level(1, 1, 4)                    //> rawbase  : List[schedule.Schedule] = List(Schedule(Map(Thirupathi Gudi -> Ti
                                                  //| ming(Tuesday, Thursday,3:30  -  5:00), Gadadhar Misra -> Timing(Monday, Wedn
                                                  //| esday, Friday,3:00  -  4:00), E. K. Narayanan -> Timing(Monday, Wednesday, F
                                                  //| riday,9:00  - 10:00), Gautam Bharali -> Timing(Monday, Wednesday, Friday,11:
                                                  //| 00 - 12:00), Arvind Ayyer -> Timing(Tuesday, Thursday,2:00  -  3:30), Subhoj
                                                  //| oy Gupta -> Timing(Monday, Wednesday, Friday,11:00 - 12:00), A. K. Nandakuma
                                                  //| ran -> Timing(Tuesday, Thursday,9:30  - 11:00), Umesh Dubey -> Timing(Monday
                                                  //| , Wednesday, Friday,2:00  -  3:00), Sowmya Das -> Timing(Tuesday, Thursday,1
                                                  //| 1:00 - 12:30), Mousumi Mandal -> Timing(Tuesday, Thursday,11:00 - 12:30), Di
                                                  //| lip P. Patil -> Timing(Tuesday, Thursday,9:30  - 11:00), Krishna Madali -> T
                                                  //| iming(Tuesday, Thursday,3:30  -  5:00))))
   
   rawbase.size                                   //> res0: Int = 1

	val better = level(1, 1, 3)               //> better  : List[schedule.Schedule] = List()

	val base = rawbase filter (clashesToAvoid(_).isEmpty)
                                                  //> base  : List[schedule.Schedule] = List(Schedule(Map(Thirupathi Gudi -> Timin
                                                  //| g(Tuesday, Thursday,3:30  -  5:00), Gadadhar Misra -> Timing(Monday, Wednesd
                                                  //| ay, Friday,3:00  -  4:00), E. K. Narayanan -> Timing(Monday, Wednesday, Frid
                                                  //| ay,9:00  - 10:00), Gautam Bharali -> Timing(Monday, Wednesday, Friday,11:00 
                                                  //| - 12:00), Arvind Ayyer -> Timing(Tuesday, Thursday,2:00  -  3:30), Subhojoy 
                                                  //| Gupta -> Timing(Monday, Wednesday, Friday,11:00 - 12:00), A. K. Nandakumaran
                                                  //|  -> Timing(Tuesday, Thursday,9:30  - 11:00), Umesh Dubey -> Timing(Monday, W
                                                  //| ednesday, Friday,2:00  -  3:00), Sowmya Das -> Timing(Tuesday, Thursday,11:0
                                                  //| 0 - 12:30), Mousumi Mandal -> Timing(Tuesday, Thursday,11:00 - 12:30), Dilip
                                                  //|  P. Patil -> Timing(Tuesday, Thursday,9:30  - 11:00), Krishna Madali -> Timi
                                                  //| ng(Tuesday, Thursday,3:30  -  5:00))))

	base.size                                 //> res1: Int = 1
	
	val c = base.head                         //> c  : schedule.Schedule = Schedule(Map(Thirupathi Gudi -> Timing(Tuesday, Thu
                                                  //| rsday,3:30  -  5:00), Gadadhar Misra -> Timing(Monday, Wednesday, Friday,3:0
                                                  //| 0  -  4:00), E. K. Narayanan -> Timing(Monday, Wednesday, Friday,9:00  - 10:
                                                  //| 00), Gautam Bharali -> Timing(Monday, Wednesday, Friday,11:00 - 12:00), Arvi
                                                  //| nd Ayyer -> Timing(Tuesday, Thursday,2:00  -  3:30), Subhojoy Gupta -> Timin
                                                  //| g(Monday, Wednesday, Friday,11:00 - 12:00), A. K. Nandakumaran -> Timing(Tue
                                                  //| sday, Thursday,9:30  - 11:00), Umesh Dubey -> Timing(Monday, Wednesday, Frid
                                                  //| ay,2:00  -  3:00), Sowmya Das -> Timing(Tuesday, Thursday,11:00 - 12:30), Mo
                                                  //| usumi Mandal -> Timing(Tuesday, Thursday,11:00 - 12:30), Dilip P. Patil -> T
                                                  //| iming(Tuesday, Thursday,9:30  - 11:00), Krishna Madali -> Timing(Tuesday, Th
                                                  //| ursday,3:30  -  5:00)))
                                                  
  c.clashes                                       //> res2: List[(schedule.Course, schedule.Course)] = List((Ma 241 : ODE (Instruc
                                                  //| tor: Thirupathi Gudi),Ma 369 : Quantum Mechanics (Instructor: Krishna Madali
                                                  //| )), (Ma 224 : Complex Analysis (Instructor: Gautam Bharali),Ma 332 : Algebra
                                                  //| ic Topology (Instructor: Subhojoy Gupta)), (Ma 222 : Analysis II (Instructor
                                                  //| : A. K. Nandakumaran),Ma 313 : Algebraic Number Theory (Instructor: Dilip P.
                                                  //|  Patil)), (Ma 213 : Algebra II (Instructor: Mousumi Mandal),Ma 317 : Intro t
                                                  //| o Analytical Number Theory (Instructor: Sowmya Das)))
 
	clashNames(c)                             //> res3: List[(String, String)] = List((ODE,Quantum Mechanics), (Complex Analys
                                                  //| is,Algebraic Topology), (Analysis II,Algebraic Number Theory), (Algebra II,I
                                                  //| ntro to Analytical Number Theory))
                                                  
  c.notFirstPref                                  //> res4: scala.collection.immutable.Map[schedule.Faculty,Option[Int]] = Map(Gad
                                                  //| adhar Misra -> Some(1))

	val first = Preferences.revealed map (_.firstOpt.get)
                                                  //> first  : List[schedule.Timing] = List(Timing(Tuesday, Thursday,2:00  -  3:30
                                                  //| ), Timing(Tuesday, Thursday,11:00 - 12:30), Timing(Monday, Wednesday, Friday
                                                  //| ,11:00 - 12:00), Timing(Tuesday, Thursday,3:30  -  5:00), Timing(Tuesday, Th
                                                  //| ursday,3:30  -  5:00), Timing(Monday, Wednesday, Friday,11:00 - 12:00), Timi
                                                  //| ng(Tuesday, Thursday,9:30  - 11:00), Timing(Tuesday, Thursday,9:30  - 11:00)
                                                  //| , Timing(Monday, Wednesday, Friday,9:00  - 10:00), Timing(Tuesday, Thursday,
                                                  //| 11:00 - 12:30), Timing(Monday, Wednesday, Friday,2:00  -  3:00), Timing(Tues
                                                  //| day, Thursday,3:30  -  5:00))
	
	first.size                                //> res5: Int = 12
	
	first.toSet.size                          //> res6: Int = 7
	
	import java.io._
	
	val scratch = new PrintWriter("data")     //> scratch  : java.io.PrintWriter = java.io.PrintWriter@71318ec4
	
	scratch.println("# Schedule")
	
	for ((fac, timing) <- c.map) yield (scratch.println(s""""${fac.name}", "${Course.get(fac).name}", "${timing.days} ${timing.times}" """))
                                                  //> res7: scala.collection.immutable.Iterable[Unit] = List((), (), (), (), (), (
                                                  //| ), (), (), (), (), (), ())
	
	scratch.println()
	
	scratch.println("# clashes")
	
	clashNames(c).foreach(scratch.println)
	
	scratch.close
}