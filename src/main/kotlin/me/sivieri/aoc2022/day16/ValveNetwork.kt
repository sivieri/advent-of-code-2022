package me.sivieri.aoc2022.day16

import me.sivieri.aoc2022.crossProduct
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import java.util.ArrayDeque
import java.util.Queue

class ValveNetwork(input: List<String>) {

    private val graph = SimpleGraph<Valve, DefaultEdge>(DefaultEdge::class.java)
    private val costs: Map<Valve, List<Pair<Valve, Double>>>

    init {
        val parsed = input.map {
            inputRegex
                .matchEntire(it)
                ?.destructured
                ?.let { (name, rate, _, _, _, valves) -> Triple(name, rate.toInt(), valves.split(", ")) }
                ?: throw IllegalArgumentException("Unable to parse $it")
        }
        val valves = parsed.map { (name, rate, _) ->
            Valve(name, rate)
        }
        valves.forEach { graph.addVertex(it) }
        parsed.forEach { (name, _, others) ->
            val origin = valves.find { it.name == name }!!
            val destinations = others.map { o -> valves.find { it.name == o }!! }
            destinations.forEach { graph.addEdge(origin, it) }
        }
        val allPairs = valves.crossProduct(valves).filter { it.first != it.second }
        val fw = FloydWarshallShortestPaths(graph)
        costs = allPairs
            .map { Triple(it.first, it.second, fw.getPathWeight(it.first, it.second)) }
            .groupBy { it.first }
            .map { it.key to it.value.map { Pair(it.second, it.third) } }
            .toMap()
    }

    fun getMaxFlow(): Int {
        val maxFlows = mutableMapOf<String, Int>()
        val queue = ArrayDeque<QueueStatus>()
        queue.addLast(QueueStatus(Valve("AA", 0), 30, 0, emptySet()))
        while (queue.isNotEmpty()) {

        }
        return maxFlows.values.max()
    }

    companion object {
        private val inputRegex = "Valve ([A-Z]{2}) has flow rate=([0-9]+); tunnel(s?) lead(s?) to valve(s?) ([A-Z, ]+)".toRegex()
    }

}