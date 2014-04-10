import sbt._
import Keys._

object SbtBowerBuild extends Build with BuildExtra {

  lazy val sbtBower = Project("sbt-bower", file("."), settings = mainSettings)

	lazy val mainSettings: Seq[Def.Setting[_]] = Defaults.defaultSettings ++ Seq(
		sbtPlugin := true,
		name := "sbt-bower",
		organization := "com.mdedetrich",
		version := "0.2.0",
		scalacOptions ++= Seq("-deprecation", "-unchecked"),
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % "3.2.5"
    )
  )

  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (version.value.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  }
}