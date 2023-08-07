package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.zipWithIndex

@Suppress("ConvertArgumentToSet")
class TetrisAdvancedBoard(
    wind: String,
    private val pieces: List<TetrisPiece>
) {
    private val wind: String = wind.trim()
    private var index = 0
    private var highestY = -1
    var maxHeight = 0L
    private lateinit var board: List<MutableList<Char>>

    fun play(moves: Long) {
        // find loop
        val runs = mutableListOf<Pair<Int, Int>>()
        board = List(CONST_HEIGHT) { List(WIDTH) { AIR }.toMutableList() }
        var currentPiece = 0
        var i = 1
        var reference = Pair(0, 0)
        while (true) {
            reference = Pair(currentPiece, index)
            runs.add(reference)
            if (i > moves) break
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
            if (highestY >= CONST_HEIGHT - 10) {
                maxHeight += CONST_HEIGHT / 2
                board = board.subList(CONST_HEIGHT / 2, board.size) + List(CONST_HEIGHT / 2) { List(WIDTH) { AIR }.toMutableList() }
                highestY -= CONST_HEIGHT / 2
            }
            i++
            val repeatStarted = findRepetitions(runs)
            if (repeatStarted) {
                break
            }
        }
        if (i > moves) {
            maxHeight += highestY + 1
            return
        }
        val initialMoves = runs.indexOf(reference) + 1
        val loopLength = i - initialMoves - 1
        val loops = (moves - initialMoves) / loopLength
        val remainingMoves = (moves - initialMoves) % loopLength
        println("$initialMoves - $loopLength - $loops - $remainingMoves")

        // run loop
        board = List(CONST_HEIGHT) { List(WIDTH) { AIR }.toMutableList() }
        currentPiece = 0
        highestY = -1
        for (i in 1..initialMoves) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
            if (highestY >= CONST_HEIGHT - 10) {
                maxHeight += CONST_HEIGHT / 2
                board = board.subList(CONST_HEIGHT / 2, board.size) + List(CONST_HEIGHT / 2) { List(WIDTH) { AIR }.toMutableList() }
                highestY -= CONST_HEIGHT / 2
            }
        }
        maxHeight += highestY + 1

        board = List(CONST_HEIGHT) { List(WIDTH) { AIR }.toMutableList() }
        currentPiece = reference.first
        index = reference.second
        highestY = -1
        for (i in 1..loopLength) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
            if (highestY >= CONST_HEIGHT - 10) {
                maxHeight += CONST_HEIGHT / 2
                board = board.subList(CONST_HEIGHT / 2, board.size) + List(CONST_HEIGHT / 2) { List(WIDTH) { AIR }.toMutableList() }
                highestY -= CONST_HEIGHT / 2
            }
        }
        maxHeight += (highestY + 1) * loops

        board = List(CONST_HEIGHT) { List(WIDTH) { AIR }.toMutableList() }
        currentPiece = reference.first
        index = reference.second
        highestY = -1
        for (i in 1..remainingMoves) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
            if (highestY >= CONST_HEIGHT - 10) {
                maxHeight += CONST_HEIGHT / 2
                board = board.subList(CONST_HEIGHT / 2, board.size) + List(CONST_HEIGHT / 2) { List(WIDTH) { AIR }.toMutableList() }
                highestY -= CONST_HEIGHT / 2
            }
        }
        maxHeight += highestY + 1
    }

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

    private fun findRepetitions(runs: List<Pair<Int, Int>>): Boolean =
        runs.groupBy { it }.mapValues { it.value.size }.filter { it.value > 1 }.isNotEmpty()

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
        private const val WIDTH = 7
        private val CONST_HEIGHT = 1000
    }

}