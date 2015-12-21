package schedule

import Faculty._

import Jan2016._

object FindSchedule {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val rawbase = level(1, 1, 4)                    //> rawbase  : List[schedule.Schedule] = List(Schedule(Map(Thirupathi Gudi -> Ti
                                                  //| ming(Tuesday, Thursday,3:30  -  5:00), Gadadhar Misra -> Timing(Monday, Wede
                                                  //| sday, Friday,3:00  -  4:00), E. K. Narayanan -> Timing(Monday, Wedesday, Fri
                                                  //| day,9:00  - 10:00), Gautam Bharali -> Timing(Monday, Wedesday, Friday,11:00 
                                                  //| - 12:00), Arvind Ayyer -> Timing(Tuesday, Thursday,2:00  -  3:30), Subhojoy 
                                                  //| Gupta -> Timing(Monday, Wedesday, Friday,11:00 - 12:00), A. K. Nandakumaran 
                                                  //| -> Timing(Tuesday, Thursday,9:30  - 11:00), Umesh Dubey -> Timing(Monday, We
                                                  //| desday, Friday,2:00  -  3:00), Sowmya Das -> Timing(Tuesday, Thursday,11:00 
                                                  //| - 12:30), Mousumi Mandal -> Timing(Tuesday, Thursday,11:00 - 12:30), Dilip P
                                                  //| . Patil -> Timing(Tuesday, Thursday,9:30  - 11:00), Krishna Madali -> Timing
                                                  //| (Tuesday, Thursday,3:30  -  5:00))))
   
   rawbase.size                                   //> res0: Int = 1

	val better = level(1, 1, 3)               //> better  : List[schedule.Schedule] = List()

	val base = rawbase filter (clashesToAvoid(_).isEmpty)
                                                  //> base  : List[schedule.Schedule] = List(Schedule(Map(Thirupathi Gudi -> Timin
                                                  //| g(Tuesday, Thursday,3:30  -  5:00), Gadadhar Misra -> Timing(Monday, Wedesda
                                                  //| y, Friday,3:00  -  4:00), E. K. Narayanan -> Timing(Monday, Wedesday, Friday
                                                  //| ,9:00  - 10:00), Gautam Bharali -> Timing(Monday, Wedesday, Friday,11:00 - 1
                                                  //| 2:00), Arvind Ayyer -> Timing(Tuesday, Thursday,2:00  -  3:30), Subhojoy Gup
                                                  //| ta -> Timing(Monday, Wedesday, Friday,11:00 - 12:00), A. K. Nandakumaran -> 
                                                  //| Timing(Tuesday, Thursday,9:30  - 11:00), Umesh Dubey -> Timing(Monday, Wedes
                                                  //| day, Friday,2:00  -  3:00), Sowmya Das -> Timing(Tuesday, Thursday,11:00 - 1
                                                  //| 2:30), Mousumi Mandal -> Timing(Tuesday, Thursday,11:00 - 12:30), Dilip P. P
                                                  //| atil -> Timing(Tuesday, Thursday,9:30  - 11:00), Krishna Madali -> Timing(Tu
                                                  //| esday, Thursday,3:30  -  5:00))))

	base.size                                 //> res1: Int = 1
	
	val c = base.head                         //> c  : schedule.Schedule = Schedule(Map(Thirupathi Gudi -> Timing(Tuesday, Thu
                                                  //| rsday,3:30  -  5:00), Gadadhar Misra -> Timing(Monday, Wedesday, Friday,3:00
                                                  //|   -  4:00), E. K. Narayanan -> Timing(Monday, Wedesday, Friday,9:00  - 10:00
                                                  //| ), Gautam Bharali -> Timing(Monday, Wedesday, Friday,11:00 - 12:00), Arvind 
                                                  //| Ayyer -> Timing(Tuesday, Thursday,2:00  -  3:30), Subhojoy Gupta -> Timing(M
                                                  //| onday, Wedesday, Friday,11:00 - 12:00), A. K. Nandakumaran -> Timing(Tuesday
                                                  //| , Thursday,9:30  - 11:00), Umesh Dubey -> Timing(Monday, Wedesday, Friday,2:
                                                  //| 00  -  3:00), Sowmya Das -> Timing(Tuesday, Thursday,11:00 - 12:30), Mousumi
                                                  //|  Mandal -> Timing(Tuesday, Thursday,11:00 - 12:30), Dilip P. Patil -> Timing
                                                  //| (Tuesday, Thursday,9:30  - 11:00), Krishna Madali -> Timing(Tuesday, Thursda
                                                  //| y,3:30  -  5:00)))
                                                  
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
                                                  //| ), Timing(Tuesday, Thursday,11:00 - 12:30), Timing(Monday, Wedesday, Friday,
                                                  //| 11:00 - 12:00), Timing(Tuesday, Thursday,3:30  -  5:00), Timing(Tuesday, Thu
                                                  //| rsday,3:30  -  5:00), Timing(Monday, Wedesday, Friday,11:00 - 12:00), Timing
                                                  //| (Tuesday, Thursday,9:30  - 11:00), Timing(Tuesday, Thursday,9:30  - 11:00), 
                                                  //| Timing(Monday, Wedesday, Friday,9:00  - 10:00), Timing(Tuesday, Thursday,11:
                                                  //| 00 - 12:30), Timing(Monday, Wedesday, Friday,2:00  -  3:00), Timing(Tuesday,
                                                  //|  Thursday,3:30  -  5:00))
	
	first.size                                //> res5: Int = 12
	
	first.toSet.size                          //> res6: Int = 7
}