package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.common.Coordinate2D

sealed class TetrisPiece(val width: Int, val height: Int) {
    abstract fun generateFigure(bottomLeft: Coordinate2D): List<Coordinate2D>
}

object LinePiece : TetrisPiece(4, 1) {
    override fun generateFigure(bottomLeft: Coordinate2D): List<Coordinate2D> = listOf(
        bottomLeft.copy(),
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y),
        Coordinate2D(bottomLeft.x + 2, bottomLeft.y),
        Coordinate2D(bottomLeft.x + 3, bottomLeft.y)
    )
}

object PlusPiece : TetrisPiece(3, 3) {
    override fun generateFigure(bottomLeft: Coordinate2D): List<Coordinate2D> = listOf(
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y),
        Coordinate2D(bottomLeft.x, bottomLeft.y + 1),
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y + 1),
        Coordinate2D(bottomLeft.x + 2, bottomLeft.y + 1),
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y + 2)
    )
}

object ElPiece : TetrisPiece(3, 3) {
    override fun generateFigure(bottomLeft: Coordinate2D): List<Coordinate2D> = listOf(
        bottomLeft.copy(),
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y),
        Coordinate2D(bottomLeft.x + 2, bottomLeft.y),
        Coordinate2D(bottomLeft.x + 2, bottomLeft.y + 1),
        Coordinate2D(bottomLeft.x + 2, bottomLeft.y + 2)
    )
}

object PipePiece : TetrisPiece(1, 4) {
    override fun generateFigure(bottomLeft: Coordinate2D): List<Coordinate2D> = listOf(
        Coordinate2D(bottomLeft.x, bottomLeft.y),
        Coordinate2D(bottomLeft.x, bottomLeft.y + 1),
        Coordinate2D(bottomLeft.x, bottomLeft.y + 2),
        Coordinate2D(bottomLeft.x, bottomLeft.y + 3)
    )
}

object SquarePiece : TetrisPiece(2, 2) {
    override fun generateFigure(bottomLeft: Coordinate2D): List<Coordinate2D> = listOf(
        bottomLeft.copy(),
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y),
        Coordinate2D(bottomLeft.x, bottomLeft.y + 1),
        Coordinate2D(bottomLeft.x + 1, bottomLeft.y + 1),
    )
}
