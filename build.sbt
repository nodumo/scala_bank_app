/*
 *    ___            __    _             ___             ___          __  _
 *   / _ )___ ____  / /__ (_)__  ___ _  / _ | ___  ___  / (_)______ _/ /_(_)__  ___
 *  / _  / _ `/ _ \/  '_// / _ \/ _ `/ / __ |/ _ \/ _ \/ / / __/ _ `/ __/ / _ \/ _ \
 * /____/\_,_/_//_/_/\_\/_/_//_/\_, / /_/ |_/ .__/ .__/_/_/\__/\_,_/\__/_/\___/_//_/
 *                           /___/       /_/  /_/
 * (c) Platform Nick Odumo (nodumo@nodumo.com)
 */
name := "bankapp"
organization in ThisBuild := "com.nodumo"
scalaVersion in ThisBuild := "2.12.3"

// PROJECTS

lazy val global = project
  .in(file("."))
  .settings(settings)
  .disablePlugins(AssemblyPlugin)
  .aggregate(
    common,
    domain,
    infrastructure,
    application
  )

lazy val common = project
  .settings(
    name := "common",
    settings,
    libraryDependencies ++= ProjectDependencies.commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val domain = project
  .settings(
    name := "domain",
    settings,
    assemblySettings,
    libraryDependencies ++= ProjectDependencies.domainDependencies
  )
  .dependsOn(
    common
  )

lazy val infrastructure = project
  .settings(
    name := "infrastructure",
    settings,
    assemblySettings,
    libraryDependencies ++= ProjectDependencies.infrastructureDependencies
  )
  .dependsOn(
    common,
    domain
  )

lazy val application = project
  .settings(
    name := "application",
    settings,
    assemblySettings,
    libraryDependencies ++= ProjectDependencies.applicationDependencies
  )
  .dependsOn(
    common,
    domain,
    infrastructure
  )

// SETTINGS

lazy val settings =
commonSettings ++
wartremoverSettings ++
scalafmtSettings

lazy val compilerOptions = Seq(
  "-Ypartial-unification",
  "-Yrangepos",
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

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val wartremoverSettings = Seq(
  wartremoverWarnings in (Compile, compile) ++= Warts.allBut(Wart.Throw)
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtTestOnCompile := true,
    scalafmtVersion := "1.2.0"
  )

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)
