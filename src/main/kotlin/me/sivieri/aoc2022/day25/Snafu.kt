package me.sivieri.aoc2022.day25

import kotlin.math.pow

data class Snafu(
    val value: String
) {
    fun toInt(): Int = value
        .toList()
        .reversed()
        .mapIndexed { pos, c ->
            SYMBOLS[c]!! * BASE.pow(pos).toInt()
        }
        .sum()

    fun toLong(): Long = value
        .toList()
        .reversed()
        .mapIndexed { pos, c ->
            SYMBOLS[c]!! * BASE.pow(pos).toLong()
        }
        .sum()

    companion object {
        internal const val BASE = 5.0
        internal val SYMBOLS = mapOf(
            '=' to -2,
            '-' to -1,
            '0' to 0,
            '1' to 1,
            '2' to 2
        )
    }

    override fun toString(): String = value
}

fun Int.toSnafu(): Snafu = this.toLong().toSnafu()

fun Long.toSnafu(): Snafu {
    val builder = StringBuilder("")
    val digits = Snafu.SYMBOLS.keys.joinToString("")
    val zeroOffset = digits.indexOf('0')
    var num = this
    do {
        val shiftedIndex = ((num + zeroOffset) % digits.length).toInt()
        builder.append(digits[shiftedIndex])
        if (shiftedIndex < zeroOffset) num += digits.length
        num = num.floorDiv(digits.length)
    } while (num > 0)
    return Snafu(builder.toString().toList().reversed().joinToString(""))
}
