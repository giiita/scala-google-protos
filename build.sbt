import sbtrelease.ReleaseStateTransformations._

lazy val root = (project in file("."))
  .settings(
    name := "scala-google-protos",
    organization := "com.github.giiita",
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    description := "GoogleAPI proto build for Scala.",
    scalaVersion := "2.12.4",
    releaseCrossBuild := true,
    crossScalaVersions := Seq("2.11.12", "2.12.4"),
    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),
    libraryDependencies ++= Seq(
      "com.google.protobuf" % "protobuf-java" % "3.5.1",

      // scalaPB
      "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.6.7" % "protobuf",
      "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.6.7"
    ),
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value / "scala"
    ),
    PB.includePaths in Compile := Seq(
      (baseDirectory in LocalRootProject).value,
      file("target/protobuf_external/scalapb")
    ),
    PB.protoSources in Compile := Seq(
      (baseDirectory in LocalRootProject).value / "google"
    ),
    PB.deleteTargetDirectory := true
  )

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeRelease", _), enableCrossBuild = true),
  pushChanges
)