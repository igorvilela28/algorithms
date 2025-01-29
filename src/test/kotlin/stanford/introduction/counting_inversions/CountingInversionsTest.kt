package stanford.introduction.counting_inversions

import kotlin.test.Test
import kotlin.test.assertEquals

class CountingInversionsTest {

    @Test
    fun `should count test case 01`() {
        val a = listOf(1, 3, 5, 2, 4, 6)
        val result = counting_inversions(a)
        assertEquals(3, result)
    }
}