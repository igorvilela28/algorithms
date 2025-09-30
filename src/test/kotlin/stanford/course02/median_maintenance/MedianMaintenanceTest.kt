package stanford.course02.median_maintenance

import helper.getLines
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MedianMaintenanceTest {

    @Test
    fun solveAssignment() {

        val lines = getLines("/src/main/kotlin/stanford/course02/median_maintenance/input.txt")

        val numbers = lines.map { it.toInt() }

        val medianSum = MedianMaintenance().getMediansSum(numbers)

        val expected = 1213
        assertEquals(expected, medianSum % 10_000)
    }
}