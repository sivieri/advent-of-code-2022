package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.common.Coordinate2D

data class MapPosition3D(
    val board: Int,
    val position: Coordinate2D,
    val direction: MapDirection
) {
    fun moveRight3D(
        boards: Map<Int, MutableMap<Coordinate2D, Char>>,
        maxX: Int,
        maxY: Int
    ): MapPosition3D {
        val board = boards[this.board]!!
        val newPosition = this.copy(position = position.copy(x = position.x + 1))
        return if (newPosition.position.x == maxX) {
            val n = adjacencyList(this, maxX, maxY)
            val newBoard = boards[n.board]!!
            if (newBoard[n.position] == MonkeyMap.WALL) this
            else n
        }
        else {
            if (board[Coordinate2D(newPosition.position.x, position.y)] == MonkeyMap.WALL) this
            else newPosition
        }
    }

    fun moveLeft3D(
        boards: Map<Int, MutableMap<Coordinate2D, Char>>,
        maxX: Int,
        maxY: Int
    ): MapPosition3D {
        val board = boards[this.board]!!
        val newPosition = this.copy(position = position.copy(x = position.x - 1))
        return if (newPosition.position.x < 0) {
            val n = adjacencyList(this, maxX, maxY)
            val newBoard = boards[n.board]!!
            if (newBoard[n.position] == MonkeyMap.WALL) this
            else n
        }
        else {
            if (board[Coordinate2D(newPosition.position.x, position.y)] == MonkeyMap.WALL) this
            else newPosition
        }
    }

    fun moveDown3D(
        boards: Map<Int, MutableMap<Coordinate2D, Char>>,
        maxX: Int,
        maxY: Int
    ): MapPosition3D {
        val board = boards[this.board]!!
        val newPosition = this.copy(position = position.copy(y = position.y + 1))
        return if (newPosition.position.y == maxY) {
            val n = adjacencyList(this, maxX, maxY)
            val newBoard = boards[n.board]!!
            if (newBoard[n.position] == MonkeyMap.WALL) this
            else n
        }
        else {
            if (board[Coordinate2D(position.x, newPosition.position.y)] == MonkeyMap.WALL) this
            else newPosition
        }
    }

    fun moveUp3D(
        boards: Map<Int, MutableMap<Coordinate2D, Char>>,
        maxX: Int,
        maxY: Int
    ): MapPosition3D {
        val board = boards[this.board]!!
        val newPosition = this.copy(position = position.copy(y = position.y - 1))
        return if (newPosition.position.y < 0) {
            val n = adjacencyList(this, maxX, maxY)
            val newBoard = boards[n.board]!!
            if (newBoard[n.position] == MonkeyMap.WALL) this
            else n
        }
        else {
            if (board[Coordinate2D(position.x, newPosition.position.y)] == MonkeyMap.WALL) this
            else newPosition
        }
    }

    companion object {
        private fun adjacencyList(
            c: MapPosition3D,
            mx: Int,
            my: Int
        ): MapPosition3D = when (Pair(c.board, c.direction)) {
            Pair(1, MapDirection.UP) -> MapPosition3D(2, Coordinate2D(mx - c.position.x - 1, 0), MapDirection.DOWN)
            Pair(1, MapDirection.DOWN) -> MapPosition3D(4, Coordinate2D(c.position.x, 0), MapDirection.DOWN)
            Pair(1, MapDirection.LEFT) -> MapPosition3D(3, Coordinate2D(c.position.y, 0), MapDirection.DOWN)
            Pair(1, MapDirection.RIGHT) -> MapPosition3D(6, Coordinate2D(mx - 1, my - c.position.y - 1), MapDirection.LEFT)
            Pair(2, MapDirection.UP) -> MapPosition3D(1, Coordinate2D(mx - c.position.x - 1, 0), MapDirection.DOWN)
            Pair(2, MapDirection.DOWN) -> MapPosition3D(5, Coordinate2D(mx - c.position.x - 1, my - 1), MapDirection.UP)
            Pair(2, MapDirection.LEFT) -> MapPosition3D(6, Coordinate2D(my - c.position.y - 1, my - 1), MapDirection.UP)
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
            Pair(5, MapDirection.DOWN) -> MapPosition3D(2, Coordinate2D(mx - c.position.x - 1, my - 1), MapDirection.UP)
            Pair(5, MapDirection.LEFT) -> MapPosition3D(3, Coordinate2D(my - c.position.y - 1, my - 1), MapDirection.UP)
            Pair(5, MapDirection.RIGHT) -> MapPosition3D(6, Coordinate2D(0, c.position.y), MapDirection.RIGHT)
            Pair(6, MapDirection.UP) -> MapPosition3D(4, Coordinate2D(mx - 1, mx - c.position.x - 1), MapDirection.LEFT)
            Pair(6, MapDirection.DOWN) -> MapPosition3D(2, Coordinate2D(0, mx - c.position.x - 1), MapDirection.RIGHT)
            Pair(6, MapDirection.LEFT) -> MapPosition3D(5, Coordinate2D(mx - 1, c.position.y), MapDirection.LEFT)
            Pair(6, MapDirection.RIGHT) -> MapPosition3D(1, Coordinate2D(mx - 1, my - c.position.y - 1), MapDirection.LEFT)
            else -> throw IllegalArgumentException("Illegal position $c")
        }
    }
}
