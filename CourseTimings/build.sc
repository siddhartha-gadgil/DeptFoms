import mill._, scalalib._, define.Task
import ammonite.ops._

trait MetalsModule extends ScalaModule{
  import java.io._

  def metalsBuildInfo = T{
    def targDeps : Agg[eval.PathRef] = resolveDeps(transitiveIvyDeps, false)()

    Map[String, String](
      "sources" -> allSourceFiles().map(_.path).mkString(java.io.File.pathSeparator),
      "unmanagedSourceDirectories" -> "",
      "managedSourceDirectories" -> "",
      "scalacOptions" -> scalacOptions().mkString(" "),
      "classDirectory" -> compile().classes.path.toString,
      "dependencyClasspath" ->
        (targDeps  ++
          Task.traverse(moduleDeps)(_.sources)().flatten
        ).map(_.path).mkString(java.io.File.pathSeparator),
      "scalaVersion" -> scalaVersion(),
      "sourceJars" ->
        resolveDeps(transitiveIvyDeps, true)().map(_.path).mkString(java.io.File.pathSeparator)
      )
  }

  def metalsConfig() = T.command{
    def outFile = pwd / ".metals" / "buildinfo" / RelPath(artifactName().toString) / "main.properties"
    def info = metalsBuildInfo()
    def output = info.map{
      case (k, v) => s"$k=$v"
    }.mkString("\n")
    write.over(outFile, output)
    output
  }
}

object schedule extends MetalsModule{
  def scalaVersion = "2.12.6"
}
