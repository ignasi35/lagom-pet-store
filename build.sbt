organization in ThisBuild := "com.example.pet"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val root = (project in file("."))
  .settings(name := "pet-store")
  .aggregate(petService,
//    storeService,
    web)
  .settings(commonSettings: _*)

lazy val petService = (project in file("petService"))
  .settings(commonSettings: _*)
  .enablePlugins(LagomJava && LagomOpenApiPlugin)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++=
      Seq(
        lagomJavadslApi,
        lagomJavadslJackson,
        filters
      )
  )


lazy val storeService = (project in file("storeService"))
  .settings(commonSettings: _*)
  .enablePlugins(LagomScala && LagomOpenApiPlugin)
  .settings(
    version := "1.0-SNAPSHOT"
  )

lazy val web = (project in file("web"))
  .settings(commonSettings: _*)
  .enablePlugins(PlayJava && LagomPlay && LagomOpenApiPlugin)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomJavadslClient
    )
  )

def commonSettings: Seq[Setting[_]] = Seq(
  javacOptions in compile += "-parameters"
)

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false
