package dev.alanen.aoc24.days

import dev.alanen.aoc24.utils.FileUtils
import dev.alanen.aoc24.utils.getContent
import dev.alanen.aoc24.utils.splitRows

class DayTwo {
    companion object {
        fun taskOne(): String {
            var file = FileUtils.readFile("dayTwo.txt")

            var rows = file
                .getContent()
                .splitRows()
                .map { it.split(" ").map { value -> value.toInt() } }
                .map { row ->
                    row.mapIndexed { idx, it ->
                        if (idx != 0) it - row[idx - 1] else null
                    }.filterNotNull()
                }
                .map { row -> row.all { it in -3..-1 } || row.all { it in 1..3 } }
                .fold(0) { sum, el -> if (el) sum + 1 else sum }

            return rows.toString()
        }

        fun taskTwo(): String {
            val file = FileUtils.readFile("dayTwo.txt")

            val rows = file
                .getContent()
                .splitRows()
                .map { it.mapRow() }
                .map { row ->
                    val fullRowDiff = row.calculateDiff()

                    if (fullRowDiff.isValid()) {
                        return@map 1
                    }

                    (0..row.lastIndex).map { idx ->
                        val clonedRow = row.joinToString(" ").mapRow()

                        return@map clonedRow.subList(0, idx) + clonedRow.subList(idx + 1, clonedRow.size)
                    }.map { row -> row.calculateDiff() }.forEach { row ->
                        if (row.isValid()) {
                            return@map 1
                        }
                    }

                    return@map 0
                }
                .sum()
            return rows.toString()
        }
    }
}

fun String.mapRow() = this.split(" ").map { value -> value.toInt() }
fun List<Int>.calculateDiff() = this.mapIndexed { idx, it ->
    if (idx < this.lastIndex) it - this[idx + 1] else null
}.filterNotNull()

fun List<Int>.isValid() = this.all { it in -3..-1 } || this.all { it in 1..3 }