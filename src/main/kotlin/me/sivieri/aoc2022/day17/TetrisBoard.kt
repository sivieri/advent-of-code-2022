package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.zipWithIndex

@Suppress("ConvertArgumentToSet")
class TetrisBoard(
    wind: String,
    private val pieces: List<TetrisPiece>
) {
    private val wind: String = wind.trim()
    private var index = 0
    private var highestY = -1
    private lateinit var board: List<MutableList<Char>>

    fun play(moves: Int) {
        val height = moves * MAX_HEIGHT + SPACE * 10
        board = List(height) { List(WIDTH) { AIR }.toMutableList() }
        var currentPiece = 0
        for (i in 1..moves) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
        }
    }

    fun playWithCollateral(moves: Int, f: (Int, List<MutableList<Char>>, Int) -> Unit) {
        val height = moves * MAX_HEIGHT + SPACE * 10
        board = List(height) { List(WIDTH) { AIR }.toMutableList() }
        var currentPiece = 0
        for (i in 1..moves) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
            f(i, board, maxHeight())
        }
    }

    fun maxHeight(): Int = highestY + 1

    fun boardStatusChecker(checker: (List<MutableList<Char>>) -> Boolean): Boolean = checker(board)

    private fun singlePieceCompleteFall(
        piece: TetrisPiece,
        highestY: Int
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
            position = if (move == LEFT) position.map { Coordinate2D(it.x - 1, it.y) }
            else position.map { Coordinate2D(it.x + 1, it.y) }
            if (canMoveLateral(position, prev, move)) {
                prev.forEach { board[it.y][it.x] = AIR }
                position.forEach { board[it.y][it.x] = PIECE }
                prev = position
            }
            else position = prev // recover previous

            // down move
            position = position.map { Coordinate2D(it.x, it.y - 1) }
            if (canMoveDown(position, prev)) {
                prev.forEach { board[it.y][it.x] = AIR }
                position.forEach { board[it.y][it.x] = PIECE }
            }
            else moving = false
        }
    }

    private fun canMoveDown(
        newPosition: List<Coordinate2D>,
        oldPosition: List<Coordinate2D>
    ): Boolean =
        (newPosition - oldPosition).all { it.y >= 0 && board[it.y][it.x] == AIR }

    private fun canMoveLateral(
        newPosition: List<Coordinate2D>,
        oldPosition: List<Coordinate2D>,
        move: Char
    ): Boolean {
        val limitsCheck = if (move == RIGHT) newPosition.maxOf { it.x } < WIDTH
        else newPosition.minOf { it.x } >= 0
        return limitsCheck && (newPosition - oldPosition).all { board[it.y][it.x] == AIR }
    }

    private fun calculateNewHeight(): Int =
        board.zipWithIndex { it }.last { it.second.any { it == PIECE } }.first

    private fun origin(y: Int): Coordinate2D = Coordinate2D(2, y + 4)

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