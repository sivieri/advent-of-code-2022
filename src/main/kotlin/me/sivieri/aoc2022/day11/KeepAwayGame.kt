package me.sivieri.aoc2022.day11

import me.sivieri.aoc2022.multiplyBy

class KeepAwayGame(startingStatus: List<String>) {

    private val monkeys: Map<Int, Monkey> = startingStatus.associate {
        val monkey = Monkey.parse(it)
        monkey.id to monkey
    }
    private val monkeyInspections: MutableMap<Int, Int> = monkeys.keys.associateWith { 0 }.toMutableMap()

    fun play(rounds: Int) {
        (1..rounds).forEach { round ->
            monkeys.keys.sorted().forEach { id ->
                val monkey = monkeys[id]!!
                while (monkey.checkEvaluate()) {
                    val (item, receiver) = monkey.evaluateItem()
                    monkeys[receiver]!!.addItem(item)
                    monkeyInspections[id] = monkeyInspections[id]!! + 1
                }
            }
        }
    }

    fun calculateMonkeyBusiness(): Long = monkeyInspections.values.sortedDescending().take(2).multiplyBy { it.toLong() }

    override fun toString(): String = monkeys
        .keys
        .sorted()
        .joinToString("\n") { id ->
            "Monkey $id: " + monkeys[id]!!.items.joinToString(", ")
        }
}