package stanford.course02.scc

import java.io.File
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTime

class StronglyConnectedComponentsTest {

    @Test
    fun testSimpleInput() {

        val sizesSCC = runOnFile("/src/main/kotlin/stanford/course02/scc/short_input.txt")
        val expected = listOf(4, 3, 3, 1)

        assertEquals(expected, sizesSCC)

    }

    @Test
    fun solveAssignment() {

        val timeSpent = measureTime {
            val sizesSCC = runOnFile("/src/main/kotlin/stanford/course02/scc/input.txt")
            val expected = listOf(434821, 968, 459, 313, 211)
            assertEquals(expected, sizesSCC.take(5))
        }

        println(timeSpent.inWholeMilliseconds)

    }

    private fun runOnFile(filePath: String): List<Int> {

        val path = Paths.get("").toAbsolutePath()
            .toString() + filePath
        val file = File(path)

        val edges = file.readLines()

        val graphOutAdjList = mutableMapOf<Int, MutableList<Int>>()
        val graphInAdjList = mutableMapOf<Int, MutableList<Int>>()

        for (edge in edges) {

            val edgeValues = edge.split(" ")

            val v = edgeValues[0].toInt()
            val w = edgeValues[1].toInt()

            // making sure that each vertex is in the adjLists
            if (!graphOutAdjList.containsKey(v)) {
                graphOutAdjList[v] = mutableListOf()
            }
            if (!graphOutAdjList.containsKey(w)) {
                graphOutAdjList[w] = mutableListOf()
            }
            if (!graphInAdjList.containsKey(v)) {
                graphInAdjList[v] = mutableListOf()
            }

            if (!graphInAdjList.containsKey(w)) {
                graphInAdjList[w] = mutableListOf()
            }

            val outAdjList = graphOutAdjList[v]!!
            outAdjList.add(w)
            graphOutAdjList[v] = outAdjList

            val inAdjList = graphInAdjList[w]!!
            inAdjList.add(v)
            graphInAdjList[w] = inAdjList
        }

        val kosarajuInputGraph = KosarajuInputGraph(graphOutAdjList, graphInAdjList)
        val stronglyConnectedComponents = StronglyConnectedComponents(graph = kosarajuInputGraph)

        val sizesSCC = stronglyConnectedComponents.kosaraju()
        return sizesSCC
    }
}