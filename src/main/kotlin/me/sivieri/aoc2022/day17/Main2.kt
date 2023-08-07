package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(17)
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisAdvancedBoard(data, pieces)
        board.play(1_000_000_000_000)
        val height = board.maxHeight
        println(height)
    }

}