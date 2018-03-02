addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.13")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")

libraryDependencies ++= Seq(
  "com.trueaccord.scalapb" %% "compilerplugin" % "0.6.7"
)