package me.sivieri.aoc2022.day19

import me.sivieri.aoc2022.multiplyBy
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.jgrapht.traverse.DepthFirstIterator

class BlueprintSelectorViaRobot(
    data: List<String>,
    private val constructionPlan: ConstructionPlan
) {

    private val blueprints = data.map {
        val blueprint = Blueprint.parse(it)
        blueprint.id to blueprint
    }.toMap()

    fun calculateProduct(time: Int) = calculateBlueprintsValue(time).values.toList().multiplyBy { it.toLong() }

    private fun calculateBlueprintsValue(time: Int): Map<Int, Int> = blueprints.values.associate {
        it.id to calculateBlueprintValue(time, it.id)
    }

    fun calculateBlueprintValue(time: Int, id: Int): Int {
        val graph = SimpleDirectedGraph<ExtractionStatusV2, DefaultEdge>(DefaultEdge::class.java)
        graph.addVertex(ExtractionStatusV2()) // root
        val it = DepthFirstIterator(graph)
        while (it.hasNext()) {
            val node = it.next()
        }
        TODO()
    }

}