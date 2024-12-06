package dev.alanen.aoc24.utils

class Matrix {
    private val xMax: Int
    private val yMax: Int
    private val matrix: List<MutableList<String>>

    constructor(input: String) {
        matrix = input.splitRows().map { it.split("").toMutableList() }
        xMax = if (matrix.isNotEmpty()) matrix[0].lastIndex else 0
        yMax = matrix.lastIndex
    }

    fun pointExists(point: Point): Boolean {
        return !(point.x < 0 || point.x > xMax || point.y < 0 || point.y > yMax)
    }

    fun getPoint(point: Point): String? {
        if (!pointExists(point)) {
            return null
        }

        return matrix[point.y][point.x]
    }

    fun setPoint(point: Point, element: String) {
        if (pointExists(point)) {
            matrix[point.y][point.x] = element
        }
    }

    fun findElementPoint(element: String): Point? {
        matrix.forEachIndexed { y, rows ->
            rows.forEachIndexed { x, el ->
                if (el == element) {
                    return Point(x, y)
                }
            }
        }
        return null
    }

    override fun toString(): String {
        return matrix.joinToString("\n") { row -> row.joinToString("") }
    }

    fun print() {
        print("\n${matrix}\n")
    }
}

class Point {
    val x: Int
    val y: Int

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}
