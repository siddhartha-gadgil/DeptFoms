package models
import java.io._

import upickle._

import shared._

import shared.Timing

/**
 * @author gadgil
 */
object FileIO {
  def writeln(filename: String, text: String, append: Boolean = true) = {
    val fl = new FileWriter(filename, append)
    
    val writer = new BufferedWriter(fl)
    
    writer.write(text)
    
    writer.newLine()
    
    writer.close
  }
  
  import shared.Course
  
  import shared.Course._
  
  def courseMail(c: Course, server: String = Course.server) = {
    writeln(c.filename, c.mailfile(server), false)
  }
  
  def allCourseMails(server: String = Course.server)(implicit cs : List[Course]) = 
    for (c <- cs) yield courseMail(c, server)
  
  def courseScript(implicit cs : List[Course]) = {
    val fl = new FileWriter(mailscript)
    
    val writer = new BufferedWriter(fl)
    
    for (c <- cs) yield {
      writer.write(c.mailcmd)
      writer.newLine()
    }
    writer.close
  }
  
  def readFile(filename: String) = scala.io.Source.fromFile(filename).getLines
  
  def prefList = (readFile(prefFile) map (read[(Int, List[(Int, Timing)])])).toList
  
  def prefMap = prefList.toMap
}