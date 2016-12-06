lazy val scalaV = "2.11.8"

val initCommands = """import schedule._; import Aug2016Scheduler._; import Aug2016._"""

lazy val root = (project in file(".")).
  settings(
    name := "Course-Timings",
    version := "1.0",
    scalaVersion := scalaV,
    libraryDependencies += "com.lihaoyi" % "ammonite-repl" % "0.8.0" % "test"  cross CrossVersion.full,
    initialCommands in (Test, console) := """ammonite.Main().run()"""
  )
