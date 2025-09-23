package leetcode.bfs_dfs

import java.util.Stack

/**
 * https://leetcode.com/problems/find-if-path-exists-in-graph/description/
 */

private class SolutionIterative {
    /**
     * Uses DFS to check if exists a valid path
     */
    fun dfsIterative(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {

        if (source == destination) return true

        val adjList = createAdjList(edges)
        val explored = mutableSetOf<Int>()

        val stack = Stack<Int>()
        stack.push(source)

        while (stack.isNotEmpty()) {

            val v = stack.pop()

            if (!explored.contains(v)) {
                explored.add(v)
                val neighbors = adjList[v] ?: continue
                for (neighbor in neighbors) {
                    if (neighbor == destination) {
                        return true
                    }

                    if (!explored.contains(neighbor)) {
                        stack.push(neighbor)
                    }
                }
            }
        }
        return false
    }
}

private fun createAdjList(edges: Array<IntArray>): Map<Int, MutableSet<Int>> {

    // 0, 1 - 1, 2 - 2,0
    // 0 -> 1,2
    // 1 -> 0, 2
    // 2 -> 1, 0

    val adjList = mutableMapOf<Int, MutableSet<Int>>()

    // Arestas são sempre em 02 vertices, portanto, é esperado que em edges tenha apenas 02 valores
    for ((u, v) in edges) {
        // Adiciona v em u
        adjList.getOrPut(u) { mutableSetOf() }.add(v)
        // Adiciona u em v (grafo não direcionado)
        adjList.getOrPut(v) { mutableSetOf() }.add(u)
    }
    return adjList
}

private class SolutionRecursive {

    val explored = mutableSetOf<Int>()

    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        return dfsRecursive(n, createAdjList(edges), source, destination)
    }

    private fun dfsRecursive(n: Int, adjList: Map<Int, MutableSet<Int>>, source: Int, destination: Int): Boolean {

        if (source == destination) return true

        explored.add(source)

        for (v in adjList[source] ?: return false) {
            if (!explored.contains(v)) {
                val result = dfsRecursive(n, adjList, v, destination)
                if (result) return true
            }
        }
        return false
    }

}

private fun main() {

    val result1 = SolutionIterative().dfsIterative(
        n = 3 ,
        edges = arrayOf(intArrayOf(0, 1),intArrayOf(1, 2), intArrayOf(2, 0) ),
        source = 0,
        destination = 2
    )

    println(result1)

    val result2 = SolutionIterative().dfsIterative(
        n = 6,
        edges = arrayOf(intArrayOf(0, 1),intArrayOf(0, 2), intArrayOf(3, 5), intArrayOf(5, 4), intArrayOf(4, 3) ),
        source = 0,
        destination = 5
    )

    println(result2)

    val result3 = SolutionRecursive().validPath(
        n = 6,
        edges = arrayOf(intArrayOf(0, 1),intArrayOf(0, 2), intArrayOf(3, 5), intArrayOf(5, 4), intArrayOf(4, 3) ),
        source = 0,
        destination = 5
    )

    println(result3)

    val result4 = SolutionRecursive().validPath(
        n = 3 ,
        edges = arrayOf(intArrayOf(0, 1),intArrayOf(1, 2), intArrayOf(2, 0) ),
        source = 0,
        destination = 2
    )

    println(result4)
}
