organization := "net.whily"

name := "hgc"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-optimize", "-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.0" % "test"

libraryDependencies += "net.whily" %% "chinesecalendar" % "0.0.1-SNAPSHOT"
