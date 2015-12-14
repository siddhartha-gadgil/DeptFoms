package schedule

/**
 * @author gadgil
 */
case class Timing(days: String, times: String)

object Timing {
  val mwf =
    List(
//        "8:00  -  9:00",
        "9:00  - 10:00",
        "10:00 - 11:00",
        "11:00 - 12:00",
        "12:00 -  1:00",
        "2:00  -  3:00",
        "3:00  -  4:00"
      ) map (Timing("Monday, Wedesday, Friday", _))
   val tuth =
     List(
//         "8:00  -  9:30",
         "9:30  - 11:00",
         "11:00 - 12:30",
         "2:00  -  3:30",
         "3:30  -  5:00"
         ) map (Timing("Tuesday, Thursday" , _))

   val List(m9, m10, m11, m12, m2, m3) = mwf

   val List(t930, t1100, t200, t330) = tuth

   val all = mwf ++ tuth
}
