package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.common.Left
import me.sivieri.aoc2022.common.Right

object ListsParser {

    fun parse(line: String): ListsElement = parse0(line, 0, true).second

    private fun parse0(line: String, index: Int, isRoot: Boolean): Pair<Int, ListsElement> {
        val root = ListsElement(mutableMapOf())
        var i = index
        var element = 0
        var buffer = StringBuffer()
        while (i < line.length) {
            when (line[i]) {
                ',' -> {
                    if (buffer.isNotEmpty()) root.elements[element++] = Left(buffer.toString().toInt())
                    buffer = StringBuffer()
                }
                '[' -> {
                    val (newIndex, e) = parse0(line, i + 1, false)
                    root.elements[element++] = Right(e)
                    i = newIndex
                }
                ']' -> {
                    if (buffer.isNotEmpty()) root.elements[element] = Left(buffer.toString().toInt())
                    return if (isRoot) Pair(i, (root.elements[0]!! as Right).value) else Pair(i, root)
                }
                else -> {
                    buffer.append(line[i])
                }
            }
            i++
        }
        return if (isRoot) Pair(i, (root.elements[0]!! as Right).value) else Pair(i, root)
    }

}