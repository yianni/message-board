import sbt.Keys._

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.joda" % "joda-convert" % "1.9.2"
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "4.11"
libraryDependencies += "com.netaporter" %% "scala-uri" % "0.4.16"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.1"
//libraryDependencies += "com.h2database" % "h2" % "1.4.192"

// The Play project itself
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """Message board""",
  )

organization := "com.job.message"
version := "0.1"
resolvers += Resolver.typesafeRepo("releases")
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions ++= Seq(
  "-encoding",
  "UTF-8", // yes, this is 2 args
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen"
)