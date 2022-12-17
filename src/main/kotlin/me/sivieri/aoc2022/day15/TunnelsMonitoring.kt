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

    fun findDistressBeacon(min: Int, max: Int): Int {
        val data = sensorsWithBeacons.flatMap {
            listOf(it.key to SENSOR, it.value to BEACON)
        }.toMap()
        val distances = sensorsWithBeacons.mapValues { it.key.manhattanDistance(it.value) }
        val result = (min..max).firstNotNullOf { y ->
            if (y % 10000 == 0) println(y)
            var found = false
            var cur = min
            while (cur <= max && !found) {
                val c = Coordinate2D(cur, y)
                found = data[c] == null && distances.all { (sensor, d) ->
                    val manhattanDistance = c.manhattanDistance(sensor)
                    data[c] == null && manhattanDistance > d
                }
                cur++
            }
            if (found) Coordinate2D(cur - 1, y)
            else null
        }
        return result.x * 4000000 + result.y
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