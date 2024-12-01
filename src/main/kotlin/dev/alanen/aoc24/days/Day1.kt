package dev.alanen.aoc24.days

import dev.alanen.aoc24.utils.FileUtils
import dev.alanen.aoc24.utils.getContent
import dev.alanen.aoc24.utils.splitRows
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.abs

class Day1 {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Day1::class.java)

        fun taskOne(): String {
            var file = FileUtils.readFile("dayOne.txt")

            val left = mutableListOf<Long>()
            val right = mutableListOf<Long>()

            file.getContent()
                .splitRows()
                .map { it.split("   ") }
                .filter { it.isNotEmpty() && it.size == 2 }
                .forEach {
                    left.add(it[0].toLong())
                    right.add(it[1].toLong())
                }

            left.sort()
            right.sort()

            val result = left.zip(right) { l, r -> abs(l - r) }.reduce { subtotal, value -> subtotal + value }

            return result.toString()
        }

        fun taskTwo(): String {
            var file = FileUtils.readFile("dayOne.txt")

            val left = mutableListOf<Long>()
            val right = mutableMapOf<Long, Int>()

            file.getContent()
                .splitRows()
                .map { it.split("   ") }
                .filter { it.isNotEmpty() && it.size == 2 }
                .forEach {
                    left.add(it[0].toLong())

                    val r = it[1].toLong()
                    right.putIfAbsent(r, 0)
                    right[r] = right[r]!! + 1
                }

            val result = left.map { it * (right[it] ?: 0) }.reduce { subtotal, value -> subtotal + value }

            return result.toString()
        }
    }
}