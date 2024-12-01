package dev.alanen.aoc24.utils

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import java.nio.charset.Charset

class FileUtils {
    companion object {
        fun readFile(fileName: String): Resource {
            return ClassPathResource("inputs/$fileName")
        }
    }
}

fun Resource.getContent() = this.getContentAsString(Charset.defaultCharset())
fun String.splitRows() = this.split("\n")