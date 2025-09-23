package leetcode.map

// https://leetcode.com/problems/repeated-dna-sequences/

private class SolutionDnaSequences {
    fun findRepeatedDnaSequences(s: String): List<String> {

        val mapCount = mutableMapOf<String, Int>()

        for (i in 0 .. s.length - 10) {
            //println(i)
            val substring = s.substring(startIndex = i, endIndex = i+10)
            //println(substring)
            val current = mapCount[substring] ?: 0
            mapCount.put(substring, current+1)
        }

        "".toSet()

        "".reversed()

        "".toList().sorted()

        val mapResult = mutableMapOf<String, MutableList<String>>()

        val x = mapResult.values.toList()

        //println(mapCount)

        return mapCount.filter { it.value > 1 }.keys.toList()

    }
}