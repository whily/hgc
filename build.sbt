organization := "net.whily"

name := "hgc"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.11"

scalacOptions ++= Seq("-optimize", "-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "net.whily" %% "chinesecalendar" % "0.2.1-SNAPSHOT"

libraryDependencies += "net.whily" %% "scasci" % "0.0.1-SNAPSHOT"
