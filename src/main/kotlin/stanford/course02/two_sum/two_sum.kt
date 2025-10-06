package stanford.course02.two_sum

// TODO: implement my own hash data structure
internal class TwoSum {

    fun countTwoSum(array: LongArray): Int {

        val map = mutableMapOf<Long, Boolean>()
        for (i in array) {
            map[i] = true
        }

        var count = 0

        for (i in -10_000L..10_000L) {
            if (hasTwoSum(map, i)) {
                count++
            }
        }

        return count
    }

    private fun hasTwoSum(map: Map<Long, Boolean>, target: Long): Boolean {

        for (value in map.keys) {

            val diff = target - value

            if (map[diff] == true) {
                return true
            }
        }

        return false
    }


}