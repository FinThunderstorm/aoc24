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
    }
}
