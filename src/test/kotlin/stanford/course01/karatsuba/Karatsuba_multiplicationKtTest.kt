package stanford.course01.karatsuba

import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Karatsuba_multiplicationKtTest {


    @Test
    fun `should check if is power of 2`() {
        assertFalse { isPowerOfTwo(0) }
        assertFalse { isPowerOfTwo(3) }
        assertFalse { isPowerOfTwo(12) }
        assertFalse { isPowerOfTwo(15) }
        assertFalse { isPowerOfTwo(23) }


        assertTrue { isPowerOfTwo(2) }
        assertTrue { isPowerOfTwo(4) }
        assertTrue { isPowerOfTwo(8) }
        assertTrue { isPowerOfTwo(16) }
        assertTrue { isPowerOfTwo(32) }
        assertTrue { isPowerOfTwo(64) }
        assertTrue { isPowerOfTwo(128) }
        assertTrue { isPowerOfTwo(256) }
    }

    @Test
    fun `should append leading 0 when number length is not power of 2`() {
        assertEquals("0123", "123".makeStringPowerOfTwo())
        assertEquals("00123456", "123456".makeStringPowerOfTwo())
        assertEquals("00012345", "12345".makeStringPowerOfTwo())
        assertEquals("12345678", "12345678".makeStringPowerOfTwo())
    }

    @Test
    fun `should transform numbers to same length`() {
        assertEquals("12345678" to "00001234", makeNumbersSameLength("12345678", "00001234"))
        assertEquals("00012345" to "00000001", makeNumbersSameLength("12345", "1"))
        assertEquals("0123" to "0012", makeNumbersSameLength("123", "12"))
        assertEquals("0001" to "0123", makeNumbersSameLength("1", "123"))
    }
    @Test
    fun `should multiply 1234 times 5678`() {
        val bigNumber1 = BigInteger("1234")
        val bigNumber2 = BigInteger("5678")
        val expected = BigInteger("7006652")
        val result = multiplyByKaratsuba(bigNumber1, bigNumber2)
        assertEquals(expected, result)
    }

    @Test
    fun `should multiply odd numbers - test case 01`() {
        val bigNumber1 = BigInteger("46")
        val bigNumber2 = BigInteger("102")
        val expected = BigInteger("4692")
        val result = multiplyByKaratsuba(bigNumber1, bigNumber2)
        assertEquals(expected, result)
    }

    @Test
    fun `should multiply odd numbers - test case 02`() {
        val bigNumber1 = BigInteger("1234567")
        val bigNumber2 = BigInteger("12345678")
        val expected = BigInteger("15241566651426")
        val result = multiplyByKaratsuba(bigNumber1, bigNumber2)
        assertEquals(expected, result)
    }

    @Test
    fun `should multiply 16 digits numbers`() {
        val bigNumber1 = BigInteger("1234567890123456")
        val bigNumber2 = BigInteger("7890123456780123")
        val expected = BigInteger("9740893068850625727530716865088")
        val result = multiplyByKaratsuba(bigNumber1, bigNumber2)
        assertEquals(expected, result)
    }

    @Test
    fun `should multiply big numbers - solve 1st assignment`() {
        val bigNumber1 = BigInteger("3141592653589793238462643383279502884197169399375105820974944592")
        val bigNumber2 = BigInteger("2718281828459045235360287471352662497757247093699959574966967627")
        val expected = BigInteger("8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184")
        val result = multiplyByKaratsuba(bigNumber1, bigNumber2)
        assertEquals(expected, result)
    }

}