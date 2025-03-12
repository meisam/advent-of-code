package parser

object StringParsers extends Parsers[StringParsers.Parser]:
  /*opaque*/
  type Parser[+A] = Location => Result[A]
  enum Result[+A]:
    case Success(get: A, length: Int)
    case Failure(get: ParseError, isCommited: Boolean) extends Result[Nothing]

    def extract: ParseError | A =
      this match
        case Failure(e, _) => e
        case Success(a, _) => a

    def uncommit: Result[A] =
      this match
        case Failure(e, _) => Result.Failure(e, false)
        case _             => this

    def addCommit(isCommited: Boolean): Result[A] =
      this match
        case Failure(get, c) => Result.Failure(get, c || isCommited)
        case _               => this

    def mapError(f: ParseError => ParseError): Result[A] =
      this match
        case Failure(e, c) => Failure(f(e), c)
        case _             => this
    def advanceSuccess(n: Int): Result[A] =
      this match
        case Success(a, m) => Success(a, m + n)
        case _             => this
  end Result

  def succeed[A](a: A): Parser[A] =
    _ => Result.Success(a, 0)

  def firstNonmatchingIndex(s1: String, s2: String, offset: Int): Option[Int] =
    @scala.annotation.tailrec
    def recurse(i: Int): Option[Int] =
      if offset + i >= s1.size then return Some(s1.size - offset)
      if i >= s2.size then return None
      if s1.charAt(offset + i) != s2.charAt(i) then return Some(i)
      recurse(i + 1)
    recurse(0)

  def string(s: String): Parser[String] =
    loc =>
      firstNonmatchingIndex(loc.input, s, loc.offset) match
        case None => Result.Success(s, s.length)
        case Some(i) =>
          Result.Failure(loc.advanceBy(i).toError(s"'$s'"), i != 0)

  def fail(msg: String): Parser[Nothing] =
    loc => Result.Failure(loc.toError(msg), true)

  def regex(r: scala.util.matching.Regex): Parser[String] =
    loc =>
      r.findPrefixOf(loc.remaining) match
        case None    => Result.Failure(loc.toError(s"regex $r"), false)
        case Some(m) => Result.Success(m, m.size)

  extension [A](p: Parser[A])
    def attempt: Parser[A] = p(_).uncommit

    def flatMap[B](f: A => Parser[B]): Parser[B] =
      loc =>
        p(loc) match
          case Result.Success(a, n) =>
            f(a)(loc.advanceBy(n))
              .addCommit(n != 0)
              .advanceSuccess(n)
          case fail @ Result.Failure(_, _) => fail

    def label(message: String): Parser[A] =
      p(_).mapError(_.label(message))

    infix def or(p2: Parser[A]): Parser[A] =
      loc =>
        p(loc) match
          case Result.Failure(_, _) => p2(loc)
          case success              => success

    def run(input: String): ParseError | A =
      p(Location(input, 0)).extract

    def slice: Parser[String] =
      loc =>
        p(loc) match
          case Result.Success(a, n)        => Result.Success(loc.slice(n), n)
          case fail @ Result.Failure(_, _) => fail

    def scope(message: String): Parser[A] =
      loc => p(loc).mapError(_.push(loc, message))
  end extension
end StringParsers
