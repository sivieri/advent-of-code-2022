package me.sivieri.aoc2022.day21

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.jgrapht.traverse.DepthFirstIterator

class MonkeyRiddle(data: String) {

    private val tree: Graph<RiddleInstance, DefaultEdge> = SimpleDirectedGraph(DefaultEdge::class.java)

    init {
        val lines = data.split("\n").filter { it.isNotBlank() }.map { it.split(":").map { it.trim() } }
        val numbers = lines
            .filter { ' ' !in it[1] }
            .map { RiddleInstance(it[0], it[1].toLong(), NoOp) }
        val ops = lines.filter { ' ' in it[1] }.map {
            val (first, op, second) = it[1].split(" ")
            Pair(
                RiddleInstance(it[0], null, RiddleOperation.operationFromSymbol(op[0])),
                listOf(first, second)
            )
        }
        val allVertex = (numbers + ops.map { it.first }).map { Pair(it.label, it) }.toMap()
        allVertex.values.forEach { tree.addVertex(it) }
        ops.forEach { op -> op.second.forEach { tree.addEdge(op.first, allVertex[it]!!) } }
    }

    fun findRootNumber(): Long {
        solve()
        return tree.vertexSet().find { it.label == ROOT }!!.value!!
    }

    private fun solve() {
        var changed = true
        var iteration = 1
        while (changed) {
            println("Iteration ${iteration++}")
            changed = false
            DepthFirstIterator(tree).forEach { ri ->
                if (ri.value == null) {
                    val values = tree.outgoingEdgesOf(ri).map { tree.getEdgeTarget(it).value }
                    if (values.all { it != null }) {
                        ri.value = ri.operation.execute(values[0]!!, values[1]!!)
                        changed = true
                    }
                }
            }
        }
    }

    companion object {
        private const val ROOT = "root"
    }

}