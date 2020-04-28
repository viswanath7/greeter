package com.example.greeter

import cats.Applicative
import cats.implicits._
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe._

trait Greeter[F[_]]{
  def greet(name: Greeter.Name): F[Greeter.Greeting]
}

object Greeter {
  implicit def apply[F[_]](implicit ev: Greeter[F]): Greeter[F] = ev

  final case class Name(name: String) extends AnyVal
  /**
    * More generally you will want to decouple your edge representations from
    * your internal data structures, however this shows how you can
    * create encoders for your data.
    **/
  final case class Greeting(greeting: String) extends AnyVal
  object Greeting {
    implicit val greetingEncoder: Encoder[Greeting] =
      (greeting: Greeting) => Json.obj( ("message", Json fromString greeting.greeting) )
    implicit def greetingEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Greeting] =
      jsonEncoderOf[F, Greeting]
  }

  def impl[F[_]: Applicative]: Greeter[F] =
    (name: Greeter.Name) => Greeting("Hello, " + name.name).pure[F]
}