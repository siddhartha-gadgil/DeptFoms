lazy val scalaV = "2.11.7"

lazy val root = (project in file(".")).
  settings(
    name := "Course-Timings",
    version := "1.0",
    scalaVersion := scalaV,
    libraryDependencies += "com.lihaoyi" % "ammonite-repl" % "0.5.2" cross CrossVersion.full,
    initialCommands in (Test, console) := """ammonite.repl.Main.run("import schedule._; import Schedule._; import Course._; import Preferences._")"""
  )
