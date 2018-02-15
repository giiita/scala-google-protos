addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.13")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")

libraryDependencies ++= Seq(
  "com.trueaccord.scalapb" %% "compilerplugin" % "0.6.7"
)