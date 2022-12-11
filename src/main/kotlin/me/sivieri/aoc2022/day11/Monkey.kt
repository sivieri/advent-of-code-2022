package me.sivieri.aoc2022.day11

import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.IllegalStateException
import java.util.*

data class Monkey(
    val id: Int,
    val items: ArrayDeque<Long>,
    val operation: String,
    val test: Long,
    val trueResponse: Int,
    val falseResponse: Int
) {

    fun addItem(item: Long) {
        items.addLast(item)
    }

    fun evaluateItem(worryDivider: Int?, mcm: Long): Pair<Long, Int> {
        if (items.isEmpty()) throw IllegalStateException("Cannot evaluate without items")
        val item = items.removeFirst()
        val result = performOperation(item)
        val worryLevel = if (worryDivider != null) result / worryDivider
        else result % mcm
        return if (worryLevel % test == 0L) Pair(worryLevel, trueResponse)
        else Pair(worryLevel, falseResponse)
    }

    fun checkEvaluate(): Boolean = !items.isEmpty()

    private fun performOperation(item: Long): Long {
        val expression = ExpressionBuilder(operation)
            .variable("old")
            .build()
            .setVariable("old", item.toDouble())
        return expression.evaluate().toLong()
    }

    companion object {
        private val idRegex = "Monkey ([0-9]):".toRegex()
        private val itemsReges = "Starting items: ([0-9, ]*)".toRegex()
        private val operationRegex = "Operation: new = ([a-z0-9*+ ]*)".toRegex()
        private val testRegex = "Test: divisible by ([0-9]*)".toRegex()
        private val trueResponseRegex = "If true: throw to monkey ([0-9])".toRegex()
        private val falseResponseRegex = "If false: throw to monkey ([0-9])".toRegex()

        fun parse(repr: String): Monkey {
            val lines = repr.split("\n").map { it.trim() }.filter { it.isNotBlank() }
            if (lines.size != 6) throw IllegalArgumentException("String $repr is missing some lines")
            val id = idRegex
                .matchEntire(lines[0])
                ?.destructured
                ?.let { (id) -> id.toInt() }
                ?: throw IllegalArgumentException("Unable to parse id from ${lines[0]}")
            val items = itemsReges
                .matchEntire(lines[1])
                ?.destructured
                ?.let { (items) -> items.split(", ").map { it.toLong() } }
                ?: throw IllegalArgumentException("Unable to parse items from ${lines[1]}")
            val operation = operationRegex
                .matchEntire(lines[2])
                ?.destructured
                ?.let { (op) -> op }
                ?: throw IllegalArgumentException("Unable to parse operation from ${lines[2]}")
            val test = testRegex
                .matchEntire(lines[3])
                ?.destructured
                ?.let { (test) -> test.toLong() }
                ?: throw IllegalArgumentException("Unable to parse test from ${lines[3]}")
            val trueResponse = trueResponseRegex
                .matchEntire(lines[4])
                ?.destructured
                ?.let { (tr) -> tr.toInt() }
                ?: throw IllegalArgumentException("Unable to parse true response from ${lines[4]}")
            val falseResponse = falseResponseRegex
                .matchEntire(lines[5])
                ?.destructured
                ?.let { (fr) -> fr.toInt() }
                ?: throw IllegalArgumentException("Unable to parse false response from ${lines[5]}")
            return Monkey(
                id,
                ArrayDeque(items),
                operation,
                test,
                trueResponse,
                falseResponse
            )
        }

    }

}