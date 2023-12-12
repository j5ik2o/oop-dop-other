ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "oop-dop-other",
    scalacOptions ++=
      Seq(
        "-feature",
        "_",
        "-deprecation",
        "-unchecked",
        "-encoding",
        "UTF-8",
        "-language:_"
      ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
      "org.scalacheck" %% "scalacheck" % "1.15.4" % Test
    )
  )
