addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")

addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.6.1")

addSbtPlugin("org.scoverage" %% "sbt-coveralls" % "1.2.7")

resolvers += Resolver.bintrayIvyRepo("rallyhealth", "sbt-plugins")
addSbtPlugin("com.rallyhealth.sbt" % "sbt-git-versioning" % "1.2.2")

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.5")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.0")

addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.4.0")
