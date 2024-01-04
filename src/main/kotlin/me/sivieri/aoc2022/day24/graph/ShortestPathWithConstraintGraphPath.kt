package me.sivieri.aoc2022.day24.graph

import me.sivieri.aoc2022.common.Coordinate3D
import me.sivieri.aoc2022.day24.Valley
import org.jgrapht.Graph
import org.jgrapht.GraphPath
import org.jgrapht.graph.DefaultWeightedEdge

class ShortestPathWithConstraintGraphPath(
    private val graph: Graph<Coordinate3D, DefaultWeightedEdge>,
    private val vertexAccumulator: VertexAccumulator
): GraphPath<Coordinate3D, DefaultWeightedEdge> {
    override fun getGraph(): Graph<Coordinate3D, DefaultWeightedEdge> = graph

    override fun getStartVertex(): Coordinate3D = vertexAccumulator.vertices.first().coordinate

    override fun getEndVertex(): Coordinate3D = vertexAccumulator.vertices.last().coordinate

    override fun getEdgeList(): MutableList<DefaultWeightedEdge> = throw NotImplementedError("Unsupported")

    override fun getVertexList(): MutableList<Coordinate3D> = vertexAccumulator.vertices.map { it.coordinate }.toMutableList()

    override fun getWeight(): Double = vertexAccumulator.weight.toDouble()

    fun getRealPath(): List<VisitedCell> = vertexAccumulator.vertices

    fun getLatestValley(): Valley = vertexAccumulator.valley
}
