package stanford.course02.scc


/**
 * Solution using a recursive approach. It was interesting that for the input graph, with 5kk lines, the recursive
 * approach was leading to a StackOverFlow, making necessary change it for an iterative approach.
 */

internal class TopoSortRecursive(val graph: Map<Int, List<Int>>) {

    var curLevel = graph.keys.size
    val visited = mutableSetOf<Int>()
    val fValues = IntArray(graph.size)

    // returns the fValues for the topological sort
    fun topoSort(): IntArray {

        for (v in graph.keys) {
            if (!visited.contains(v)) {
                dfsTopo(v)
            }
        }
        return fValues
    }

    private fun dfsTopo(v: Int) {
        visited.add(v)
        val neighbors = graph[v] ?: emptyList()
        for (neighbor in neighbors) {
            if (!visited.contains(neighbor)) {
                dfsTopo(neighbor)
            }

        }
        fValues[curLevel-1] = v
        curLevel--
    }
}

internal class StronglyConnectedComponentsRecursive(val graph: KosarajuInputGraph) {
    val visited = mutableSetOf<Int>()
    val sscValues = IntArray(graph.graphOutAdjList.size)
    var numberOfScc = 0

    // returns the sizes of each SCC
    fun kosaraju(): List<Int> {

        val topoSort = TopoSortRecursive(graph.graphInAdjList)
        val fValues = topoSort.topoSort()
        for (v in fValues) {
            if (!visited.contains(v)) {
                numberOfScc++
                dfsScc(v)
            }
        }
        val sizesOfScc = sscValues
            .groupBy { it }
            .mapValues { it.value.size }
            .values
            .sortedDescending()

        return sizesOfScc
    }

    private fun dfsScc(v: Int) {
        sscValues[v-1] = numberOfScc
        visited.add(v)
        val neighbors = graph.graphOutAdjList[v] ?: emptyList()
        for (neighbor in neighbors) {
            if (!visited.contains(neighbor)) {
                dfsScc(neighbor)
            }
        }
    }
}