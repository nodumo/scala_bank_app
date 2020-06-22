import sbt._

/*
 * Project dependencies.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 */
object ProjectDependencies {

  // --- Versions

  private lazy val catsVersion = "2.1.1"

  private lazy val catsEffectVersion = "2.1.3"

  private lazy val circeVersion = "0.12.3"

  private lazy val doobieVersion = "0.8.8"

  private lazy val http4sVersion = "0.21.1"

  private lazy val fs2Version = "2.4.0"

  // --- Libraries

  private lazy val circe = "io.circe" %% "circe-core" % circeVersion

  private lazy val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectVersion

  private lazy val doobie = "org.tpolecat" %% "doobie-core" % doobieVersion

  private lazy val doobieHikari = "org.tpolecat" %% "doobie-hikari" % doobieVersion

  private lazy val doobiePostgres = "org.tpolecat" %% "doobie-postgres" % doobieVersion

  private lazy val doobieSpec2 = "org.tpolecat" %% "doobie-specs2" % doobieVersion % "test"

  private lazy val doobieDependencies = Seq(doobie, doobieHikari, doobiePostgres)

  private lazy val circeGeneric = "io.circe" %% "circe-generic" % circeVersion

  private lazy val circeParser = "io.circe" %% "circe-parser" % circeVersion

  private lazy val catsLib = "org.typelevel" %% "cats-core" % catsVersion

  private lazy val fs2Lib = "co.fs2" %% "fs2-core" % fs2Version

  private lazy val fs2LibIO = "co.fs2" %% "fs2-io" % fs2Version

  private lazy val fs2LibDependencies = Seq(fs2Lib, fs2LibIO)

  private lazy val http4sBlazeServer = "org.http4s" %% "http4s-blaze-server" % http4sVersion

  private lazy val http4sCirce = "org.http4s" %% "http4s-circe" % http4sVersion

  private lazy val http4sDSL = "org.http4s" %% "http4s-dsl" % http4sVersion

  private lazy val http4sBlazeClient = "org.http4s" %% "http4s-blaze-client" % http4sVersion % "it,test"

  private lazy val http4sDependencies = Seq(http4sBlazeServer, http4sCirce, http4sDSL, http4sBlazeClient)

  private lazy val jodaScalaLib = "com.github.nscala-time" %% "nscala-time" % "2.24.0"

  private lazy val spec2 = "org.specs2" %% "specs2-core" % "4.10.0" % "test"

  // --- Project dependencies

  lazy val applicationDependencies: Seq[ModuleID] = Seq(catsEffect, catsLib, circe, circeGeneric, circeParser, spec2)

  lazy val applicationtransactptransactDependencies: Seq[ModuleID] = Seq(circe, circeGeneric, circeParser, fs2Lib, fs2LibIO, spec2) ++ http4sDependencies

  lazy val applicationtransactposDependencies: Seq[ModuleID] = Seq(circe, circeGeneric, circeParser, fs2Lib, fs2LibIO, spec2)

  lazy val commonDependencies: Seq[ModuleID] = Seq(catsLib, jodaScalaLib, spec2)

  lazy val domainDependencies: Seq[ModuleID] = Seq(catsLib, jodaScalaLib, spec2)

  lazy val infrastructureDependencies: Seq[ModuleID] = Seq(catsEffect) ++ doobieDependencies

}