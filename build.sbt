organization in ThisBuild := "com.example"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

EclipseKeys.projectFlavor in Global := EclipseProjectFlavor.Java

lazy val root = (project in file("."))
  .settings(name := "pet-store")
  .aggregate(petApi, petImpl)
  .settings(commonSettings: _*)

lazy val petApi = (project in file("pet-api"))
  .settings(commonSettings: _*)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomJavadslApi
    )
  )

lazy val petImpl = (project in file("pet-impl"))
  .settings(commonSettings: _*)
  .enablePlugins(LagomJava)
  .settings(
    version := "1.0-SNAPSHOT"
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(petApi)

def commonSettings: Seq[Setting[_]] = eclipseSettings ++ Seq(
  javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation", "-parameters")
)
