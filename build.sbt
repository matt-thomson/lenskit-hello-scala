organization := "org.grouplens.lenskit"

name := "lenskit-hello-scala"
 
version := "0.0.1"
 
scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.grouplens.lenskit" % "lenskit-knn" % "2.0",
  "org.slf4j" % "slf4j-simple" % "1.7.5"
)
