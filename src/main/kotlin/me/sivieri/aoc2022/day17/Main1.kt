package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(17)
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(data, pieces)
        board.play(2022)
        val height = board.maxHeight()
        println(height)
    }

}