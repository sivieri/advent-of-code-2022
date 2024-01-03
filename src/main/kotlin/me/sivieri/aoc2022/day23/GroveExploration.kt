package me.sivieri.aoc2022.day23

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.tail

class GroveExploration(data: String) {

    private val grove = Grove(data)
    private var elves: List<Elf>

    init {
        var counter = 1
        elves = grove.findAllPositions(ELF).map { c ->
            Elf(
                counter++,
                c,
                listOf(GroveDirection.NORTH, GroveDirection.SOUTH, GroveDirection.WEST, GroveDirection.EAST)
            )
        }
    }

    fun countEmptyGround(rounds: Int): Int {
        play(rounds)
        val minx = elves.minOf { it.position.x }
        val maxx = elves.maxOf { it.position.x }
        val miny = elves.minOf { it.position.y }
        val maxy = elves.maxOf { it.position.y }
        return grove
            .findAllPositions(EMPTY)
            .filter { it.x in minx..maxx && it.y in miny..maxy }
            .size
    }

    private fun play(rounds: Int) {
        var counter = 0
        do {
            println("Round ${counter++}")
            println(grove.stringRepresentation())
            println()
            // part 1
            roundPart1()
            elves.forEach { grove.setSymbol(it.position, EMPTY) }
            // part 2
            roundPart2()
            elves.forEach { grove.setSymbol(it.position, ELF) }
        } while (counter <= rounds)
    }

    private fun roundPart1() {
        elves = elves.map { elf ->
            val neighbors = findNeighbors(elf.position)
            if (neighbors.all { grove.getSymbol(it) == EMPTY }) elf
            else {
                elf.possibleDirections.fold(elf) { acc, d ->
                    if (acc.nextPosition != null) acc
                    else {
                        when (d) {
                            GroveDirection.NORTH -> {
                                val ns = findNorthNeighbors(elf.position)
                                if (ns.isNotEmpty() && ns.all { grove.getSymbol(it) == EMPTY })
                                    elf.copy(nextPosition = elf.position.copy(y = elf.position.y - 1))
                                else elf
                            }

                            GroveDirection.SOUTH -> {
                                val ns = findSouthNeighbors(elf.position)
                                if (ns.isNotEmpty() && ns.all { grove.getSymbol(it) == EMPTY })
                                    elf.copy(nextPosition = elf.position.copy(y = elf.position.y + 1))
                                else elf
                            }

                            GroveDirection.WEST -> {
                                val ns = findWestNeighbors(elf.position)
                                if (ns.isNotEmpty() && ns.all { grove.getSymbol(it) == EMPTY })
                                    elf.copy(nextPosition = elf.position.copy(x = elf.position.x - 1))
                                else elf
                            }

                            GroveDirection.EAST -> {
                                val ns = findEastNeighbors(elf.position)
                                if (ns.isNotEmpty() && ns.all { grove.getSymbol(it) == EMPTY })
                                    elf.copy(nextPosition = elf.position.copy(x = elf.position.x + 1))
                                else elf
                            }
                        }
                    }
                }
            }
        }
    }

    private fun roundPart2(): Boolean {
        var changed = false
        elves = elves.map { elf ->
            val nextDirections = elf.possibleDirections.tail().plus(elf.possibleDirections.first())
            if (elf.nextPosition != null) {
                if (elves.minus(elf).all { it.nextPosition != elf.nextPosition }) {
                    changed = true
                    elf.copy(position = elf.nextPosition, nextPosition = null, possibleDirections = nextDirections)
                }
                else elf.copy(possibleDirections = nextDirections, nextPosition = null)
            } else elf.copy(possibleDirections = nextDirections)
        }
        return changed
    }

    private fun findNeighbors(position: Coordinate2D): Set<Coordinate2D> =
        findNorthNeighbors(position) + findEastNeighbors(position) + findSouthNeighbors(position) + findWestNeighbors(position)

    private fun findNorthNeighbors(position: Coordinate2D): Set<Coordinate2D> = setOf(
        Coordinate2D(position.x - 1, position.y - 1),
        Coordinate2D(position.x, position.y - 1),
        Coordinate2D(position.x + 1, position.y - 1),
    ).minus(position)

    private fun findSouthNeighbors(position: Coordinate2D): Set<Coordinate2D> = setOf(
        Coordinate2D(position.x - 1, position.y + 1),
        Coordinate2D(position.x, position.y + 1),
        Coordinate2D(position.x + 1, position.y + 1)
    ).minus(position)

    private fun findWestNeighbors(position: Coordinate2D): Set<Coordinate2D> = setOf(
        Coordinate2D(position.x - 1, position.y - 1),
        Coordinate2D(position.x - 1, position.y),
        Coordinate2D(position.x - 1, position.y + 1),
    ).minus(position)

    private fun findEastNeighbors(position: Coordinate2D): Set<Coordinate2D> = setOf(
        Coordinate2D(position.x + 1, position.y - 1),
        Coordinate2D(position.x + 1, position.y ),
        Coordinate2D(position.x + 1, position.y + 1)
    ).minus(position)

    companion object {
        private const val EMPTY = '.'
        private const val ELF = '#'
    }

}