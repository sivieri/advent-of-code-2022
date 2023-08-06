package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.zipWithIndex

class TetrisBoard(
    private val wind: String,
    private val pieces: List<TetrisPiece>
) {
    private var index = 0
    private var highestY = -1
    private lateinit var board: List<MutableList<Char>>

    fun play(moves:Int) {
        val height = moves * MAX_HEIGHT + SPACE * 10
        board = List(height) { List(WIDTH) { AIR }.toMutableList() }
        var currentPiece = 0
        for (i in 1..moves) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            move(piece, highestY, board)
            highestY = calculateNewHeight(board)
        }
    }

    fun maxHeight(): Int = highestY + 1

    fun boardStatusChecker(checker: (List<MutableList<Char>>) -> Boolean): Boolean = checker(board)

    private fun move(
        piece: TetrisPiece,
        highestY: Int,
        board: List<MutableList<Char>>
    ) {
        val start = origin(highestY)
        var position = piece.generateFigure(start)
        var prev: List<Coordinate2D>
        var moving = true
        while (moving) {
            // wind move
            prev = position
            val move = wind[index]
            index = (index + 1) % wind.length
            if (canMoveLateral(position, move, board)) {
                position = if (move == LEFT) position.map { Coordinate2D(it.x - 1, it.y) }
                else position.map { Coordinate2D(it.x + 1, it.y) }
                prev.forEach { board[it.y][it.x] = AIR }
                position.forEach { board[it.y][it.x] = PIECE }
            }

            // down move
            prev = position
            if (canMoveDown(position, board)) {
                position = position.map { Coordinate2D(it.x, it.y - 1) }
                prev.forEach { board[it.y][it.x] = AIR }
                position.forEach { board[it.y][it.x] = PIECE }
            }
            else moving = false
        }
    }

    private fun canMoveDown(position: List<Coordinate2D>, board: List<MutableList<Char>>): Boolean =
        position
            .filter { it.y == position.minOf { it.y } }
            .map { generateCoordinateWithinLimits(it, 0, -1, WIDTH, board.size) }
            .all { board[it.y][it.x] == AIR }

    private fun canMoveLateral(position: List<Coordinate2D>, move: Char, board: List<MutableList<Char>>): Boolean =
        position
            .filter {
                if (move == RIGHT) it.x == position.maxOf { it.x }
                else it.x == position.minOf { it.x }
            }
            .map {
                if (move == RIGHT) generateCoordinateWithinLimits(it, 1, 0, WIDTH, board.size)
                else generateCoordinateWithinLimits(it, -1, 0, WIDTH, board.size)
            }
            .all { board[it.y][it.x] == AIR }

    private fun calculateNewHeight(board: List<List<Char>>): Int =
        board.zipWithIndex { it }.last { it.second.any { it == PIECE } }.first

    private fun origin(y: Int): Coordinate2D = Coordinate2D(2, y + 4)

    private fun generateCoordinateWithinLimits(
        c: Coordinate2D,
        xIncrement: Int,
        yIncrement: Int,
        maxX: Int,
        maxY: Int
    ): Coordinate2D =
        if (c.x + xIncrement >= maxX || c.x + xIncrement < 0 || c.y + yIncrement >= maxY || c.y + yIncrement < 0) c
        else Coordinate2D(c.x + xIncrement, c.y + yIncrement)

    companion object {
        private const val AIR = '.'
        private const val PIECE = '#'
        private const val LEFT = '<'
        private const val RIGHT = '>'
        private const val SPACE = 3
        private const val WIDTH = 7
        private val MAX_HEIGHT = maxOf(LinePiece.height, PipePiece.height, PlusPiece.height, ElPiece.height, SquarePiece.height)
    }

}