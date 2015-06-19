package shared

/**
 * @author gadgil
 */
case class Timing(days: String, times: String)

object Timing {
  val mwf = 
    List(
        "8:00 - 9:00",
        "9:00 - 10:00",
        "10:00 - 11:00",
        "11:00 - 12:00",
        "12:00 - 1:00",
        "2:00 - 3:00",
        "3:00 - 4:00"
      ) map (Timing("Monday, Wedesday, Friday", _))
   val tuth = 
     List(
         "8:00 - 9:30",
         "9:30 - 11:00",
         "11:00 - 12:30",
         "2:00 - 3:30",
         "3:30 - 5:00"
         ) map (Timing("Tuesday, Thursday" , _))
   
   val all = mwf ++ tuth
}