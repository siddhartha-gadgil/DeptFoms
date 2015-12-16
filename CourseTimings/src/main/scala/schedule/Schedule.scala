package schedule

case class Schedule(map: Map[Faculty, Timing]) {
  def clashes(implicit l : List[Course]) 
    = (for ((a, x) <- map; (b, y) <- map if x == y && Course.get(a).id < Course.get(b).id) yield (Course.get(a), Course.get(b))).toList
    
  def clashSet(implicit l : List[Course]) = 
    (map.toList).groupBy(_._2) filter (_._2.size >1) mapValues((l) => for ((a, _) <- l) yield Course.get(a))
    
  def notFirstPref =
    for ((fac, t) <- map; pref <- Preferences.get(fac) if pref.weight(t) != Some(0)) yield (fac -> pref.weight(t))
}

object Schedule{
  def firstMap = ((Preferences.all map (_.firstPair)).flatten).toMap
  
  def first = Schedule(firstMap)
}