package leetcode.list


// https://leetcode.com/problems/middle-of-the-linked-list/

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

//private class ListNode(var `val`: Int) {
//    var next: ListNode? = null
//}

private class Solution {
    fun middleNode(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }
        var size = 0;
        var current = head

        while (current != null) {
            current = current.next
            size++;
        }

        val midPos = (size/2)+1;

        current = head;
        for (i in 1 until midPos) {
            current = current?.next
        }
        return current;

    }
}