package me.sivieri.aoc2022.day8

import me.sivieri.aoc2022.crossProduct

class Forest(input: List<String>) {

    private val trees = input.map { it.toList().map { it.toString().toInt() } }
    private val xSize = trees.first().size
    private val ySize = trees.size

    fun countVisibleTrees(): Int = (1 until (xSize - 1))
        .toList()
        .crossProduct((1 until (ySize - 1)).toList())
        .count { (x, y) ->
            val current = trees[y][x]
            Direction.values().any {
                val elements = getAllElements(x, y, it)
                elements.all { it < current }
            }
        } + xSize + xSize + ySize + ySize - 4 // borders

    fun getAllElements(x: Int, y: Int, direction: Direction): List<Int> = when (direction) {
        Direction.TOP -> trees.subList(0, y).map { it[x] }
        Direction.BOTTOM -> trees.subList(y + 1, ySize).map { it[x] }
        Direction.LEFT -> trees[y].subList(0, x)
        Direction.RIGHT -> trees[y].subList(x + 1, xSize)
    }

}