package dev.alanen.aoc24.days

import dev.alanen.aoc24.utils.FileUtils
import dev.alanen.aoc24.utils.Matrix
import dev.alanen.aoc24.utils.Point
import dev.alanen.aoc24.utils.getContent

class DaySix {
    companion object {
        private val input = FileUtils.readFile("daySix.txt").getContent()
        private val matrix = Matrix(input)

        fun taskOne(): String {
            var visited: MutableSet<String> = mutableSetOf()
            var currentMode: Mode = Mode.UP
            var currentPoint: Point = matrix.findElementPoint(currentMode.element) ?: return "0"
            var nextPoint: Point = getNextPoint(currentPoint, currentMode)

            while (matrix.pointExists(nextPoint)) {
                if (matrix.isPointObstruction(nextPoint)) {
                    currentMode = getNextMode(currentMode)
                    nextPoint = getNextPoint(currentPoint, currentMode)
                    continue
                }
                visited.addVisited(currentPoint)
                matrix.setVisited(currentPoint)
                matrix.setGuard(nextPoint, currentMode)
                currentPoint = nextPoint
                nextPoint = getNextPoint(currentPoint, currentMode)
            }
            matrix.setVisited(currentPoint)
            visited.addVisited(currentPoint)

            return visited.size.toString()
        }

        private fun getNextMode(currentMode: Mode): Mode {
            return when (currentMode) {
                Mode.UP -> Mode.RIGHT
                Mode.RIGHT -> Mode.DOWN
                Mode.DOWN -> Mode.LEFT
                Mode.LEFT -> Mode.UP
            }
        }

        private fun getNextPoint(currentPoint: Point, currentMode: Mode): Point {
            return when (currentMode) {
                Mode.UP -> Point(currentPoint.x, currentPoint.y - 1)
                Mode.RIGHT -> Point(currentPoint.x + 1, currentPoint.y)
                Mode.DOWN -> Point(currentPoint.x, currentPoint.y + 1)
                Mode.LEFT -> Point(currentPoint.x - 1, currentPoint.y)
            }
        }
    }
}

fun MutableSet<String>.addVisited(point: Point) = this.add(point.toString())
fun Matrix.isPointVisited(point: Point): Boolean = this.checkPoint(point, Elements.VISITED)
fun Matrix.isPointObstruction(point: Point): Boolean = this.checkPoint(point, Elements.OBSTRUCTION)
fun Matrix.checkPoint(point: Point, element: Elements): Boolean = this.getPoint(point) == element.element
fun Matrix.setGuard(point: Point, currentMode: Mode) = this.setPoint(point, currentMode.element)
fun Matrix.setVisited(point: Point) = this.setPoint(point, Elements.VISITED.element)

enum class Elements(val element: String) {
    OBSTRUCTION("#"),
    VISITED("X"),
    UNVISITED(".")
}

enum class Mode(val element: String) {
    UP("^"), DOWN("v"), LEFT("<"), RIGHT(">")
}