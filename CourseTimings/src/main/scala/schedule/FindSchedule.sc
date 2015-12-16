package schedule

import Faculty._

object FindSchedule {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val s = Schedule.first                          //> s  : schedule.Schedule = Schedule(Map(Thirupathi Gudi -> Timing(Tuesday, Thu
                                                  //| rsday,3:30  -  5:00), E. K. Narayanan -> Timing(Monday, Wedesday, Friday,9:0
                                                  //| 0  - 10:00), Gautam Bharali -> Timing(Monday, Wedesday, Friday,11:00 - 12:00
                                                  //| ), Subhojoy Gupta -> Timing(Monday, Wedesday, Friday,11:00 - 12:00), A. K. N
                                                  //| andakumaran -> Timing(Tuesday, Thursday,9:30  - 11:00), Umesh Dubey -> Timin
                                                  //| g(Monday, Wedesday, Friday,2:00  -  3:00), Sowmya Das -> Timing(Tuesday, Thu
                                                  //| rsday,11:00 - 12:30), Mousumi Mandal -> Timing(Tuesday, Thursday,11:00 - 12:
                                                  //| 30), Dilip P. Patil -> Timing(Tuesday, Thursday,9:30  - 11:00), Krishna Mada
                                                  //| li -> Timing(Tuesday, Thursday,3:30  -  5:00)))

	Preferences.get(somu)                     //> res0: Option[schedule.Preferences] = Some(Preferences(Sowmya Das,List(Timing
                                                  //| (Tuesday, Thursday,11:00 - 12:30), Timing(Tuesday, Thursday,3:30  -  5:00), 
                                                  //| Timing(Monday, Wedesday, Friday,10:00 - 11:00))))
	
	s.map.keySet                              //> res1: scala.collection.immutable.Set[schedule.Faculty] = Set(Thirupathi Gudi
                                                  //| , E. K. Narayanan, Gautam Bharali, Subhojoy Gupta, A. K. Nandakumaran, Umesh
                                                  //|  Dubey, Sowmya Das, Mousumi Mandal, Dilip P. Patil, Krishna Madali)
	
	s.notFirstPref                            //> res2: scala.collection.immutable.Map[schedule.Faculty,Option[Int]] = Map()

	val teachers = (Course.jan2016 map (_.instructor)).toSet
                                                  //> teachers  : scala.collection.immutable.Set[schedule.Faculty] = Set(Thirupath
                                                  //| i Gudi, Gadadhar Misra, E. K. Narayanan, Gautam Bharali, Arvind Ayyer, Subho
                                                  //| joy Gupta, S. Thangavelu, A. K. Nandakumaran, Umesh Dubey, Sowmya Das, Mousu
                                                  //| mi Mandal, Dilip P. Patil, Krishna Madali)

	teachers -- s.map.keySet                  //> res3: scala.collection.immutable.Set[schedule.Faculty] = Set(Gadadhar Misra,
                                                  //|  Arvind Ayyer, S. Thangavelu)

	s.clashes                                 //> res4: List[(schedule.Course, schedule.Course)] = List((Ma 241 : ODE (Instruc
                                                  //| tor: Thirupathi Gudi),Ma 369 : Quantum Mechanics (Instructor: Krishna Madali
                                                  //| )), (Ma 224 : Complex Analysis (Instructor: Gautam Bharali),Ma 332 : Algebra
                                                  //| ic Topology (Instructor: Subhojoy Gupta)), (Ma 222 : Analysis II (Instructor
                                                  //| : A. K. Nandakumaran),Ma 313 : Algebraic Number Theory (Instructor: Dilip P.
                                                  //|  Patil)), (Ma 213 : Algebra II (Instructor: Mousumi Mandal),Ma 317 : Intro t
                                                  //| o Analytical Number Theory (Instructor: Sowmya Das)))
	
	s.clashes.size                            //> res5: Int = 4
	
	
	s.clashSet                                //> res6: scala.collection.immutable.Map[schedule.Timing,List[schedule.Course]] 
                                                  //| = Map(Timing(Monday, Wedesday, Friday,11:00 - 12:00) -> List(Ma 224 : Comple
                                                  //| x Analysis (Instructor: Gautam Bharali), Ma 332 : Algebraic Topology (Instru
                                                  //| ctor: Subhojoy Gupta)), Timing(Tuesday, Thursday,11:00 - 12:30) -> List(Ma 3
                                                  //| 17 : Intro to Analytical Number Theory (Instructor: Sowmya Das), Ma 213 : Al
                                                  //| gebra II (Instructor: Mousumi Mandal)), Timing(Tuesday, Thursday,3:30  -  5:
                                                  //| 00) -> List(Ma 241 : ODE (Instructor: Thirupathi Gudi), Ma 369 : Quantum Mec
                                                  //| hanics (Instructor: Krishna Madali)), Timing(Tuesday, Thursday,9:30  - 11:00
                                                  //| ) -> List(Ma 222 : Analysis II (Instructor: A. K. Nandakumaran), Ma 313 : Al
                                                  //| gebraic Number Theory (Instructor: Dilip P. Patil)))
}