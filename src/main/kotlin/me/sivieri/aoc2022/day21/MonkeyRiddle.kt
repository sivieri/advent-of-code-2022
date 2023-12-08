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
        ops.forEach { op ->
            val (left, right) = op.second
            val leftNode = allVertex[left]!!
            val rightNode = allVertex[right]!!
            leftNode.side = RiddleSide.LEFT
            rightNode.side = RiddleSide.RIGHT
            tree.addEdge(op.first, leftNode)
            tree.addEdge(op.first, rightNode)
        }
    }

    fun findRootNumber(): Long {
        solve()
        return tree.vertexSet().find { it.label == ROOT }!!.value!!
    }

    fun findYelledNumber(): Long {
        solve()
        var root = tree.vertexSet().find { it.label == ROOT }!!
        var children = tree.outgoingEdgesOf(root).map { tree.getEdgeTarget(it) }
        var mine = children.find { child ->
            DepthFirstIterator(tree, child).forEach { ri ->
                if (ri.label == ME) return@find true
            }
            false
        }!!
        var other = children.find { it != mine }!!
        mine.value = other.value!!
        root = mine
        while (true) {
            if (root.label == ME) return root.value!!
            children = tree.outgoingEdgesOf(root).map { tree.getEdgeTarget(it) }
            mine = children.find { child ->
                DepthFirstIterator(tree, child).forEach { ri ->
                    if (ri.label == ME) return@find true
                }
                false
            }!!
            other = children.find { it != mine }!!
            val newValue = if (mine.side == RiddleSide.LEFT) {
                root.operation.calculateMissingValue(null, other.value, root.value!!)
            }
            else {
                root.operation.calculateMissingValue(other.value, null, root.value!!)
            }
            mine.value = newValue
            root = mine
        }
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
        private const val ME = "humn"
    }

}