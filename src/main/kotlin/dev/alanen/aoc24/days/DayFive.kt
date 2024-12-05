package dev.alanen.aoc24.days

import dev.alanen.aoc24.utils.FileUtils
import dev.alanen.aoc24.utils.getContent

class DayFive {
    companion object {
        private val input = FileUtils.readFile("dayFive.txt").getContent()
        private val pageOrderingRuleRegex = """\d+\|\d+""".toRegex()
        private val updateRegex = """(\d+,)+\d+""".toRegex()
        private val rulebook = getRulebook(input)


        fun taskOne(): String {
            var updates = updateRegex.findAll(input)
                .map { it.value.split(",") }
                .filter { update -> update.getRowValidity(rulebook).all { it } }
                .sumMiddleValues()
            return updates.toString()
        }

        private fun getRulebook(input: String): Map<String, Set<String>> {
            val rulebook = mutableMapOf<String, MutableSet<String>>()

            pageOrderingRuleRegex.findAll(Companion.input).forEach {
                val rule = it.value.split("|")

                if (!rulebook.contains(rule[1])) {
                    rulebook[rule[1]] = mutableSetOf()
                }
                rulebook[rule[1]]?.add(rule[0])
            }

            return rulebook
        }
    }
}

fun List<String>.getRestRow(idx: Int) = this.subList(idx + 1, this.lastIndex + 1)
fun List<String>.checkValidity(rules: Set<String>) = this.map { rules.contains(it) }.none { it }
fun List<String>.getRowValidity(rulebook: Map<String, Set<String>>) = this.mapIndexed { idx, num ->
    val rules = rulebook[num] ?: return@mapIndexed true

    return@mapIndexed this
        .getRestRow(idx)
        .checkValidity(rules)
}

fun List<String>.getMiddleValue() = this[this.lastIndex / 2].toInt()
fun Sequence<List<String>>.sumMiddleValues() = this.sumOf { it.getMiddleValue() }