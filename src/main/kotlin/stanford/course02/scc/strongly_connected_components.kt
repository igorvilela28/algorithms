package stanford.course02.scc

internal class KosarajuInputGraph(
    val graphOutAdjList: Map<Int, List<Int>>,
    val graphInAdjList: Map<Int, List<Int>>,
)

internal class TopoSort(val graph: Map<Int, List<Int>>) {

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

    // 1. adiciona vertice como visitado
    // 2. processa vizinhos -> DFS -> assim que encontra um vizinho não visitado, adiciona no topo da stack
    // 3. Após todos vizinhos forem visitados (visitou toda a profundidade) se adiciona o curLevel
    private fun dfsTopo(v: Int) {

        val stack = ArrayDeque<Int>()
        stack.add(v)

        visited.add(v)

        while (!stack.isEmpty()) {
            val vertex = stack.first()
            val neighbors = graph[vertex] ?: emptyList()

            if (neighbors.isEmpty() || isAllNeighborsVisited(neighbors)) {
                stack.removeFirst()
                fValues[curLevel - 1] = vertex
                curLevel--
            }

            for (neighbor in neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.addFirst(neighbor)
                    visited.add(neighbor)
                }
            }

        }
    }

    private fun isAllNeighborsVisited(neighbors: List<Int>): Boolean {
        var result = true
        for (v in neighbors) {
            if (!visited.contains(v)) {
                result = false
                break
            }
        }
        return result
    }
}

internal class StronglyConnectedComponents(val graph: KosarajuInputGraph) {
    val visited = mutableSetOf<Int>()
    val sscValues = IntArray(graph.graphOutAdjList.size)
    var numberOfScc = 0

    /**
     * returns the sizes of each SCC, by descending order
     */
    fun kosaraju(): List<Int> {

        val topoSort = TopoSort(graph.graphInAdjList)
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
        val stack = ArrayDeque<Int>()
        stack.add(v)
        while (!stack.isEmpty()) {
            val v = stack.removeFirst()
            visited.add(v)
            sscValues[v - 1] = numberOfScc
            val neighbors = graph.graphOutAdjList[v] ?: emptyList()
            for (neighbor in neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.addFirst(neighbor)
                }
            }
        }
    }
}