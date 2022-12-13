package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.common.Either

data class ListsElement(
    val elements: MutableMap<Int, Either<Int, ListsElement>>
)
