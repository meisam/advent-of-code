package day03

@main
def main: Unit =
  scala.util.Using(scala.io.Source.fromFile("day01.input")):
    source =>
        day03.parse(source.mkString)
  match
    case scala.util.Success(s) => println(f"OUTPUT: $s")
    case scala.util.Failure(ex) => println("Error: $ex")
