package me.sivieri.aoc2022.day12

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import java.lang.IllegalStateException

class HillClimbing(input: List<String>) {

    private val matrix = input.map { it.toList() }
    private val maxX = matrix.first().size
    private val maxY = matrix.size
    private val graph = SimpleDirectedGraph<HillCell, DefaultEdge>(DefaultEdge::class.java)
    private var startCell: HillCell? = null
    private var endCell: HillCell? = null

    init {
        // add all vertices
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val cell = createHillCell(y, x)
                if (cell.isStart) startCell = cell.copy()
                if (cell.isEnd) endCell = cell.copy()
                graph.addVertex(cell)
            }
        }
        // add all edges
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val cell = createHillCell(y, x)
                val neighbors = getNeighbors(cell)
                neighbors.forEach { neighbor ->
                    if (neighbor.id <= cell.id + 1) graph.addEdge(cell, neighbor)
                }
            }
        }
    }

    private fun createHillCell(y: Int, x: Int) = HillCell(
        matrix[y][x].let { if (it == 'S') 'a' else if (it == 'E') 'z' else it },
        x,
        y,
        matrix[y][x] == 'S',
        matrix[y][x] == 'E'
    )

    private fun getNeighbors(cell: HillCell): List<HillCell> {
        val up = if (cell.y == 0) null else createHillCell(cell.y - 1, cell.x)
        val down = if (cell.y == maxY - 1) null else createHillCell(cell.y + 1, cell.x)
        val left = if (cell.x == 0) null else createHillCell(cell.y, cell.x - 1)
        val right = if (cell.x == maxX - 1) null else createHillCell(cell.y, cell.x + 1)
        return listOfNotNull(up, down, left, right)
    }

    fun findShortestPathStepNumber(): Int = DijkstraShortestPath.findPathBetween(graph, startCell, endCell).edgeList.size

}