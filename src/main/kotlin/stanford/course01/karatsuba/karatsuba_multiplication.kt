package stanford.course01.karatsuba

import java.math.BigInteger

internal fun multiplyByKaratsuba(value1: BigInteger, value2: BigInteger): BigInteger {

    val s1 = value1.toString()
    val s2 = value2.toString()

    if (s1.length == 1 || s2.length == 1) {
        return value1.multiply(value2)
    }

    val uniformizedNumbers = makeNumbersSameLength(s1, s2)

    val a = uniformizedNumbers.first.splitInHalf().first.toBigInteger()
    val b = uniformizedNumbers.first.splitInHalf().second.toBigInteger()
    val c = uniformizedNumbers.second.splitInHalf().first.toBigInteger()
    val d = uniformizedNumbers.second.splitInHalf().second.toBigInteger()

    val n = uniformizedNumbers.first.length

    val p = a + b
    val q = c + d

    val ac = multiplyByKaratsuba(a, c)
    val bd = multiplyByKaratsuba(b, d)
    val pq = multiplyByKaratsuba(p, q)
    val adbc = pq - ac - bd

    val tenPowerN = BigInteger.TEN.pow(n)
    val tenPowerHalfN = BigInteger.TEN.pow(n/2)

    val result = tenPowerN.multiply(ac) + tenPowerHalfN.multiply(adbc) + bd
    println("result: $value1 * $value2 = $result")
    return result
}

internal fun String.splitInHalf(): Pair<String, String> {

    val x = substring(0, length / 2)
    val y = substring(length / 2, length)

    return x to y
}

internal fun makeNumbersSameLength(s1: String, s2: String): Pair<String, String> {

    var number1 = s1.makeStringPowerOfTwo()
    var number2 = s2.makeStringPowerOfTwo()

    if (number1.length == number2.length) {
        return number1 to number2
    }

    if (number1.length < number2.length) {
        while (number1.length < number2.length) {
            number1 = "0" + number1
        }
        return number1 to number2
    }

    while (number2.length < number1.length) {
        number2 = "0" + number2
    }
    return number1 to number2
}

internal fun String.makeStringPowerOfTwo(): String {

    var s1 = this

    while (!isPowerOfTwo(s1.length)) {
        s1 = "0" + s1
    }

    return s1
}

internal fun isPowerOfTwo(number: Int): Boolean {
    return number != 0 && (number and (number - 1) == 0)
}