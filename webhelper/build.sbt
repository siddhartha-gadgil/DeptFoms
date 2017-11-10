import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++=
      Seq(
        scalaTest % Test,
        "com.typesafe.akka" %% "akka-http" % "10.0.10",
        "com.typesafe.akka" %% "akka-stream" % "2.5.4", // or whatever the latest version is
        "com.typesafe.akka" %% "akka-actor"  % "2.5.4" // or whatever the latest version is 
      )

  )
