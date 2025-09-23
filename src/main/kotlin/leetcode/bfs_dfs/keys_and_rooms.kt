package leetcode.bfs_dfs

/**
 * https://leetcode.com/problems/keys-and-rooms/description/
 */

private class Solution {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {

        val explored = mutableSetOf<Int>()
        explored.add(0)

        // FIFO = first in first out -> fila
        // LIFO = last in first out -> stack
        val queue = ArrayDeque<Int>()
        queue.addLast(0)

        while (queue.isNotEmpty()) {
            val currentRoom = queue.removeFirst()
            val keys = rooms[currentRoom]

            for (key in keys) {
                if (!explored.contains(key)) {
                    queue.addLast(key)
                    explored.add(key)
                }
            }

        }

        return explored.size == rooms.size
    }

    // TODO: Aplicar algoritmo de componentes conectados apenas para treino
}

fun main() {
    val result1 = Solution().canVisitAllRooms(
        listOf(
            listOf(1),
            listOf(2),
            listOf(3),
            listOf(),
        )
    )

    println(result1)

    val result2 = Solution().canVisitAllRooms(
        listOf(
            listOf(1, 3),
            listOf(3, 0, 1),
            listOf(2),
            listOf(0),
        )
    )

    println(result2)

    val result3 = Solution().canVisitAllRooms(
        listOf(
            listOf(1),
            listOf(2),
            listOf(2),
            listOf(),
        )
    )

    println(result3)
}