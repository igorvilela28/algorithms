package leetcode.list

// https://leetcode.com/problems/kth-distinct-string-in-an-array/

private class Solution {
    fun kthDistinct(arr: Array<String>, k: Int): String {
        val distincts = mutableSetOf<String>()
        val repeatable = mutableSetOf<String>()
        for (str in arr) {
            if (!distincts.contains(str) && !repeatable.contains(str)) {
                distincts.add(str)
            } else {
                distincts.remove(str)
                repeatable.add(str)
            }
        }

        println(distincts)

        if (distincts.size < k) {
            return ""
        }

        return distincts.toList().get(k - 1)
    }
}