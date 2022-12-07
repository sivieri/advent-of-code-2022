package me.sivieri.aoc2022.day7

import me.sivieri.aoc2022.tail

class Interpreter(
    input: List<String>
) {

    val root = Directory("/", null, mutableListOf(), mutableListOf())
    private val commandsAndResponses: List<Pair<Command, List<List<String>>>> = run {
        val indexes = input
            .mapIndexed { index, s -> if (s.first() == '$') index else null }
            .filterNotNull()
            .zipWithNext()
        indexes
            .plus(Pair(indexes.last().second, input.size))
            .map { (start, nextStart) ->
                val block = input
                    .subList(start, nextStart)
                    .map { it.split(" ") }
                val command = Command(block.first().tail().first(), block.first().tail().tail())
                Pair(command, block.tail())
            }
    }

    init {
        var currentDir = root
        commandsAndResponses.forEach { (command, response) ->
            when (command.application) {
                "cd" -> {
                    currentDir = when (command.args.first()) {
                        "/" -> root
                        ".." -> currentDir.parent ?: throw IllegalArgumentException("Cannot go back from root!")
                        else -> currentDir.getSubdirectoryByName(command.args.first())
                            ?: throw java.lang.IllegalArgumentException("Directory ${command.args.first()} never created!")
                    }
                }
                "ls" -> {
                    response.forEach {
                        when (it[0]) {
                            "dir" -> currentDir.addSubdirIfNotExists(it[1])
                            else -> currentDir.addFileIfNotExists(it[1], it[0].toInt())
                        }
                    }
                }
                else -> throw IllegalArgumentException("Unknown command $command")
            }
        }
    }

    fun findTotalSizeOfAtMost(most: Int): Int = root
            .getAllSubdirectories()
            .map { it.size() }
            .filter { it <= most }
            .sum()

    fun findSizeOfSmallestToDelete(totalSize: Int, requiredSpace: Int): Int {
        val currentSize = root.size()
        val missingSize = requiredSpace - (totalSize - currentSize)
        return root
            .getAllSubdirectories()
            .map { it.size() }
            .filter { it >= missingSize }
            .minOf { it }
    }

    override fun toString(): String {
        return root.toString()
    }
}