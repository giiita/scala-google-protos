
name := "scala-google-protos"

scalaVersion := "2.11.12"


/**
  * 共通設定
  */
lazy val commonsSettings = Seq(
  organization := "jp.giita",
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  libraryDependencies ++= Seq(
    // "com.google.api.grpc" % "googleapis-common-protos" % "0.0.3" % "protobuf",
    "com.google.protobuf" % "protobuf-java" % "3.5.1",

    // scalaPB
    "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.6.7" % "protobuf",
    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.6.7"
  )
)

lazy val root = (project in file(".")).settings(
  Seq(
    name := "scala-google-protos"
  )
).aggregate(
  googleapi
)

lazy val googleapi = (project in file("googleapi"))
  .settings(
    commonsSettings union Seq(
      version := "0.0.1",
      name := "scala-google-proto-api",
      PB.targets in Compile := Seq(
        scalapb.gen() -> (sourceManaged in Compile).value / "scala"
      ),
      PB.includePaths in Compile := Seq(
        file("googleapi/src/main/protobuf")
      )
    )
  )