package me.sivieri.aoc2022.day15

import me.sivieri.aoc2022.common.Coordinate2D

class TunnelsMonitoring(input: List<String>) {

    private val sensors: List<Coordinate2D>
    private val beacons: List<Coordinate2D>
    private val sensorsWithBeacons: Map<Coordinate2D, Coordinate2D>
    private val minX: Int
    private val minY: Int
    private val maxX: Int
    private val maxY: Int
    private val maxDistance: Int

    init {
        sensorsWithBeacons = input.associate {
            inputRegex
                .matchEntire(it)
                ?.destructured
                ?.let { (sx, sy, bx, by) ->
                    Coordinate2D(sx.toInt(), sy.toInt()) to Coordinate2D(bx.toInt(), by.toInt())
                }
                ?: throw IllegalArgumentException("Unable to parse line $it")
        }
        sensors = sensorsWithBeacons.keys.toList()
        beacons = sensorsWithBeacons.values.toList()
        minX = sensors.union(beacons).minOf { it.x }
        minY = sensors.union(beacons).minOf { it.y }
        maxX = sensors.union(beacons).maxOf { it.x }
        maxY = sensors.union(beacons).maxOf { it.y }
        maxDistance = sensorsWithBeacons.maxOf { it.key.manhattanDistance(it.value) }
    }

    fun countEmptyPositions(index: Int): Int {
        val minX = this.minX - maxDistance
        val maxX = this.maxX + maxDistance
        val line = (minX..maxX).map { x ->
            if (sensors.contains(Coordinate2D(x, index))) SENSOR
            else if (beacons.contains(Coordinate2D(x, index))) BEACON
            else AIR
        }
        return (minX..maxX).count { x ->
            sensorsWithBeacons.any { (sensor, beacon) ->
                val d = sensor.manhattanDistance(beacon)
                val c = Coordinate2D(x, index)
                val manhattanDistance = c.manhattanDistance(sensor)
                line[x - minX] == AIR && manhattanDistance <= d || line[x - minX] == SENSOR
            }
        }
    }

    fun findDistressBeacon(min: Int, max: Int): Long {
        sensorsWithBeacons.entries.forEachIndexed { index, entry ->
            println("Sensor ${index + 1} of ${sensorsWithBeacons.size}")
            val d = entry.key.manhattanDistance(entry.value)
            val n = findAllNeighbors(entry.key, d + 1, min, max)
            val others = sensorsWithBeacons.filterNot { it.key == entry.key }
            val r = n.find { c ->
                others.all {
                    val coordinateDistance = c.manhattanDistance(it.key)
                    val beaconDistance = it.key.manhattanDistance(it.value)
                    coordinateDistance > beaconDistance
                }
            }
            if (r != null) return r.x * 4000000L + r.y
        }
        throw IllegalArgumentException("Not found")
    }

    fun findAllNeighbors(center: Coordinate2D, distance: Int, min: Int, max: Int): List<Coordinate2D> {
        val right = center.copy(x = center.x + distance)
        val left = center.copy(x = center.x - distance)
        val top = center.copy(y = center.y - distance)
        val bottom = center.copy(y = center.y + distance)
        val allNeighbors = (left.x..bottom.x).zip(left.y..bottom.y).map { Coordinate2D(it.first, it.second) }.union(
        (left.x..top.x).zip(left.y downTo top.y).map { Coordinate2D(it.first, it.second) }).union(
        (top.x..right.x).zip(top.y..right.y).map { Coordinate2D(it.first, it.second) }).union(
        (bottom.x..right.x).zip(bottom.y downTo right.y).map { Coordinate2D(it.first, it.second) })
        return allNeighbors
            .filterNot { sensors.contains(it) || beacons.contains(it) }
            .filter { it.x in min..max && it.y in min..max }
            .distinct()
    }

    fun stringRepresentation(): String =
        (minY..maxY).joinToString("\n") { y ->
            (minX..maxX).joinToString("") { x ->
                if (sensors.contains(Coordinate2D(x, y))) SENSOR
                else if (beacons.contains(Coordinate2D(x, y))) BEACON
                else AIR
            }
        }

    companion object {
        private const val SENSOR = "S"
        private const val BEACON = "B"
        private const val AIR = "."
        private val inputRegex = "Sensor at x=(-?[0-9]*), y=(-?[0-9]*): closest beacon is at x=(-?[0-9]*), y=(-?[0-9]*)".toRegex()
    }

}