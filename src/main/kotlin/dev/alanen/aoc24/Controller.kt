package dev.alanen.aoc24

import dev.alanen.aoc24.days.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @GetMapping("/dayOne/taskOne")
    fun dayOneTaskOne(): String {
        return DayOne.taskOne()
    }

    @GetMapping("/dayOne/taskTwo")
    fun dayOneTaskTwo(): String {
        return DayOne.taskTwo()
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

    @GetMapping("/dayFive/taskOne")
    fun dayFiveTaskOne(): String {
        return DayFive.taskOne()
    }
}