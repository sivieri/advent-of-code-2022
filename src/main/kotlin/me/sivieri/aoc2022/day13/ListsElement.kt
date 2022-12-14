package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.common.Either
import me.sivieri.aoc2022.common.Left
import me.sivieri.aoc2022.common.Right
import kotlin.math.max

data class ListsElement(
    val elements: MutableMap<Int, Either<Int, ListsElement>>
): Comparable<ListsElement> {

    override fun compareTo(other: ListsElement): Int =
        if (this == other) 0
        else if (checkOrder(this, other)) -1
        else 1

    companion object {
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

        fun checkOrder(a: ListsElement, b: ListsElement): Boolean = checkOrder0(a, b)!!

        private fun checkOrder0(a: ListsElement, b: ListsElement): Boolean? {
            val max = max(a.elements.size, b.elements.size)
            (0 until max).forEach { i ->
                if (a.elements.size <= i) return true
                else if (b.elements.size <= i) return false
                else {
                    val a1 = a.elements[i]!!
                    val b1 = b.elements[i]!!
                    when {
                        a1 is Left && b1 is Left -> {
                            if (a1.value < b1.value) return true
                            else if (a1.value > b1.value) return false
                        }
                        a1 is Right && b1 is Right -> {
                            val v = checkOrder0(a1.value, b1.value)
                            if (v != null) return v
                        }
                        a1 is Left && b1 is Right -> {
                            val v = checkOrder0(ListsElement(mutableMapOf(0 to a1)), b1.value)
                            if (v != null) return v
                        }
                        a1 is Right && b1 is Left -> {
                            val v = checkOrder0(a1.value, ListsElement(mutableMapOf(0 to b1)))
                            if (v != null) return v
                        }
                    }
                }
            }
            return null
        }
    }

}
