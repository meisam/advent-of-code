package parser

import scala.util.matching.Regex
import java.util.regex.Pattern

trait Parsers[Parser[+_], ParseError]:
  def string(s: String): Parser[String]

  def succeed[A](a: A): Parser[A]

  def fail(msg: String): Parser[Nothing]

  def regex(r: Regex): Parser[String]

  def char(c: Char): Parser[Char] =
    string(c.toString).map(_.charAt(0))

  def defaultSucceed[A](a: A): Parser[A] =
    string("").map(_ => a)

  def whitespace: Parser[String] =
    regex(raw"\s*".r)

  def digits: Parser[String] =
    regex(raw"\d+".r)

  def thru(s: String): Parser[String] =
    regex((raw".*?" + Pattern.quote(s)).r)

  def quoted: Parser[String] =
    string("\"") *> thru("\"").map(_.dropRight(1))

  def escapedQuotod: Parser[String] =
    quoted.label("string literal")

  def doubleString: Parser[String] =
    regex("([+-]?[0-9]*\\.)?[0-9]+([eE][+-]?[0-]+)?".r)
      .label("doubleString")

  def double: Parser[Double] =
    doubleString.map(_.toDouble).label("double literal")

  def decIntString: Parser[String] =
    regex("[+-]?[0-9]+".r)
      .label("decIntString")

  def hexIntString: Parser[String] =
    regex("[+-]?0[xX][0-9a-fA-F]+".r)
      .label("hexIntString")

  def octIntString: Parser[String] =
    regex("[+-]?0[oO][0-7]+".r)
      .label("octIntString")

  def binIntString: Parser[String] =
    regex("[+-]?0[bB][01]+".r)
      .label("binIntString")

  def int: Parser[Int] =
    (decIntString or hexIntString or octIntString or binIntString)
      .map(_.toInt)
    
  def eof: Parser[String] =
    regex("\\z".r).label("Unexpected trailing characters")

  extension [A](p: Parser[A])
    def run(input: String): ParseError | A // why not Either[ParseError, A]

    def flatMap[B](f: A => Parser[B]): Parser[B]

    def attempt: Parser[A]

    def label(message: String): Parser[A]

    infix def or(p2: Parser[A]): Parser[A]

    def slice: Parser[String]

    def scope(message: String): Parser[A]

    def map[B](f: A => B): Parser[B] =
      p.flatMap(f andThen succeed)

    def map2[B, C](p2: => Parser[B])(f: (A, B) => C): Parser[C] =
      for
        a <- p
        b <- p2
      yield f(a, b)

    def listOfN(n: Int): Parser[List[A]] =
      if n <= 0 then succeed(Nil)
      else
        for
          a <- p
          rest <- listOfN(n - 1)
        yield a :: rest
    def many: Parser[List[A]] =
      p.map2(p.many)(_ :: _) | succeed(Nil)

    def many1: Parser[List[A]] =
      for
        a <- p
        rest <- p.many
      yield a :: rest

    def opt: Parser[Option[A]] =
      p.map(Some(_)) | succeed(None)

    def product[B](p2: Parser[B]): Parser[(A, B)] =
      for
        a <- p
        b <- p2
      yield (a, b)

    def |(p2: Parser[A]): Parser[A] =
      p or p2

    def **[B](p2: Parser[B]): Parser[(A, B)] =
      p.product(p2)

    def *>[B](p2: => Parser[B]): Parser[B] =
      for
        a <- p.slice
        b <- p2
      yield b

    def <*[B](p2: => Parser[B]): Parser[A] =
      for
        a <- p
        b <- p2.slice
      yield a

    def token: Parser[A] =
      p.attempt <* whitespace

    def sep(separator: Parser[Any]): Parser[List[A]] =
      p.sep1(separator) | succeed(Nil)

    def sep1(separator: Parser[Any]): Parser[List[A]] =
      for
        a <- p
        tail <- sep1(separator)
      yield a :: tail

    def as[B](b: B): Parser[B] =
      p.map(_ => b)

    def opL(op: Parser[(A, A) => A]): Parser[A] =
      def combine(accum: A, element: ((A, A) => A, A)): A =
        element match
          case (f, e) => f(accum, e)
      for
        a <- p
        tail <- (op ** p).many
      yield tail.foldLeft(a)(combine)