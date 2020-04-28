package com.example.greeter

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.syntax._
import org.http4s.dsl.io._
import org.http4s.implicits._

object GreeterRoutes {

  def healthRoutes[F[_]: Sync]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "health-check" => Ok(s"Okay")
    }
  }

  def jokeRoutes[F[_]: Sync](jokes: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- jokes.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def greeterRoutes[F[_]: Sync](H: Greeter[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "greet" / name =>
        for {
          greeting <- H.greet(Greeter.Name(name))
          resp <- Ok(greeting)
        } yield resp
    }
  }
}