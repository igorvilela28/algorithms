package stanford.course01.random_contraction

import java.io.File
import java.lang.Exception
import java.nio.file.Paths
import kotlin.random.Random

internal fun main() {
    randomContraction()
}

internal fun randomContraction() {

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
    println(vertexes)

    // para testes, vou gerar uma outra

    /*vertexes.clear()
    vertexes.add(Vertex(id = 1, edges = listOf(2, 3)))
    vertexes.add(Vertex(id = 2, edges = listOf(1, 3, 4)))
    vertexes.add(Vertex(id = 3, edges = listOf(1, 2, 4)))
    vertexes.add(Vertex(id = 4, edges = listOf(2, 3)))*/

    var edges = vertexes.generateEdges()
    println(edges)

    val vertexesSize = vertexes.size

    while (vertexes.size > 2) {

        val randomEdgeIndex = Random.nextInt(until = edges.size)
        val edge = edges[randomEdgeIndex]
        // contraction
        //edges.remove(edge)

        val vertex1Id = edge.first
        val vertex2Id = edge.second

        try {
            val vertex1 = vertexes.first { it.id == vertex1Id }
            val vertex2 = vertexes.first { it.id == vertex2Id }
            //println("$vertex1 - $vertex2")


            val v1Edges = vertex1.edges.filter { it != vertex2Id }
            val v2Edges = vertex2.edges.filter { it != vertex1Id }

            val combinedVertexId = Random.nextInt(from = vertexesSize + 2, until = Integer.MAX_VALUE)
            val combinedVertexEdges = v1Edges + v2Edges
            val combinedVertex = Vertex(id = combinedVertexId, edges = combinedVertexEdges)

            vertexes.remove(vertex1)
            vertexes.remove(vertex2)

            //edges.removeAll { it.first == vertex1Id || it.first == vertex2Id || it.second == vertex1Id || it.second == vertex2Id }

            // replace adjacency on other vertex
            for (i in 0 until vertexes.size) {
                val v = vertexes[i]
                var vEdges = v.edges.toMutableList()
                if (vEdges.contains(vertex1Id)) {
                    //vEdges = vEdges - vertex1Id + combinedVertexId
                    vEdges.replaceAll { if (it == vertex1Id) combinedVertexId else it }
                }

                if (vEdges.contains(vertex2Id)) {
                    //vEdges = vEdges - vertex2Id + combinedVertexId
                    vEdges.replaceAll { if (it == vertex2Id) combinedVertexId else it }
                }

                if (vEdges != v.edges) {
                    vertexes.removeAt(i)
                    vertexes.add(i, Vertex(id = v.id, edges = vEdges))
                }
            }

            vertexes.add(combinedVertex)

            //println(combinedVertex.generateEdges())

            // para testar, vou só gerar as edges novamente
            edges.clear()
            edges.addAll(vertexes.generateEdges())

            /*val edg = edges.filter {
            it.first == vertex1Id || it.first == vertex2Id
                    ||  it.second == vertex1Id || it.second == vertex2Id}
        println(edg)*/

        } catch (e: Exception) {
            println(e)
        }

    }

    println(vertexes)
    println("edges1: ${vertexes[0].edges.size} \n edges2: ${vertexes[1].edges.size}")

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

private fun Vertex.generateEdges(): MutableList<Pair<Int, Int>> {
    val edges1 = mutableListOf<Pair<Int, Int>>()
    for (edge in edges) {
        edges1.add(id to edge)
    }
    return edges1
}