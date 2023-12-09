package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.common.Coordinate2D

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
        var current = Pair(start, MapDirection.RIGHT)
        instructions.forEachIndexed { index, instruction ->
            board[current.first] = current.second.symbol
            if (printSteps) println("Iteration $index\nInstruction $current\n${boardRepresentation()}\n")
            when (instruction) {
                is RotateClockwiseInstruction -> current = Pair(current.first, current.second.rotate(RotateClockwiseInstruction))
                is RotateCounterClockwiseInstruction -> current = Pair(current.first, current.second.rotate(RotateCounterClockwiseInstruction))
                is MoveInstruction -> {
                    val destination = findDestination3D(current, instruction)
                    current = Pair(destination, current.second)
                }
            }
        }
        return 1000 * (current.first.y + 1) + 4 * (current.first.x + 1) + current.second.facing
    }

    private fun findDestination3D(current: Pair<Coordinate2D, MapDirection>, instruction: MoveInstruction): Coordinate2D =
        (1..instruction.move).fold(current.first) { acc, _ ->
            when (current.second) {
                MapDirection.RIGHT -> acc.moveRight3D(board, maxX)
                MapDirection.LEFT -> acc.moveLeft3D(board, maxX)
                MapDirection.UP -> acc.moveUp3D(board, maxY)
                MapDirection.DOWN -> acc.moveDown3D(board, maxY)
            }
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

        internal fun Coordinate2D.moveRight3D(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            TODO()
        }

        internal fun Coordinate2D.moveLeft3D(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            TODO()
        }

        internal fun Coordinate2D.moveDown3D(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            TODO()
        }

        internal fun Coordinate2D.moveUp3D(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
            TODO()
        }
    }

}