package day01

@main
def main: Unit =
  scala.util.Using(scala.io.Source.fromFile("day01.input")): source =>
    val input = source.mkString
    day01.parse(input) match
      case scala.util.Success(s)  => println(f"OUTPUT: $s")
      case scala.util.Failure(ex) => println(f"Error: $ex")
      case err                    => println(f"Error: $err")

    day01.similarityScore(input) match
      case scala.util.Success(s)  => println(f"OUTPUT: $s")
      case scala.util.Failure(ex) => println(f"Error: $ex")
      case err                    => println(f"Error: $err")
