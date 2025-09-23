package leetcode.tree.exercise02

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 */

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

private data class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

private class Solution1 {

    fun inorderTraversal(root: TreeNode?): List<Int> {

        if (root == null) return emptyList()
        val result = mutableListOf<Int>()
        //inOrderRecursive(root, result)
        inOrderStack(root, mutableListOf())

        return result
    }

    fun inOrderStack(root: TreeNode?, result: MutableList<Int>) {

        if (root == null) return

        val stack = ArrayDeque<TreeNode>()
        stack.addFirst(root)
        var current = root

        while (stack.isNotEmpty()) {
            if (current?.left != null) {
                current = current.left
                stack.addFirst(current!!)
                continue
            }
            current = stack.removeFirst()
            result.add(current.`val`)
            if (current.right != null) {
                current = current.right
                stack.addFirst(current!!)
                continue
            }
        }
    }

    fun inOrderRecursive(root: TreeNode?, result: MutableList<Int>) {
        if (root == null) return

        if (root.left != null) {
            inOrderRecursive(root.left, result)
        }

        result.add(root.`val`)

        if (root.right != null) {
            inOrderRecursive(root.right, result)
        }
    }
}

private fun main() {

    // Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
    //Output: [4,2,6,5,7,1,3,9,8]

    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }
        right = TreeNode(3).apply {
            right = TreeNode(8).apply {
                left = TreeNode(9)
            }
        }
    }

    val solution = Solution1()
    println(solution.inorderTraversal(root))
}