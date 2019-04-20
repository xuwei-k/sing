    lazy val theSettings = Project.defaultSettings ++ Seq(
        organization := "com.github.okomok"
        , version := "0.2.0"
        , scalaVersion := "2.13.0-RC1"

        , scalacOptions ++=
            Seq("-unchecked", "-deprecation", "-feature")
            ++ Seq("-Yrecursion", "50") ++ Seq("-language", "higherKinds")

        ,libraryDependencies ++= Seq(
             "junit" % "junit" % "4.4" % "test",
             "org.scalatest" %% "scalatest" % "3.0.8-RC2" % "test"
        )

        , parallelExecution := false
        , publishArtifact in packageDoc := false
//        , offline := true

//        , resolvers += Resolver.sonatypeRepo("releases")
//        , addCompilerPlugin("org.scalamacros" % "paradise" % "2.0.0" cross CrossVersion.full)
    )

    lazy val root = Project(
        "sing-root"
        , file(".")
        , settings = theSettings ++ Seq(
            publish := ()
            , publishLocal := ()
        )
    ) aggregate(macros, core)

    lazy val macros = Project(
        "sing-macros"
        , file("macros")
        , settings = theSettings ++ Seq(
            libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)
        )
    )

    lazy val core = Project(
        "sing-core"
        , file("core")
        , settings = theSettings
    ).dependsOn(macros)
