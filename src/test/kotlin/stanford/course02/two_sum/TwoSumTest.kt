package stanford.course02.two_sum

import helper.getLines
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class TwoSumTest {


    @Test
    fun solveAssignment() {

        val lines = getLines("/src/main/kotlin/stanford/course02/two_sum/input.txt")

        val array = lines.map { it.toLong() }.toLongArray()

        val count = TwoSum().countTwoSum(array)

    }

}