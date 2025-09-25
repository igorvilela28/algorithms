package stanford.course02.dijkstra

import helper.getLines
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DijkstraTest {

    @Test
    fun testDijkstra() {

        val adjMap = buildAdjMap()

        val distances = Dijkstra(adjMap).getShortestPaths(1)

        val destinations = listOf(7,37,59,82,99,115,133,165,188,197)

        val result = mutableListOf<Int>()
        for (dest in destinations) {
            result.add(distances[dest-1])
        }

        val expected = listOf(2599, 2610, 2947, 2052, 2367, 2399, 2029, 2442, 2505, 3068)

        assertEquals(expected, result)
    }

    private fun buildAdjMap(): Map<Int, List<Edge>> {
        val lines = getLines("/src/main/kotlin/stanford/course02/dijkstra/input.txt")

        val adjMap = mutableMapOf<Int, List<Edge>>()

        for (line in lines) {

            val splitted = line.split("\t").filter { it.isNotBlank() }

            val source = splitted[0].toInt()

            val edges = mutableListOf<Edge>()

            for (i in 1 until splitted.size) {
                val split = splitted[i].split(",")
                val destination = split[0].toInt()
                val weight = split[1].toInt()
                val edge = Edge(source = source, destination = destination, weight = weight)
                edges.add(edge)
            }

            adjMap.put(source, edges)
        }

        return adjMap
    }
}