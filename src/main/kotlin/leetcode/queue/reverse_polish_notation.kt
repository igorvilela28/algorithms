package leetcode.queue

import java.util.Stack

//https://leetcode.com/problems/evaluate-reverse-polish-notation/description/

private class SolutionReversePolishNotation {
    fun evalRPN(tokens: Array<String>): Int {

        val operators = setOf("+", "-", "*", "/")
        val stack = Stack<Int>()
        var result = 0

        for (token in tokens) {
            //println(stack)
            if (token !in operators) {
                stack.push(token.toInt())
            } else {
                //println("token: $token")
                val a = stack.pop()
                val b = stack.pop()
                val result = evaluate(b, a, token)
                //println("result: $a $token $b = $result")
                stack.push(result)
            }
        }

        return stack.pop()
    }

    private fun evaluate(a: Int, b: Int, operator: String): Int = when (operator) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> a / b
        else -> throw Exception("operator not valid")
    }
}

private fun main() {

}