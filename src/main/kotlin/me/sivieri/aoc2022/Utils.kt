package me.sivieri.aoc2022

import java.nio.file.Files
import java.nio.file.Path

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object Utils {

    fun readInput(day: Int): String = Files.readString(Path.of(this::class.java.getResource("/day$day/input_wrong_folding.txt").toURI()))

    fun readInputToList(day: Int): List<String> =
        Files
            .lines(Path.of(this::class.java.getResource("/day$day/input_wrong_folding.txt").toURI()))
            .iterator()
            .asSequence()
            .toList()
            .filter { it.isNotBlank() }

    fun <T> readInputBlocks(
        day: Int,
        f: (String) -> T
    ): List<T> {
        val lines = Files
            .lines(Path.of(this::class.java.getResource("/day$day/input_wrong_folding.txt").toURI()))
            .iterator()
        var buffer = StringBuffer()
        val data = mutableListOf<T>()
        while (lines.hasNext()) {
            val cur = "${lines.next()}\n"
            if (cur == "\n") {
                data.add(f(buffer.toString()))
                buffer = StringBuffer()
            }
            else {
                buffer.append(cur)
            }
        }
        data.add(f(buffer.toString()))
        return data.toList()
    }

}