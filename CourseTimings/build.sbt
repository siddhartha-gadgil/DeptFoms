lazy val scalaV = "2.12.2"

val ammV = "1.0.0"

val initCommands =
  """import schedule._; import Aug2016Scheduler._; import Aug2016._"""

libraryDependencies += "com.lihaoyi" % "ammonite" % ammV % "test" cross CrossVersion.full

sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  val initCommands =
    """import schedule._; import Aug2016Scheduler._; import Aug2016._"""
  IO.write(
    file,
    s"""object amm extends App { ammonite.Main("$initCommands").run() }""")
  Seq(file)
}.taskValue

lazy val root = (project in file("."))
  .settings(name := "Course-Timings", version := "1.0", scalaVersion := scalaV)
