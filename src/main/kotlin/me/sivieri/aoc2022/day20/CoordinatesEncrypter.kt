package me.sivieri.aoc2022.day20

class CoordinatesEncrypter {

    fun decrypt(data: List<Int>): Int {
        val size = data.size
        var mixed = data.toTypedArray()
        data.forEach { v ->
            val cur = mixed.copyOf()
            val index = cur.indexOf(v)
            val newIndex = getNewIndex(index, size, v)
            println("Value $v from $index to $newIndex")
            mixed = Array(size) { 0 }
            var i = 0
            cur.forEach { v ->
                if (v != cur[index]) mixed[i++] = v
            }
            mixed = push(mixed.copyOf(), v, newIndex)
            println(mixed.joinToString(", "))
        }
        return mixed[getValue(FIRST_INDEX, size)] +
            mixed[getValue(SECOND_INDEX, size)] +
            mixed[getValue(THIRD_INDEX, size)]
    }

    private fun getNewIndex(
        initialPosition: Int,
        size: Int,
        value: Int
    ): Int {
        val res = (initialPosition + value) % size
        return if (res > 0) res
        else if (res < 0) size + res - 1
        else size - 1
    }

    private fun getValue(index: Int, size: Int): Int = index % size

    private fun push(
        current: Array<Int>,
        value: Int,
        index: Int
    ): Array<Int> {
        val result = Array(current.size) { 0 }
        (0 until index).forEach { i ->
            result[i] = current[i]
        }
        result[index] = value
        ((index + 1) until current.size).forEach { i ->
            result[i] = current[i - 1]
        }
        return result
    }

    companion object {
        private const val FIRST_INDEX = 1000
        private const val SECOND_INDEX = 2000
        private const val THIRD_INDEX = 3000
    }

}