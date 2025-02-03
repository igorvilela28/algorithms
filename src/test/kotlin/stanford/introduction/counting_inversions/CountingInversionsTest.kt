package stanford.introduction.counting_inversions

import kotlin.test.Test
import kotlin.test.assertEquals

class CountingInversionsTest {

    @Test
    fun `should split even list of numbers in half`() {
        val a = listOf(1, 3, 5, 2, 4, 6)
        val expected = listOf(1,3,5) to listOf(2,4,6)
        val result = split(a)
        assertEquals(expected, result)
    }

    @Test
    fun `should split odd list of numbers in half`() {
        val a = listOf(1, 3, 5, 2, 4)
        val expected = listOf(1,3,5) to listOf(2,4)
        val result = split(a)
        assertEquals(expected, result)
    }

    @Test
    fun `should merge and count splitted for even sorted arrays`() {
        val a = listOf(1, 4)
        val b = listOf(2,3)
        val expected = listOf(1,2,3,4) to 2
        val result = mergeAndCountSpplited(a, b)
        assertEquals(expected, result)
    }

    @Test
    fun `should merge and count splitted for odd sorted arrays`() {
        val a = listOf(1, 4, 5)
        val b = listOf(2,3)
        val expected = listOf(1,2,3,4,5) to 4
        val result = mergeAndCountSpplited(a, b)
        assertEquals(expected, result)
    }

    @Test
    fun `should merge and count splitted for odd sorted arrays - 02`() {
        val a = listOf(1, 5)
        val b = listOf(2,3,4)
        val expected = listOf(1,2,3,4,5) to 3
        val result = mergeAndCountSpplited(a, b)
        assertEquals(expected, result)
    }

    @Test
    fun `should count test case 01`() {
        val a = listOf(1, 3, 5, 2, 4, 6)
        val result = countInversions(a)
        assertEquals(3, result.second)
    }
}