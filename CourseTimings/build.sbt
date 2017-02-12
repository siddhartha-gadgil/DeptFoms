lazy val scalaV = "2.12.1"

val initCommands = """import schedule._; import Aug2016Scheduler._; import Aug2016._"""

lazy val root = (project in file(".")).
  settings(
    name := "Course-Timings",
    version := "1.0",
    scalaVersion := scalaV,
    libraryDependencies += "com.lihaoyi" % "ammonite" % "0.8.1" % "test"  cross CrossVersion.full,
    initialCommands in (Test, console) := """ammonite.Main().run()"""
  )
