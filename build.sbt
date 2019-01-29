import sbt.Keys._

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.joda" % "joda-convert" % "2.2.0"
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "5.3"
libraryDependencies += "com.netaporter" %% "scala-uri" % "0.4.16"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.2"

// The Play project itself
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """Message board""",
  )

organization := "com.cool.message-board"
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