
name := "logging"

scalaVersion in ThisBuild := "2.13.2"

lazy val all = project
  .in(file("."))
  .settings(settings)
  .aggregate(logging, examples)

lazy val logging = project
  .in(file("logging"))
  .settings(Seq(
    name := "logging",
    libraryDependencies ++= loggingDependencies ++ testDependencies
  ))

lazy val examples = project
  .in(file("examples"))
  .settings(Seq(
    name := "examples"
  ))
  .dependsOn(logging)

lazy val dependencies = new {
  val slf4jVersion = "1.7.30"
  val scalatestVersion = "3.2.0"

  val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
  val slf4jSimple = "org.slf4j" % "slf4j-simple" % slf4jVersion
  val scalatest = "org.scalatest" %% "scalatest" % scalatestVersion
}

lazy val testDependencies = Seq (
  dependencies.scalatest % "test"
)

lazy val loggingDependencies = Seq(
  dependencies.slf4jApi,
  dependencies.slf4jSimple
)

lazy val settings = loggingSettings 

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val loggingSettings = Seq(
  scalacOptions ++= compilerOptions
)
