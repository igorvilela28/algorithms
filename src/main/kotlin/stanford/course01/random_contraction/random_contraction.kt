package stanford.course01.random_contraction

import java.io.File
import java.lang.Exception
import java.nio.file.Paths
import kotlin.random.Random

internal fun main() {
    val vertexes = readVertexes()
    randomContraction(vertexes.toMutableList())
}

internal fun readVertexes(): List<Vertex> {
    val path = Paths.get("").toAbsolutePath()
        .toString() + "/src/main/kotlin/stanford/course01/random_contraction/graph.txt"
    val file = File(path)
    val vertexes = mutableListOf<Vertex>()
    file.readLines().forEach {
        val lines = it
            .split("\t")
            .map { it.toInt() }

        val vertexId = lines.first()
        val vertexEdges = lines.drop(1)
        val vertex = Vertex(
            id = vertexId,
            edges = vertexEdges
        )
        vertexes.add(vertex)
    }
    return vertexes
}

internal fun randomContraction(vertexes: MutableList<Vertex>): List<Vertex> {

    val edges = vertexes.generateEdges()
    val randomIdSeed = vertexes.size + 2

    while (vertexes.size > 2) {

        try {
            val randomEdgeIndex = Random.nextInt(until = edges.size)
            val edge = edges[randomEdgeIndex]

            val vertex1Id = edge.first
            val vertex2Id = edge.second
            val vertex1 = vertexes.first { it.id == vertex1Id }
            val vertex2 = vertexes.first { it.id == vertex2Id }

            // removing combined vertexes edges loops
            val v1Edges = vertex1.edges.filter { it != vertex2Id }
            val v2Edges = vertex2.edges.filter { it != vertex1Id }

            val combinedVertexId = Random.nextInt(from = randomIdSeed, until = Integer.MAX_VALUE)
            val combinedVertexEdges = v1Edges + v2Edges
            val combinedVertex = Vertex(id = combinedVertexId, edges = combinedVertexEdges)

            vertexes.remove(vertex1)
            vertexes.remove(vertex2)

            // replace adjacency on remaining vertexes
            replaceAdjacencyEdges(vertexes, vertex1Id, vertex2Id, combinedVertexId)

            vertexes.add(combinedVertex)

            // regenerate current edges
            edges.apply {
                clear()
                addAll(vertexes.generateEdges())
            }
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }

    return vertexes
}

private fun replaceAdjacencyEdges(
    vertexes: MutableList<Vertex>,
    vertex1Id: Int,
    vertex2Id: Int,
    combinedVertexId: Int,
) {
    for (i in 0 until vertexes.size) {
        val v = vertexes[i]
        val vEdges = v.edges.toMutableList()

        if (vEdges.contains(vertex1Id)) {
            vEdges.replaceAll { if (it == vertex1Id) combinedVertexId else it }
        }

        if (vEdges.contains(vertex2Id)) {
            vEdges.replaceAll { if (it == vertex2Id) combinedVertexId else it }
        }

        if (vEdges != v.edges) {
            vertexes.removeAt(i)
            vertexes.add(i, Vertex(id = v.id, edges = vEdges))
        }
    }
}

data class Vertex(
    val id: Int,
    val edges: List<Int>
)


private fun List<Vertex>.generateEdges(): MutableList<Pair<Int, Int>> {
    val edges = mutableListOf<Pair<Int, Int>>()
    for (vertex in this) {
        for (edge in vertex.edges) {
            // avoid adding edges twice
            if (!edges.contains(edge to vertex.id)) {
                edges.add(vertex.id to edge)
            }
        }
    }
    return edges
}