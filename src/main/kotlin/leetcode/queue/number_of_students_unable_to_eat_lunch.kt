package leetcode.queue

import java.util.LinkedList

// https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/

private class Solution {
    fun countStudents(students: IntArray, sandwiches: IntArray): Int {

        "".get(1).toInt()

        val sum3 = ""
        val x = sum3.get(0).digitToInt()

        "".reversed()
        val studentsQueue = LinkedList<Int>()
        val sandwichesQueue = LinkedList<Int>()

        for (i in students.indices) {
            studentsQueue.add(students[i])
        }

        for (i in sandwiches.indices) {
            sandwichesQueue.add(sandwiches[i])
        }



        return 0
    }


}

fun main() {
    val solution = Solution()

    //Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
    //Output: 3

    val students = intArrayOf(1,1,1,0,0,1)
    val sandwiches = intArrayOf(1,0,0,0,1,1)
    solution.countStudents(students, sandwiches)

//    class Solution {
//        fun findRepeatedDnaSequences(s: String): List<String> {
//
//            val mapCount = mutableMapOf<String, Int>()
//
//            for (i in 0 until s.length - 10) {
//                val substring = s.substring(startIndex = i, endIndex = i+10)
//                println(substring)
//                if (mapCount.contains(substring)) {
//                    val current = mapCount[substring] ?: 0
//                    mapCount.put(substring, current+1)
//                } else {
//                    mapCount[substring] = 1
//                }
//            }
//
//            println(mapCount)
//
//            val x = mapCount.filter { it.value > 1 }.keys.toList()
//
//            return emptyList()
//
//        }
//    }


}