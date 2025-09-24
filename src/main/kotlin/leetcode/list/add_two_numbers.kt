package leetcode.list

import kotlin.math.pow

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
private class Solution2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        //10 ^ 0 * 2
        //10 ^ 1 * 4
        //10 ^ 2 * 3

        var sum1 = 0f
        var i = 0
        var current = l1
        while (current != null) {
            sum1 += 10f.pow(i) * current.`val`
            current = current?.next
            i++
        }

        var sum2 = 0f
        i = 0
        current = l2
        while (current != null) {
            sum2 += 10f.pow(i) * current.`val`
            current = current.next
            i++
        }


        println(sum1)
        println(sum2)
        val sum3 = (sum1 + sum2).toInt().toString().reversed()
        println(sum3)
        var value = sum3[0].digitToInt()
        ///println(value)
        var head: ListNode = ListNode(value)
        current = head
        for (i in 1 until sum3.length) {
            value = sum3[i].digitToInt()
            val node = ListNode(value)
            current?.next = node
            current = node
        }

        return head
    }
}