package stanford.course02.dijkstra

import java.util.PriorityQueue


private const val EmptyPathDistance = 1_000_000

internal data class Edge(
    val source: Int,
    val destination: Int,
    val weight: Int
)

internal class Dijkstra(
    private val graph: Map<Int, List<Edge>>
) {

    fun getShortestPaths(source: Int): IntArray {

        val visited = BooleanArray(graph.size) { false }
        visited[source-1] = true

        val distances = IntArray(graph.size, { EmptyPathDistance })
        distances[source-1] = 0

        val heap = PriorityQueue<Edge>(compareBy { getScore(distances, it) })

        val edges = graph[source] ?: emptyList()

        // starts the heap with all edges from source
        for (edge in edges) {
            heap.add(edge)
        }

        while (!heap.isEmpty()) {

            val edge = heap.poll()

            if (visited[edge.source - 1] && !visited[edge.destination - 1]) {
                visited[edge.destination - 1] = true
                distances[edge.destination - 1] = getScore(distances, edge)
                val neighbors = graph[edge.destination] ?: emptyList()
                for (neighbor in neighbors) {
                    if (!visited[neighbor.destination - 1]) {
                        heap.add(neighbor)
                    }
                }
            }
        }

        return distances
    }

    private fun getScore(distances: IntArray, edge: Edge): Int {
        val score = distances[edge.source-1] + edge.weight
        return score
    }
}