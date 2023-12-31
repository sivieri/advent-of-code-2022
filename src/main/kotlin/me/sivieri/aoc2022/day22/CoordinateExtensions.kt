package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.common.Coordinate2D

internal fun Coordinate2D.moveRight(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
    val minX = (0..this.x).dropWhile { board[Coordinate2D(it, this.y)] == MonkeyMap.NOTHING }.first()
    val minC = Coordinate2D(minX, this.y)
    val x = (this.x + 1) % limit
    val c = Coordinate2D(x, this.y)
    return when {
        board[c] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[minC] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[minC] != MonkeyMap.WALL -> minC
        else -> c
    }
}

internal fun Coordinate2D.moveLeft(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
    val maxX = ((limit - 1) downTo this.x).dropWhile { board[Coordinate2D(it, this.y)] == MonkeyMap.NOTHING }.first()
    val maxC = Coordinate2D(maxX, this.y)
    val x = if (this.x - 1 < 0) limit - 1 else this.x - 1
    val c = Coordinate2D(x, this.y)
    return when {
        board[c] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[maxC] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[maxC] != MonkeyMap.WALL -> maxC
        else -> c
    }
}

internal fun Coordinate2D.moveDown(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
    val minY = (0..this.y).dropWhile { board[Coordinate2D(this.x, it)] == MonkeyMap.NOTHING }.first()
    val minC = Coordinate2D(this.x, minY)
    val y = (this.y + 1) % limit
    val c = Coordinate2D(this.x, y)
    return when {
        board[c] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[minC] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[minC] != MonkeyMap.WALL -> minC
        else -> c
    }
}

internal fun Coordinate2D.moveUp(board: Map<Coordinate2D, Char>, limit: Int): Coordinate2D {
    val maxY = ((limit - 1) downTo this.y).dropWhile { board[Coordinate2D(this.x, it)] == MonkeyMap.NOTHING }.first()
    val maxC = Coordinate2D(this.x, maxY)
    val y = if (this.y - 1 < 0) limit - 1 else this.y - 1
    val c = Coordinate2D(this.x, y)
    return when {
        board[c] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[maxC] == MonkeyMap.WALL -> this
        board[c] == MonkeyMap.NOTHING && board[maxC] != MonkeyMap.WALL -> maxC
        else -> c
    }
}
