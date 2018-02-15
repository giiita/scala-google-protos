
name := "scala-google-protos"

scalaVersion := "2.11.12"


/**
  * 共通設定
  */
lazy val commonsSettings = Seq(
  organization := "jp.giita",
  resolvers ++= Seq(
    Resolver.mavenLocal
  ),
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  crossPaths := false,
  publishTo := Some(Resolver.file("file", file("."))),
  libraryDependencies ++= Seq(
    // "com.google.api.grpc" % "googleapis-common-protos" % "0.0.3" % "protobuf",
    "com.google.protobuf" % "protobuf-java" % "3.5.1",

    // scalaPB
    "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.6.7" % "protobuf",
    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.6.7"
  )
)

lazy val googleapi = project
  .settings(
    commonsSettings union Seq(
      PB.targets in Compile := Seq(
        scalapb.gen() -> (sourceManaged in Compile).value / "scala"
      ),
      PB.includePaths in Compile := Seq(
        file("googleapi/src/main/protobuf")
      )
    )
  )