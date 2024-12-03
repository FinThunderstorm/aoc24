package dev.alanen.aoc24.days

import dev.alanen.aoc24.utils.FileUtils
import dev.alanen.aoc24.utils.getContent

class DayThree {
    companion object {
        private val mulRegex = """mul\(\d+,\d+\)""".toRegex()
        private val numRegex = """\d+""".toRegex()
        private val doRegex = """do\(\)""".toRegex()

        fun taskOne(): String {
            var file = FileUtils.readFile("dayThree.txt")

            var input = file.getContent()
                .replace("\n", "")

            var result = mulRegex.findAll(input)
                .map {
                    numRegex.findAll(it.value).map { res -> res.value.toInt() }.reduce { sum, el -> sum * el }
                }.sum()

            return result.toString()
        }

        fun taskTwo(): String {
            var file = FileUtils.readFile("dayThree.txt")

            var result = file.getContent()
                .replace("\n", "")
                .split(doRegex)
                .map { it.substringBefore("don't()") }
                .flatMap { part ->
                    mulRegex.findAll(part).map {
                        numRegex.findAll(it.value).map { res -> res.value.toInt() }.reduce { sum, el -> sum * el }
                    }
                }
                .sum()

            return result.toString()
        }
    }
}