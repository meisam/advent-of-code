package year2025
package day07

import scala.collection.mutable.ArraySeq

enum Cell:
  case `.`, `^`, `S`

import Cell.*

extension (c: Char)
  def asCell: Cell = c match
    case '.' => `.`
    case '^' => `^`
    case 'S' => `S`

type Grid = Array[Array[Cell]]

type Ray = Int

type SplitCount = Int

type RowId = Int

type Coordinate = (RowId, Ray)

extension (input: Iterator[String])
  def parse: Grid =
    input.map(_.toCharArray.map(_.asCell)).toArray

extension (grid: Grid)
  def startPoint: Ray =
    grid.head.indexOf(`S`)

  def childRays(ray: Ray): Set[Ray] =
    Set(ray-1, ray+1).filter(r => r >= 0 && r < grid.length)

  def cascadeRays(rays: Set[Ray] = Set.empty, rowId: RowId=1, tally: SplitCount = 0): SplitCount =
    rowId == grid.size match
      case true => tally
      case false => 
        val nextRays:Set[Ray] = for
          ray <- rays
          childRay <- if grid(rowId)(ray) == `.` then Set(ray) else (grid.childRays(ray))
        yield childRay

        val splitCnt: SplitCount = rays.filter(ray => grid(rowId)(ray) == `^`).size
        cascadeRays(nextRays, rowId + 1, tally + splitCnt)

  def countTimelines: Long =
    val memo = Array.tabulate[Long](grid.size, grid.head.length):
      (i, j) => if i == grid.size - 1 then 1L else 0L

    def dp(i: RowId): Unit =
      if i < 0 then return ()
      grid(i).indices.foreach:
        j => 
          memo(i)(j) = grid(i)(j) match
            case `^` =>
              grid
                .childRays(j)
                .foldLeft(0L):
                  (tally, childJ) =>
                    tally + memo(i + 1)(childJ)
            case _ => memo(i+1)(j)
      dp(i-1)

    dp(grid.size - 2)
    memo(0)(startPoint)
      



