package stanford.course01.counting_inversions

import java.io.File
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

class CountingInversionsTest {

    @Test
    fun `should split even list of numbers in half`() {
        val a = listOf(1, 3, 5, 2, 4, 6)
        val expected = listOf(1, 3, 5) to listOf(2, 4, 6)
        val result = split(a)
        assertEquals(expected, result)
    }

    @Test
    fun `should split odd list of numbers in half`() {
        val a = listOf(1, 3, 5, 2, 4)
        val expected = listOf(1, 3, 5) to listOf(2, 4)
        val result = split(a)
        assertEquals(expected, result)
    }

    @Test
    fun `should merge and count split for even sorted arrays`() {
        val a = listOf(1, 4)
        val b = listOf(2, 3)
        val expected = listOf(1, 2, 3, 4) to 2u
        val result = mergeAndCountSplit(a, b)
        assertEquals(expected, result)
    }

    @Test
    fun `should merge and count split for odd sorted arrays`() {
        val a = listOf(1, 4, 5)
        val b = listOf(2, 3)
        val expected = listOf(1, 2, 3, 4, 5) to 4u
        val result = mergeAndCountSplit(a, b)
        assertEquals(expected, result)
    }

    @Test
    fun `should merge and count split for odd sorted arrays - 02`() {
        val a = listOf(1, 5)
        val b = listOf(2, 3, 4)
        val expected = listOf(1, 2, 3, 4, 5) to 3u
        val result = mergeAndCountSplit(a, b)
        assertEquals(expected, result)
    }

    @Test
    fun `should count test case 01`() {
        val a = listOf(1, 3, 5, 2, 4, 6)
        val result = sortAndCountInversions(a)
        val expected = listOf(1,2,3,4,5,6) to 3u
        assertEquals(expected, result)
    }

    @Test
    fun `should count test case 02`() {
        val a = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        val result = sortAndCountInversions(a)
        assertEquals(0u, result.second)
    }

    @Test
    fun `should count test case 03`() {
        val a = listOf(6, 5, 4, 3, 2, 1)
        val expected = listOf(1,2,3,4,5,6) to 15u
        val result = sortAndCountInversions(a)
        assertEquals(expected, result)
    }

    @Test
    fun `should count inversions from text file`() {
        val path = Paths.get("").toAbsolutePath()
            .toString() + "/src/main/kotlin/stanford/course01/counting_inversions/numbers.txt"
        val file = File(path)
        val numbers = file.readLines().map { it.toInt() }
        val result = sortAndCountInversions(numbers)
        assertEquals(2407905288u, result.second)
    }
}