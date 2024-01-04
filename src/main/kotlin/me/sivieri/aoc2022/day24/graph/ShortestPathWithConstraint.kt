package me.sivieri.aoc2022.day24.graph

import me.sivieri.aoc2022.common.Coordinate3D
import me.sivieri.aoc2022.day24.Valley
import org.jgrapht.GraphPath
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph
import java.util.*

class ShortestPathWithConstraint(
    private val debug: Boolean,
    private val graph: SimpleDirectedWeightedGraph<Coordinate3D, DefaultWeightedEdge>,
    private val valley: Valley,
    private val findNeighbors: (Coordinate3D, Valley) -> List<Coordinate3D>
): ShortestPathAlgorithm<Coordinate3D, DefaultWeightedEdge> {
    override fun getPaths(source: Coordinate3D): ShortestPathAlgorithm.SingleSourcePaths<Coordinate3D, DefaultWeightedEdge> = throw NotImplementedError("Unavailable")

    override fun getPath(source: Coordinate3D, sink: Coordinate3D): GraphPath<Coordinate3D, DefaultWeightedEdge> =
        ShortestPathWithConstraintGraphPath(graph, findThePath(source, sink))

    override fun getPathWeight(source: Coordinate3D, sink: Coordinate3D): Double = findThePath(source, sink).weight.toDouble()

    private fun findThePath(source: Coordinate3D, sink: Coordinate3D): VertexAccumulator {
        val visited = mutableMapOf<VisitedCell, Int>()
        visited[VisitedCell(source, 0)] = 0
        val queue = PriorityQueue<VertexAccumulator>()
        queue.add(VertexAccumulator(source, 0, emptyList(), 0, valley))
        while (queue.size > 0) {
            if (debug) println("Queue size: ${queue.size}")
            if (queue.peek().vertex == sink) break
            val top = queue.remove()
            val updatedValley = top.valley.move()
            if (debug) println("Top coordinate: ${top.vertex}")
            val nextVertices = findNeighbors(top.vertex, top.valley)
            nextVertices.forEach { other ->
                val edgeWeight = graph.getEdgeWeight(graph.getEdge(top.vertex, other))
                val newCost = top.weight + edgeWeight
                val cell = VisitedCell(other, top.minutes + 1)
                if (cell !in visited.keys || newCost < visited[cell]!!) {
                    visited[cell] = newCost.toInt()
                    val vcw = VertexAccumulator(
                        cell.coordinate,
                        top.weight + edgeWeight.toInt(),
                        top.vertices.plus(cell),
                        cell.minute,
                        updatedValley
                    )
                    queue.add(vcw)
                }
            }
        }
        return queue.remove()
    }

}
