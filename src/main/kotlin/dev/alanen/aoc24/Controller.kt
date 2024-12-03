package dev.alanen.aoc24

import dev.alanen.aoc24.days.Day1
import dev.alanen.aoc24.days.DayThree
import dev.alanen.aoc24.days.DayTwo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @GetMapping("/dayOne/taskOne")
    fun dayOneTaskOne(): String {
        return Day1.taskOne()
    }

    @GetMapping("/dayOne/taskTwo")
    fun dayOneTaskTwo(): String {
        return Day1.taskTwo()
    }

    @GetMapping("/dayTwo/taskOne")
    fun dayTwoTaskOne(): String {
        return DayTwo.taskOne()
    }

    @GetMapping("/dayTwo/taskTwo")
    fun dayTwoTaskTwo(): String {
        return DayTwo.taskTwo()
    }

    @GetMapping("/dayThree/taskOne")
    fun dayThreeTaskOne(): String {
        return DayThree.taskOne()
    }

    @GetMapping("/dayThree/taskTwo")
    fun dayThreeTaskTwo(): String {
        return DayThree.taskTwo()
    }
}