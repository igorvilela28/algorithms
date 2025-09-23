package leetcode.tree

/**
 * https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
 */


private data class Node(
    val key: Int
) {
    var parent: Node? = null
    var leftChild: Node? = null
    var rightChild: Node? = null
}

private class SearchTree {

    var root: Node? = null

    fun insert(key: Int) {
        if (root == null) {
            root = Node(key)
            return
        }

        var current = root

        while (current != null) {

            if (key < current.key) {
                if (current.leftChild == null) {
                    current.leftChild = Node(key).apply { parent = current }
                    break
                }
                current = current.leftChild!!
            } else {
                if (current.rightChild == null) {
                    current.rightChild = Node(key).apply { parent = current }
                    break
                }
                current = current.rightChild!!
            }
        }
    }

    fun printTraversal() {

        val root = root ?: return
        val queue = ArrayDeque<Node>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            print("${node.key} ")
            if (node.leftChild != null) {
                queue.addLast(node.leftChild!!)
            }
            if (node.rightChild != null) {
                queue.addLast(node.rightChild!!)
            }
        }
    }

    // GPT sugestion -> use for other ideas

//    fun printTraversal() {
//        root ?: return
//        val queue = ArrayDeque<Node>().apply { add(root!!) }
//        val sb = StringBuilder()
//        while (queue.isNotEmpty()) {
//            val node = queue.removeFirst()
//            sb.append(node.key).append(' ')
//            node.leftChild?.let(queue::addLast)
//            node.rightChild?.let(queue::addLast)
//        }
//        print(sb.toString())
//    }
}


private fun main() {

//    val n = readLine()!!.toInt()
//
//    if (n == 0) {
//        return
//    }
//    val numbers = readLine()!!.split(" ").map { it.toInt() }
//
//    if (n == 1) {
//        println(numbers[0])
//    }

    val n = 6
    val numbers = listOf(1, 2, 5, 3, 6, 4)

   val searchTree = SearchTree()
    for (i in numbers) {
        searchTree.insert(i)
    }
    searchTree.printTraversal()

    //println("1")

}