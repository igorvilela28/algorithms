package stanford.course01.quick_sort

import java.io.File
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

internal class QuickSortTest {

    @Test
    fun `should choose mid pivot`() {
        val a = intArrayOf(8, 2, 4, 5, 7, 1)
        val i = ChooseMidAsPivotStrategy.choosePivotIndex(
            a, 0, 5
        )
        assertEquals(2, i)
    }

    @Test
    fun `should choose mid pivot - 02`() {
        val a = intArrayOf(8, 2)
        val i = ChooseMidAsPivotStrategy.choosePivotIndex(
            a, 0, 1
        )
        assertEquals(0, i)
    }

    @Test
    fun `should choose mid pivot - 03`() {
        val a = intArrayOf(8, 2, 5, 3, 1)
        val i = ChooseMidAsPivotStrategy.choosePivotIndex(
            a, 0, 4
        )
        assertEquals(2, i)
    }

    @Test
    fun `should sort simple array`() {
        val quickSort = QuickSort(ChooseFirstAsPivotStrategy)
        val a = intArrayOf(3, 8, 2, 5, 1, 4, 7, 6)
        quickSort.sort(a, 0, a.size - 1)
        val expected = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(expected.toList(), a.toList())
    }

    @Test
    fun `should sort array using mid as pivot strategy`() {
        val quickSort = QuickSort(ChooseMidAsPivotStrategy)
        val a = intArrayOf(2, 20, 1, 15, 3, 11, 13, 6, 16, 10, 19, 5, 4, 9, 8, 14, 18, 17, 7, 12)
        quickSort.sort(a, 0, 19)
        assertEquals(55, quickSort.totalComparisons)
        val expected = List(20) { index -> index + 1 }
        assertEquals(expected, a.toList())
    }

    @Test
    fun `solve assignment #1 - should sort array using first as pivot strategy`() {
        solveAssignment(ChooseFirstAsPivotStrategy)
    }

    @Test
    fun `solve assignment #2 - should sort array using last as pivot strategy`() {
        solveAssignment(ChooseLastAsPivotStrategy)
    }

    @Test
    fun `solve assignment #3 - should sort array using mid as pivot strategy`() {
        solveAssignment(ChooseMidAsPivotStrategy)
    }

    private fun solveAssignment(strategy: ChoosePivotStrategy) {
        val path = Paths.get("").toAbsolutePath()
            .toString() + "/src/main/kotlin/stanford/course01/quick_sort/numbers.txt"
        val file = File(path)
        val numbers = file.readLines().map { it.toInt() }.toIntArray()
        val quickSort = QuickSort(strategy)
        quickSort.sort(numbers, 0, 10_000 - 1)
        val expected = List(10_000) { index -> index + 1 }
        assertEquals(expected, numbers.toList())
        println("Comparisons: ${quickSort.totalComparisons}")
    }
}