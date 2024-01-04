package me.sivieri.aoc2022.day25

class Bob(data: List<String>) {

    private val numbers = data.map { Snafu(it) }

    fun calculateTotal(): Long = numbers.sumOf { it.toLong() }

}