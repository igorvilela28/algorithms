package stanford.course01.random_contraction

import java.io.File
import java.nio.file.Paths
import kotlin.math.ln
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.test.Test

class RandomContractionTest {

    @Test
    fun `should find minimum cut using random contraction algorithm`() {

        val vertexes = readVertexes()

        var minCut = Integer.MAX_VALUE

        // if the algorithm runs in O( n * log(n) ) times, it's guarantee that it will find the minimum cut
        val runs = vertexes.size * ln(vertexes.size.toDouble()).roundToInt()

        for (i in 0 until runs) {

            val cuts = randomContraction(vertexes.toMutableList()).first().edges.size
            println("cuts: $cuts")

            if (cuts < minCut) {
                minCut = cuts
            }
        }

        println("minCut: $minCut")
    }


    private fun readVertexes(): List<Vertex> {
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
}