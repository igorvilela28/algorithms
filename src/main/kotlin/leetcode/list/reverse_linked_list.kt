package leetcode.list



//https://leetcode.com/problems/reverse-linked-list/submissions/1766580805/

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

internal class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

private class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        var current = head
        var prev: ListNode? = null


        while (current != null) {
            var next = current?.next
            current?.next = prev
            prev = current
            current = next
        }

        return prev
    }
}

// 1 -> 2 -> 3 -> 4 -> 5
// 1.next = 2
// 2.next = 3
// 3.next = 4
// 4.next = 5
// 5.next = null

// 1.next = null
// 2.next = 1
// 3.next = 2