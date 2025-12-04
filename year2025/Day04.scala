package year2025
package day04

enum WallPart:
  case Roll, Empty

opaque type Coordinates = (Int, Int)
opaque type Wall = Array[Array[WallPart]]

extension (self: Wall)

  def apply(i: Int, j: Int): WallPart =
    if i < 0 || i >= self.length then return WallPart.Empty
    if j < 0 || j >= self(i).length then return WallPart.Empty
    self(i)(j)

  def neighborsCoordinatesOf(i: Int, j: Int): Seq[Coordinates] =
    for
      di <- Seq(-1, 0, 1)
      dj <- Seq(-1, 0, 1)
      if di != 0 || dj != 0
      x = i + di
      y = j + dj
      if x >= 0 && x < self.length && y >= 0 && y < self(x).length
    yield (x, y)

  def accessible(i: Int, j: Int): Boolean =
    self(i, j) == WallPart.Roll &&
    neighborsCoordinatesOf(i, j)
    .filter:
      (x, y) => self(x, y) == WallPart.Roll
    .size < 4
    
  
  def accessibleRols: Seq[Coordinates] =
    for
      i <- self.indices
      j <- self(i).indices
      if accessible(i, j)
    yield (i, j)

  def countAccessibleRols(cascade: Boolean): Int =
    cascade match
      case false => self.accessibleRols.size
      case true => ???

extension (self: Iterator[String])
  def toWall: Wall =
    def parseLine(line: String): Array[WallPart] =
      line.map:
        case '@' => WallPart.Roll 
        case '.' => WallPart.Empty
      .toArray
    self.map(parseLine).toArray
    