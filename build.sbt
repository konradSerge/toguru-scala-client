ThisBuild / organization := "com.autoscout24"
ThisBuild / licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
ThisBuild / bintrayOrganization := Some("autoscout24")

ThisBuild / gitVersioningSnapshotLowerBound := "3.0.0"

ThisBuild / resolvers ++= Seq(
  Resolver.jcenterRepo,
  Resolver.bintrayRepo("autoscout24", "maven"),
  "Typesafe repository".at("https://repo.typesafe.com/typesafe/releases/")
)

ThisBuild / scoverage.ScoverageKeys.coverageMinimum := 80
ThisBuild / scoverage.ScoverageKeys.coverageFailOnMinimum := true

addCommandAlias("format", "; scalafmt; test:scalafmt; scalafmtSbt")
addCommandAlias("formatCheck", "; scalafmtCheck; test:scalafmtCheck; scalafmtSbtCheck")

lazy val root = project
  .in(file("."))
  .aggregate(core.projectRefs: _*)
  .settings(publish / skip := true)

lazy val core = projectMatrix
  .in(file("core"))
  .enablePlugins(SemVerPlugin)
  .settings(
    name := "toguru-scala-client",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xfatal-warnings",
      "-Yno-adapted-args",
      "-Xmax-classfile-name",
      "130"
    ),
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core"                        % "2.0.0-RC6",
      "org.slf4j"                    % "slf4j-api"                    % "1.7.30",
      "org.komamitsu"                % "phi-accural-failure-detector" % "0.0.5",
      "net.jodah"                    % "failsafe"                     % "2.3.1",
      "org.mockito"                  %% "mockito-scala-scalatest"     % "1.10.0" % "test",
      "org.scalatest"                %% "scalatest"                   % "3.1.0" % "test",
      "org.slf4j"                    % "slf4j-nop"                    % "1.7.30" % "test"
    )
  )
  .jvmPlatform(
    scalaVersions = Seq("2.12.10"),
    settings = Seq(
      libraryDependencies ++= {
        val (playVersion, circeVersion) = scalaBinaryVersion.value match {
          case "2.12" => ("2.6.25", "0.12.3")
        }
        Seq(
          "io.circe"          %% "circe-core"   % circeVersion,
          "io.circe"          %% "circe-parser" % circeVersion,
          "com.typesafe.play" %% "play"         % playVersion % "optional",
          "com.typesafe.play" %% "play-test"    % playVersion % "test"
        )
      }
    )
  )
