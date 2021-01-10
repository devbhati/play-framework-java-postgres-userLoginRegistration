name := """PlayPOC"""
organization := "com.play.poc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

lazy val jooqVersion = "3.9.3"

lazy val postgresVersion = "42.2.18"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  jdbc,
  javaWs,
  guice,
  javaForms,
  javaJdbc,
  evolutions,
  "org.postgresql"  % "postgresql"    % postgresVersion,
  "org.jooq"        % "jooq"          % jooqVersion,
  "org.jooq"        % "jooq-meta"     % jooqVersion,
  "org.jooq"        % "jooq-codegen"  % jooqVersion,
)
