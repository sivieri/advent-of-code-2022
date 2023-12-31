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
        var current = MapPosition3D(start3D.first, start3D.second, MapDirection.RIGHT)
        val (bi, p, d) = current
        val board = board3D[bi]!!
        board[p] = d.symbol
        instructions.forEachIndexed { index, instruction ->
            if (printSteps) println("Iteration $index\nPosition $current\nInstruction $instruction\n${boardRepresentation3D()}\n")
            current = when (instruction) {
                is RotateClockwiseInstruction -> {
                    val next = current.copy(direction = current.direction.rotate(RotateClockwiseInstruction))
                    val (boardIndex, position, direction) = next
                    val board = board3D[boardIndex]!!
                    board[position] = direction.symbol
                    next
                }
                is RotateCounterClockwiseInstruction -> {
                    val next = current.copy(direction = current.direction.rotate(RotateCounterClockwiseInstruction))
                    val (boardIndex, position, direction) = next
                    val board = board3D[boardIndex]!!
                    board[position] = direction.symbol
                    next
                }
                is MoveInstruction -> findDestination3D(current, instruction)
            }
        }
        val (position, direction) = calculateFinalPosition(current)
        return 1000 * (position.y + 1) + 4 * (position.x + 1) + direction.facing
    }

    private fun findDestination3D(current: MapPosition3D, instruction: MoveInstruction): MapPosition3D =
        (1..instruction.move).fold(current) { acc, _ ->
            val next = when (acc.direction) {
                MapDirection.RIGHT -> acc.moveRight3D(board3D, maxX3D, maxY3D)
                MapDirection.LEFT -> acc.moveLeft3D(board3D, maxX3D, maxY3D)
                MapDirection.UP -> acc.moveUp3D(board3D, maxX3D, maxY3D)
                MapDirection.DOWN -> acc.moveDown3D(board3D, maxX3D, maxY3D)
            }
            val (boardIndex, position, direction) = next
            val board = board3D[boardIndex]!!
            board[position] = direction.symbol
            next
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

    /**
     * 1  2  3  4
     * 5  6  7  8
     * 9 10 11 12
     *
     * 3 board 1
     * 5 board 2
     * 6 board 3
     * 7 board 4
     * 11 board 5
     * 12 board 6
     */
    private fun boardRepresentation3D(): String {
        val matrix = Array(maxY) { Array(maxX) { NOTHING } }
        // 3
        (0 until maxX3D).forEach { x ->
            (0 until maxY3D).forEach { y ->
                matrix[y][x + 2 * maxX3D] = board3D[1]!![Coordinate2D(x, y)]!!
            }
        }
        // 5
        (0 until maxX3D).forEach { x ->
            (0 until maxY3D).forEach { y ->
                matrix[y + maxY3D][x] = board3D[2]!![Coordinate2D(x, y)]!!
            }
        }
        // 6
        (0 until maxX3D).forEach { x ->
            (0 until maxY3D).forEach { y ->
                matrix[y + maxY3D][x + maxX3D] = board3D[3]!![Coordinate2D(x, y)]!!
            }
        }
        // 7
        (0 until maxX3D).forEach { x ->
            (0 until maxY3D).forEach { y ->
                matrix[y + maxY3D][x + 2 * maxX3D] = board3D[4]!![Coordinate2D(x, y)]!!
            }
        }
        // 11
        (0 until maxX3D).forEach { x ->
            (0 until maxY3D).forEach { y ->
                matrix[y + 2 * maxY3D][x + 2 * maxX3D] = board3D[5]!![Coordinate2D(x, y)]!!
            }
        }
        // 12
        (0 until maxX3D).forEach { x ->
            (0 until maxY3D).forEach { y ->
                matrix[y + 2 * maxY3D][x + 3 * maxX3D] = board3D[6]!![Coordinate2D(x, y)]!!
            }
        }
        return (0 until maxY)
            .joinToString("\n") { y ->
                (0 until maxX).joinToString("") { x ->
                    matrix[y][x].toString()
                }
            }
    }

    companion object {
        internal const val EMPTY = '.'
        internal const val WALL = '#'
        internal const val NOTHING = ' '
    }

}