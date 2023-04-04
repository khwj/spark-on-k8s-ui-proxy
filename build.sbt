scalaVersion := "3.2.2"

name := "uiproxy"
organization := "io.khwj.spark"
version := "1.0"

val jettyVersion = "11.0.14"
val kubernetesClientVersion = "6.5.1"

// val jettyVersion = "9.4.51.v20230217"
// val kubernetesClientVersion = "5.12.4"

libraryDependencies += "org.eclipse.jetty.toolchain" % "jetty-jakarta-servlet-api" % "5.0.2"
libraryDependencies += "org.eclipse.jetty" % "jetty-server" % jettyVersion
libraryDependencies += "org.eclipse.jetty" % "jetty-servlet" % jettyVersion
libraryDependencies += "org.eclipse.jetty" % "jetty-proxy" % jettyVersion
libraryDependencies += "io.fabric8" % "kubernetes-client" % kubernetesClientVersion
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % Test
