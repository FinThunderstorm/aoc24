package dev.alanen.aoc24

import dev.alanen.aoc24.days.Day1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @GetMapping("/dayOne/taskOne")
    fun dayOneTaskOne(): String {
        return Day1.taskOne()
    }
}