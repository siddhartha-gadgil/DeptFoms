import sbt.Project.projectToRef

lazy val clients = Seq(facultyfeedbackClient)
lazy val scalaV = "2.11.6"

lazy val facultyfeedbackServer = (project in file("facultyfeedback-server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := clients,
  pipelineStages := Seq(scalaJSProd, gzip),
  libraryDependencies ++= Seq(
    "com.vmunier" %% "play-scalajs-scripts" % "0.2.1",
    "org.webjars" % "jquery" % "1.11.1"
  ),
  // Heroku specific
  herokuAppName in Compile := "your-heroku-app-name",
  herokuSkipSubProjects in Compile := false
).enablePlugins(PlayScala).
  aggregate(clients.map(projectToRef): _*).
  dependsOn(facultyfeedbackSharedJvm)

lazy val facultyfeedbackClient = (project in file("facultyfeedback-client")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  persistLauncher in Test := false,
  sourceMapsDirectories += facultyfeedbackSharedJs.base / "..",
  unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSPlay).
  dependsOn(facultyfeedbackSharedJs)

lazy val facultyfeedbackShared = (crossProject.crossType(CrossType.Pure) in file("facultyfeedback-shared")).
  settings(scalaVersion := scalaV).
  jsConfigure(_ enablePlugins ScalaJSPlay).
  jsSettings(sourceMapsBase := baseDirectory.value / "..")

lazy val facultyfeedbackSharedJvm = facultyfeedbackShared.jvm
lazy val facultyfeedbackSharedJs = facultyfeedbackShared.js

// loads the Play project at sbt startup
onLoad in Global := (Command.process("project facultyfeedbackServer", _: State)) compose (onLoad in Global).value

// for Eclipse users
EclipseKeys.skipParents in ThisBuild := false
