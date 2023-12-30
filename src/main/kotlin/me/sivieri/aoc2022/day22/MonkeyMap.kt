package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveDown3D
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveLeft3D
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveRight3D

class MonkeyMap(data: String) {

    private val instructions: List<MapInstruction>

    private val board: MutableMap<Coordinate2D, Char>
    private val start: Coordinate2D
    private val maxX: Int
    private val maxY: Int

    private val board3D: Map<Int, MutableMap<Coordinate2D, Char>>
    private val start3D: Pair<Int, Coordinate2D>
    private val maxX3D: Int
    private val maxY3D: Int

    init {
        val (board, instructions) = data.split("\n\n")
        val lines = board.split("\n").filter { it.isNotBlank() }

        // instructions
        val number = mutableListOf<Char>()
        this.instructions = instructions
            .split("\n")[0]
            .flatMap { c ->
                if (c.isDigit()) {
                    number.add(c)
                    listOf()
                }
                else {
                    listOf(
                        MoveInstruction(number.joinToString("").toInt()),
                        if (c == 'L') RotateCounterClockwiseInstruction else RotateClockwiseInstruction
                    ).also { number.clear() }
                }
            }
            .let {
                if (number.isNotEmpty()) it.plus(MoveInstruction(number.joinToString("").toInt())) else it
            }

        // board
        this.maxX = lines.maxOf { it.length }
        this.maxY = lines.size
        this.board = (0 until maxY).flatMap { y ->
            (0 until maxX).map { x ->
                Pair(Coordinate2D(x, y), lines[y].getOrElse(x) { NOTHING })
            }
        }.toMap().toMutableMap()
        this.start = this.board.filter { it.key.y == 0 && this.board[it.key] == EMPTY }.minBy { it.key.x }.key

        // board 3D
        this.maxX3D = this.maxX / 4
        this.maxY3D = this.maxY / 3
        this.start3D = Pair(1, Coordinate2D(x = this.start.x - 2 * this.maxX3D, y = this.start.y))
        val board1 = lines
            .subList(0, this.maxY3D)
            .map { it.trim() }
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexed { x, c -> Pair(Coordinate2D(x, y), c) }
            }.toMap().toMutableMap()
        val board2 = lines
            .subList(this.maxY3D, 2 * this.maxY3D)
            .map { it.trim().substring(0, this.maxX3D) }
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexed { x, c -> Pair(Coordinate2D(x, y), c) }
            }.toMap().toMutableMap()
        val board3 = lines
            .subList(this.maxY3D, 2 * this.maxY3D)
            .map { it.trim().substring(this.maxX3D, 2 * this.maxX3D) }
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexed { x, c -> Pair(Coordinate2D(x, y), c) }
            }.toMap().toMutableMap()
        val board4 = lines
            .subList(this.maxY3D, 2 * this.maxY3D)
            .map { it.trim().substring(2 * this.maxX3D, 3 * this.maxX3D) }
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexed { x, c -> Pair(Coordinate2D(x, y), c) }
            }.toMap().toMutableMap()
        val board5 = lines
            .subList(2 * this.maxY3D, 3 * this.maxY3D)
            .map { it.trim().substring(0, this.maxX3D) }
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexed { x, c -> Pair(Coordinate2D(x, y), c) }
            }.toMap().toMutableMap()
        val board6 = lines
            .subList(2 * this.maxY3D, 3 * this.maxY3D)
            .map { it.trim().substring(this.maxX3D, 2 * this.maxX3D) }
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexed { x, c -> Pair(Coordinate2D(x, y), c) }
            }.toMap().toMutableMap()
        this.board3D = mapOf(
            1 to board1,
            2 to board2,
            3 to board3,
            4 to board4,
            5 to board5,
            6 to board6
        )
    }

    fun play(printSteps: Boolean = false): Int {
        var current = Pair(start, MapDirection.RIGHT)
        instructions.forEachIndexed { index, instruction ->
            board[current.first] = current.second.symbol
            if (printSteps) println("Iteration $index\nInstruction $current\n${boardRepresentation()}\n")
            when (instruction) {
                is RotateClockwiseInstruction -> current = Pair(current.first, current.second.rotate(RotateClockwiseInstruction))
                is RotateCounterClockwiseInstruction -> current = Pair(current.first, current.second.rotate(RotateCounterClockwiseInstruction))
                is MoveInstruction -> {
                    val destination = findDestination(current, instruction)
                    current = Pair(destination, current.second)
                }
            }
        }
        return 1000 * (current.first.y + 1) + 4 * (current.first.x + 1) + current.second.facing
    }

    private fun findDestination(current: Pair<Coordinate2D, MapDirection>, instruction: MoveInstruction): Coordinate2D =
        (1..instruction.move).fold(current.first) { acc, _ ->
            when (current.second) {
                MapDirection.RIGHT -> acc.moveRight(board, maxX)
                MapDirection.LEFT -> acc.moveLeft(board, maxX)
                MapDirection.UP -> acc.moveUp(board, maxY)
                MapDirection.DOWN -> acc.moveDown(board, maxY)
            }
        }

    fun playCube(printSteps: Boolean = false): Int {
        var current = MapPosition3D(start3D.first, start3D.second, MapDirection.RIGHT)
        instructions.forEachIndexed { index, instruction ->
            val (boardIndex, position, direction) = current
            val board = board3D[boardIndex]!!
            board[position] = direction.symbol
            if (printSteps) println("Iteration $index - Instruction $current")
            current = when (instruction) {
                is RotateClockwiseInstruction -> current.copy(direction = current.direction.rotate(RotateClockwiseInstruction))
                is RotateCounterClockwiseInstruction -> current.copy(direction = current.direction.rotate(RotateCounterClockwiseInstruction))
                is MoveInstruction -> findDestination3D(current, instruction)
            }
        }
        val (position, direction) = calculateFinalPosition(current)
        return 1000 * (position.y + 1) + 4 * (position.x + 1) + direction.facing
    }

    private fun findDestination3D(current: MapPosition3D, instruction: MoveInstruction): MapPosition3D =
        (1..instruction.move).fold(current) { acc, _ ->
            when (acc.direction) {
                MapDirection.RIGHT -> acc.moveRight3D(board3D, maxX3D, maxY3D)
                MapDirection.LEFT -> acc.moveLeft3D(board3D, maxX3D, maxY3D)
                MapDirection.UP -> acc.moveUp3D(board3D, maxX3D, maxY3D)
                MapDirection.DOWN -> acc.moveDown3D(board3D, maxX3D, maxY3D)
            }
        }

    private fun calculateFinalPosition(current: MapPosition3D): Pair<Coordinate2D, MapDirection> =
        when (current.board) {
            1 -> Pair(current.position, current.direction)
            2 -> Pair(
                Coordinate2D(current.position.x, current.position.y + maxY3D),
                current.direction
            )
            3 -> Pair(
                Coordinate2D(current.position.x + maxX3D, current.position.y + maxY3D),
                current.direction
            )
            4 -> Pair(
                Coordinate2D(current.position.x + 2 * maxX3D, current.position.y + maxY3D),
                current.direction
            )
            5 -> Pair(
                Coordinate2D(current.position.x + 2 * maxX3D, current.position.y + 2 * maxY3D),
                current.direction
            )
            6 -> Pair(
                Coordinate2D(current.position.x + 3 * maxX3D, current.position.y + 2 * maxY3D),
                current.direction
            )
            else -> throw IllegalArgumentException("Board cannot be ${current.board}")
        }

    private fun boardRepresentation() = (0 until maxY)
        .joinToString("\n") { y ->
            (0 until maxX).joinToString("") { x ->
                board[Coordinate2D(x, y)].toString()
            }
        }

    companion object {
        internal const val EMPTY = '.'
        internal const val WALL = '#'
        internal const val NOTHING = ' '

        private fun adjacencyList(
            c: MapPosition3D,
            mx: Int,
            my: Int
        ): MapPosition3D = when (Pair(c.board, c.direction)) {
            Pair(1, MapDirection.UP) -> MapPosition3D(2, Coordinate2D(mx - c.position.x - 1, 0), MapDirection.DOWN)
            Pair(1, MapDirection.DOWN) -> MapPosition3D(4, Coordinate2D(0, c.position.y), MapDirection.DOWN)
            Pair(1, MapDirection.LEFT) -> MapPosition3D(3, Coordinate2D(c.position.y, 0), MapDirection.DOWN)
            Pair(1, MapDirection.RIGHT) -> MapPosition3D(6, Coordinate2D(mx - 1, my - c.position.y - 1), MapDirection.LEFT)
            Pair(2, MapDirection.UP) -> MapPosition3D(1, Coordinate2D(mx - c.position.x - 1, 0), MapDirection.DOWN)
            Pair(2, MapDirection.DOWN) -> MapPosition3D(5, Coordinate2D(mx - c.position.x - 1, my - 1), MapDirection.UP)
            Pair(2, MapDirection.LEFT) -> MapPosition3D(6, Coordinate2D(mx - c.position.x - 1, my - 1), MapDirection.UP)
            Pair(2, MapDirection.RIGHT) -> MapPosition3D(3, Coordinate2D(0, c.position.y), MapDirection.RIGHT)
            Pair(3, MapDirection.UP) -> MapPosition3D(1, Coordinate2D(0, c.position.x), MapDirection.RIGHT)
            Pair(3, MapDirection.DOWN) -> MapPosition3D(5, Coordinate2D(0, mx - c.position.x - 1), MapDirection.RIGHT)
            Pair(3, MapDirection.LEFT) -> MapPosition3D(2, Coordinate2D(mx - 1, c.position.y), MapDirection.LEFT)
            Pair(3, MapDirection.RIGHT) -> MapPosition3D(4, Coordinate2D(0, c.position.y), MapDirection.RIGHT)
            Pair(4, MapDirection.UP) -> MapPosition3D(1, Coordinate2D(c.position.x, my - 1), MapDirection.UP)
            Pair(4, MapDirection.DOWN) -> MapPosition3D(5, Coordinate2D(c.position.x, 0), MapDirection.DOWN)
            Pair(4, MapDirection.LEFT) -> MapPosition3D(3, Coordinate2D(mx - 1, c.position.y), MapDirection.LEFT)
            Pair(4, MapDirection.RIGHT) -> MapPosition3D(6, Coordinate2D(my - c.position.y - 1, 0), MapDirection.DOWN)
            Pair(5, MapDirection.UP) -> MapPosition3D(4, Coordinate2D(c.position.x, my - 1), MapDirection.UP)
            Pair(5, MapDirection.DOWN) -> MapPosition3D(2, Coordinate2D(c.position.x, 0), MapDirection.DOWN)
            Pair(5, MapDirection.LEFT) -> MapPosition3D(3, Coordinate2D(mx - c.position.x - 1, my - 1), MapDirection.UP)
            Pair(5, MapDirection.RIGHT) -> MapPosition3D(6, Coordinate2D(0, c.position.y), MapDirection.RIGHT)
            Pair(6, MapDirection.UP) -> MapPosition3D(4, Coordinate2D(mx - 1, mx - c.position.x - 1), MapDirection.LEFT)
            Pair(6, MapDirection.DOWN) -> MapPosition3D(2, Coordinate2D(mx - 1, c.position.x), MapDirection.RIGHT)
            Pair(6, MapDirection.LEFT) -> MapPosition3D(5, Coordinate2D(mx - 1, c.position.y), MapDirection.LEFT)
            Pair(6, MapDirection.RIGHT) -> MapPosition3D(1, Coordinate2D(mx - 1, my - c.position.y - 1), MapDirection.LEFT)
            else -> throw IllegalArgumentException("Illegal position $c")
        }

        internal fun Coordinate2D.moveRight(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            val minX = (0..this.x).dropWhile { board[Coordinate2D(it, this.y)] == NOTHING }.first()
            val minC = Coordinate2D(minX, this.y)
            val x = (this.x + 1) % limit
            val c = Coordinate2D(x, this.y)
            return when {
                board[c] == WALL -> this
                board[c] == NOTHING && board[minC] == WALL -> this
                board[c] == NOTHING && board[minC] != WALL -> minC
                else -> c
            }
        }

        internal fun Coordinate2D.moveLeft(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            val maxX = ((limit - 1) downTo this.x).dropWhile { board[Coordinate2D(it, this.y)] == NOTHING }.first()
            val maxC = Coordinate2D(maxX, this.y)
            val x = if (this.x - 1 < 0) limit - 1 else this.x - 1
            val c = Coordinate2D(x, this.y)
            return when {
                board[c] == WALL -> this
                board[c] == NOTHING && board[maxC] == WALL -> this
                board[c] == NOTHING && board[maxC] != WALL -> maxC
                else -> c
            }
        }

        internal fun Coordinate2D.moveDown(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            val minY = (0..this.y).dropWhile { board[Coordinate2D(this.x, it)] == NOTHING }.first()
            val minC = Coordinate2D(this.x, minY)
            val y = (this.y + 1) % limit
            val c = Coordinate2D(this.x, y)
            return when {
                board[c] == WALL -> this
                board[c] == NOTHING && board[minC] == WALL -> this
                board[c] == NOTHING && board[minC] != WALL -> minC
                else -> c
            }
        }

        internal fun Coordinate2D.moveUp(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            val maxY = ((limit - 1) downTo this.y).dropWhile { board[Coordinate2D(this.x, it)] == NOTHING }.first()
            val maxC = Coordinate2D(this.x, maxY)
            val y = if (this.y - 1 < 0) limit - 1 else this.y - 1
            val c = Coordinate2D(this.x, y)
            return when {
                board[c] == WALL -> this
                board[c] == NOTHING && board[maxC] == WALL -> this
                board[c] == NOTHING && board[maxC] != WALL -> maxC
                else -> c
            }
        }

        internal fun MapPosition3D.moveRight3D(
            boards: Map<Int, MutableMap<Coordinate2D, Char>>,
            maxX: Int,
            maxY: Int
        ): MapPosition3D {
            val board = boards[this.board]!!
            val newx = position.x + 1
            return if (newx == maxX) {
                val n = adjacencyList(this, maxX, maxY)
                val newBoard = boards[n.board]!!
                if (newBoard[n.position] == WALL) this
                else n
            }
            else {
                if (board[Coordinate2D(newx, position.y)] == WALL) this
                else copy(position = Coordinate2D(newx, position.y))
            }
        }

        internal fun MapPosition3D.moveLeft3D(
            boards: Map<Int, MutableMap<Coordinate2D, Char>>,
            maxX: Int,
            maxY: Int
        ): MapPosition3D {
            val board = boards[this.board]!!
            val newx = position.x - 1
            return if (newx < 0) {
                val n = adjacencyList(this, maxX, maxY)
                val newBoard = boards[n.board]!!
                if (newBoard[n.position] == WALL) this
                else n
            }
            else {
                if (board[Coordinate2D(newx, position.y)] == WALL) this
                else copy(position = Coordinate2D(newx, position.y))
            }
        }

        internal fun MapPosition3D.moveDown3D(
            boards: Map<Int, MutableMap<Coordinate2D, Char>>,
            maxX: Int,
            maxY: Int
        ): MapPosition3D {
            val board = boards[this.board]!!
            val newy = position.y + 1
            return if (newy == maxY) {
                val n = adjacencyList(this, maxX, maxY)
                val newBoard = boards[n.board]!!
                if (newBoard[n.position] == WALL) this
                else n
            }
            else {
                if (board[Coordinate2D(position.x, newy)] == WALL) this
                else copy(position = Coordinate2D(position.x, newy))
            }
        }

        internal fun MapPosition3D.moveUp3D(
            boards: Map<Int, MutableMap<Coordinate2D, Char>>,
            maxX: Int,
            maxY: Int
        ): MapPosition3D {
            val board = boards[this.board]!!
            val newy = position.y - 1
            return if (newy < 0) {
                val n = adjacencyList(this, maxX, maxY)
                val newBoard = boards[n.board]!!
                if (newBoard[n.position] == WALL) this
                else n
            }
            else {
                if (board[Coordinate2D(position.x, newy)] == WALL) this
                else copy(position = Coordinate2D(position.x, newy))
            }
        }
    }

}