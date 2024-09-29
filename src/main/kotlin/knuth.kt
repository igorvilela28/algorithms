import kotlin.random.Random

/**
 * Write a program that reads a sequence of words from standard input and prints one of those words uniformly at random.
 * Do not store the words in an array or list. Instead, use Knuth’s method: when reading the ith word, select it with
 * probability 1/i to be the champion, replacing the previous champion. After reading all of the words, print the
 * surviving champion.
 */
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("no args passed")
        return
    }

    if (args.size == 1) {
        println(args[0])
        return
    }

    var reservoir = args[0]
    for (i in 1 until args.size) {
        reservoir = args[randomNumber(i+1)]
    }

    println(reservoir)
}

private fun randomNumber(i: Int) = Random.nextInt(0, i)