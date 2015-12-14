lazy val scalaV = "2.11.7"

lazy val root = (project in file(".")).
  settings(
    name := "Course-Timings",
    version := "1.0",
    scalaVersion := scalaV
  )
