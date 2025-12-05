package year2025
package day04

enum WallPart:
  case Roll, Empty

opaque type Coordinates = (Int, Int)

object Coordinates:
  def apply(i: Int, j: Int): Coordinates =
    (i, j)
  
opaque type Wall = Array[Array[WallPart]]

extension (self: Wall)

  private def apply(i: Int, j: Int): WallPart =
    if i < 0 || i >= self.length then return WallPart.Empty
    if j < 0 || j >= self(i).length then return WallPart.Empty
    self(i)(j)

  def apply(coordinates: Coordinates): WallPart =
    val (i, j) = coordinates
    self(i, j)

  private def rollCount(): Int =
    (for
      i <- self.indices
      j <- self(i).indices
      if self(i, j) == WallPart.Roll
    yield 1).sum

  private def clear(coordinates: Coordinates): Wall =
    val (i, j) = coordinates
    if i < 0 || i >= self.length then return self
    if j < 0 || j >= self(i).length then return self
    self(i)(j) = WallPart.Empty
    self

  def neighborsCoordinatesOf(coordinates: Coordinates): Seq[Coordinates] =
    for
      di <- Seq(-1, 0, 1)
      dj <- Seq(-1, 0, 1)
      if di != 0 || dj != 0
      (i, j) = coordinates
      x = i + di
      y = j + dj
      if x >= 0 && x < self.length && y >= 0 && y < self(x).length
    yield Coordinates(x, y)

  def accessible(coordinates: Coordinates): Boolean =
    self(coordinates) == WallPart.Roll &&
    neighborsCoordinatesOf(coordinates)
    .filter:
      (x, y) => self(x, y) == WallPart.Roll
    .size < 4
    
  
  def accessibleRols: Seq[Coordinates] =
    for
      i <- self.indices
      j <- self(i).indices
      if accessible(i, j)
    yield Coordinates(i, j)

  def countAccessibleRols(cascade: Boolean): Int =
    val accessibleRols = self.accessibleRols
    cascade match
      case false => accessibleRols.size
      case true =>
        self.rollCount() -
        self.cascadingClear(accessibleRols.toSet).rollCount()

  private def cascadingClear(toVisit: Set[Coordinates]): Wall =
    if toVisit.isEmpty then return self
    val newToVisit = (for
      coordinates <- toVisit
      if self.accessible(coordinates)
      _ = self.clear(coordinates)
      neighbor:Coordinates <- self.neighborsCoordinatesOf(coordinates)
      if self.accessible(neighbor)
    yield neighbor)
      .toSet
      .filter(self.accessible)
    cascadingClear(newToVisit)
    


extension (self: Iterator[String])
  def toWall: Wall =
    def parseLine(line: String): Array[WallPart] =
      line.map:
        case '@' => WallPart.Roll 
        case '.' => WallPart.Empty
      .toArray
    self.map(parseLine).toArray
    