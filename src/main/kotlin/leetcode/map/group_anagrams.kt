package leetcode.map

//


private class SolutionGroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        val mapResult = mutableMapOf<String, MutableList<String>>()

        for (str in strs) {
            val key = str.toCharArray().sorted().toString()
            val currentList = mapResult[key] ?: mutableListOf()
            currentList.add(str)
            mapResult[key] = currentList
        }

        //println(mapResult)
        return mapResult.values.toList()
        //return emptyList()
    }
}