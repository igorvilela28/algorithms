package leetcode.bfs_dfs

import java.util.Deque
import java.util.LinkedList


/**
 * https://leetcode.com/problems/clone-graph/description/
 */

private data class Node(
    val `val`: Int,

) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}

private fun cloneGraph(node: Node?): Node? {

    if (node == null) return null

    val adjList = mutableMapOf<Int, List<Int?>>()
    val explored: MutableSet<Int> = mutableSetOf()
    node?.let {
        explored.add(it.`val`)
    }
    val queue: Deque<Node> = LinkedList<Node>()
    queue.push(node)
    while (!queue.isEmpty()) {
        val v = queue.pop()
        adjList[v.`val`] = v.neighbors.map { it?.`val` }
        val adjListSize = v.neighbors.size
        for (i in 0 until adjListSize) {
            val neighbor = v.neighbors[i]
            if (neighbor != null && !explored.contains(neighbor.`val`)) {
                explored.add(neighbor.`val`)
                queue.add(neighbor)
            }
        }
    }
    //println(adjList)

    // Creates Nodes using the adjacency list

    val nodes = mutableMapOf<Int, Node?>()

    for (key in adjList.keys) {
        if (!nodes.containsKey(key)) {
            nodes[key] = Node(key)
        }

        for (neighbor in adjList[key]!!) {
            if (neighbor == null) continue
            if (!nodes.containsKey(neighbor)) {
                nodes[neighbor] = Node(neighbor)
            }
                nodes[key]?.neighbors?.add(nodes[neighbor])
            }
        }

    return nodes[1]
}

private fun main() {





}