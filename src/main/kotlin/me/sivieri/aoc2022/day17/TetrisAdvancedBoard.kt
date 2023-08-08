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
    private var currentPiece = 0
    private var highestY = -1
    var maxHeight = 0L
    private lateinit var board: List<MutableList<Char>>

    fun play(moves: Long) {
        // find loop
        val states = mutableMapOf<PeakState, Pair<Int, Int>>()
        board = List(CONST_HEIGHT) { List(WIDTH) { AIR }.toMutableList() }
        currentPiece = 0
        var i = 1
        var state = PeakState(emptyList(), 0, 0)
        while (true) {
            if (i > moves) break
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
            state = calculatePeakState()
            if (state in states) {
                println("State $state")
                break
            }
            states[state] = Pair(i - 1, highestY + 1)
            i++
        }
        if (i > moves) {
            maxHeight += highestY + 1
            return
        }
        val (initialMoves, initialHeight) = states.getValue(state)
        val loopLength = i - 1 - initialMoves
        val loops = (moves - initialMoves) / loopLength
        val remainingMoves = (moves - initialMoves) - (loops * loopLength) - 1
        val heightGainedSinceLoop = highestY + 1 - initialHeight

        // run the remaining moves
        for (i in 1..remainingMoves) {
            val piece = pieces[currentPiece]
            currentPiece = (currentPiece + 1) % pieces.size
            singlePieceCompleteFall(piece, highestY)
            highestY = calculateNewHeight()
        }
        maxHeight += highestY + 1 + (heightGainedSinceLoop * (loops - 1))
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

    private fun calculatePeakState(): PeakState {
        val index = board.indexOf(EMPTY_ROW)
        val rev = board.subList(0, index).reversed().zipWithIndex { it }
        val indexes = (0 until WIDTH).map { i ->
            rev.firstOrNull { it.second[i] == PIECE }?.first ?: -1
        }
        return PeakState(indexes, currentPiece, this.index)
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

    fun printBoard() {
        val firstEmpty = board.indexOf(listOf(AIR, AIR, AIR, AIR, AIR, AIR, AIR))
        board.subList(0, firstEmpty + 3).reversed().forEach { println(it.joinToString("", prefix = "|", postfix = "|")) }
        println("|-------|")
    }

    companion object {
        private const val AIR = '.'
        private const val PIECE = '#'
        private const val LEFT = '<'
        private const val RIGHT = '>'
        private const val WIDTH = 7
        private val CONST_HEIGHT = 10000
        private val EMPTY_ROW = List(WIDTH) { AIR }
    }

}