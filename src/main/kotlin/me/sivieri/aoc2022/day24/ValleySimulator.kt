package me.sivieri.aoc2022.day24

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.common.Coordinate3D
import me.sivieri.aoc2022.day24.graph.ShortestPathWithConstraint
import me.sivieri.aoc2022.day24.graph.ShortestPathWithConstraintGraphPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

class ValleySimulator(data: String) {

    private val matrix = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val start = matrix[0].indexOf(EMPTY).let { Coordinate3D(it, 0, 0) }
    private val end = matrix.last().indexOf(EMPTY).let { Coordinate3D(it, matrix.size - 1, 0) }
    private val xmax = matrix[0].size
    private val ymax = matrix.size
    private val valley = Valley((0 until xmax)
        .flatMap { x ->
            (0 until ymax).mapNotNull { y ->
                if (matrix[y][x] in BlizzardDirection.entries.map { it.symbol })
                    Blizzard(Coordinate2D(x, y), BlizzardDirection.fromSymbol(matrix[y][x]), 1, 1, xmax - 1, ymax - 1)
                else null
            }
        },
        xmax,
        ymax
    )
    private val graph = SimpleDirectedWeightedGraph<Coordinate3D, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

    init {
        (0 until xmax).forEach { x ->
            (0 until ymax).forEach { y ->
                if (matrix[y][x] != WALL) {
                    (0 until WAITING_MAX).forEach { i -> graph.addVertex(Coordinate3D(x, y, i)) }
                }
            }
        }
        (0 until xmax).forEach { x ->
            (0 until ymax).forEach { y ->
                if (matrix[y][x] != WALL) {
                    val neighbors = setOf(
                        Coordinate2D((x - 1).coerceAtLeast(0), y),
                        Coordinate2D((x + 1).coerceAtMost(xmax - 1), y),
                        Coordinate2D(x, (y - 1).coerceAtLeast(0)),
                        Coordinate2D(x, (y + 1).coerceAtMost(ymax - 1))
                    ).minus(Coordinate2D(x, y))
                    neighbors.forEach { other ->
                        if (matrix[other.y][other.x] != WALL) {
                            (0 until WAITING_MAX).forEach { i ->
                                val source = Coordinate3D(x, y, i)
                                val dest = Coordinate3D(other.x, other.y, 0)
                                graph.addEdge(source, dest)
                                graph.setEdgeWeight(source, dest, 1.0)
                            }
                            (0 until WAITING_MAX).zipWithNext().forEach { (cur, next) ->
                                val source = Coordinate3D(x, y, cur)
                                val dest = Coordinate3D(x, y, next)
                                graph.addEdge(source, dest)
                                graph.setEdgeWeight(source, dest, 0.0)
                            }
                        }
                    }
                }
            }
        }
    }

    fun findThePath(debug: Boolean = false): Int {
        val sp = ShortestPathWithConstraint(debug, graph, valley, ::findCandidates)
        val path = sp.getPath(start, end)
        if (debug) {
            (path as ShortestPathWithConstraintGraphPath).getRealPath().forEach {
                println("Minute: ${it.minute}")
                println("Current: ${it.coordinate}")
                println()
            }
        }
        return path.vertexList.size
    }

    fun backAndForth(debug: Boolean = false): Int {
        // forth
        val sp = ShortestPathWithConstraint(debug, graph, valley, ::findCandidates)
        val path = sp.getPath(start, end)
        path as ShortestPathWithConstraintGraphPath
        if (debug) {
            path.getRealPath().forEach {
                println("Minute: ${it.minute}")
                println("Current: ${it.coordinate}")
                println()
            }
        }
        println("Forth: ${path.vertexList.size}")
        // back
        val backValley = path.getLatestValley()
        val backSP = ShortestPathWithConstraint(debug, graph, backValley, ::findCandidates)
        val backPath = backSP.getPath(end, start)
        backPath as ShortestPathWithConstraintGraphPath
        if (debug) {
            backPath.getRealPath().forEach {
                println("Minute: ${it.minute}")
                println("Current: ${it.coordinate}")
                println()
            }
        }
        println("Back: ${path.vertexList.size}")
        // forth
        val anotherValley = backPath.getLatestValley()
        val anotherSP = ShortestPathWithConstraint(debug, graph, anotherValley, ::findCandidates)
        val anotherPath = anotherSP.getPath(start, end)
        anotherPath as ShortestPathWithConstraintGraphPath
        if (debug) {
            anotherPath.getRealPath().forEach {
                println("Minute: ${it.minute}")
                println("Current: ${it.coordinate}")
                println()
            }
        }
        println("Forth: ${path.vertexList.size}")
        return path.vertexList.size + backPath.vertexList.size + anotherPath.vertexList.size
    }

    private fun findCandidates(
        c: Coordinate3D,
        valley: Valley
    ): List<Coordinate3D> {
        val targets = graph
            .outgoingEdgesOf(c)
            .map { graph.getEdgeTarget(it) }
        val filteredTargets = valley.freeLocations(targets)
        return filteredTargets
    }

    companion object {
        private const val WALL = '#'
        private const val EMPTY = '.'
        private const val WAITING_MAX = 3
    }

}