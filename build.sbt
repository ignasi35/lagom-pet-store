organization in ThisBuild := "com.example"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

EclipseKeys.projectFlavor in Global := EclipseProjectFlavor.Java

lazy val root = (project in file("."))
  .settings(name := "pet-store")
  .aggregate(pet)
  .settings(commonSettings: _*)

lazy val pet = (project in file("pet"))
  .settings(commonSettings: _*)
  .enablePlugins(LagomJava)
  .settings(
    version := "1.0-SNAPSHOT"
  )
  .settings(lagomForkedTestSettings: _*)

def commonSettings: Seq[Setting[_]] = eclipseSettings ++ Seq(
  javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation", "-parameters")
)
