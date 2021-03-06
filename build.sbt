name := """justa-desafio"""
organization := "com.justa.desafio"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies += guice

libraryDependencies += "net.objecthunter" % "exp4j" % "0.4.8"