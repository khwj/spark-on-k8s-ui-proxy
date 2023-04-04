scalaVersion := "3.2.2"

name := "k8s-ui-proxy"
organization := "io.khwj.spark"
version := "1.0"

val jettyVersion = "11.0.14"
val fabric8Version = "6.5.1"

libraryDependencies += "org.eclipse.jetty.toolchain" % "jetty-jakarta-servlet-api" % "5.0.2"
libraryDependencies += "org.eclipse.jetty" % "jetty-server" % jettyVersion
libraryDependencies += "org.eclipse.jetty" % "jetty-servlet" % jettyVersion
libraryDependencies += "org.eclipse.jetty" % "jetty-proxy" % jettyVersion
libraryDependencies += "io.fabric8" % "kubernetes-client" % fabric8Version
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.6"

libraryDependencies += "io.fabric8" % "kubernetes-server-mock" % fabric8Version % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % Test
