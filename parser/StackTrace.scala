package parser

case class StackTrace[L](stack: List[(Location, String)] = Nil):
  def push(loc: Location, msg: String): StackTrace[L] =
    StackTrace((loc, msg) :: stack)
  def label(s: String): StackTrace[L] =
    StackTrace()
