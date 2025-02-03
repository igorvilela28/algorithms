package stanford.introduction.counting_inversions

/**
 * Sorts A and return it and the number of inversions
 */
internal fun countInversions(a: List<Int>): Pair<List<Int>, Int> {

    // base case
    if (a.size <= 1) {
        return a to 0
    }

    if (a.size == 2) {
        val isInverted = a[0] > a[1]
        return if (isInverted) a.reversed() to 1 else a to 0
    }

    val splitted = split(a)
    val leftSide = splitted.first
    val rightSide = splitted.second

    val leftInversions = countInversions(leftSide)
    val rightInversions = countInversions(rightSide)
    val splittedInversions = mergeAndCountSpplited(leftSide, rightSide)
    return splittedInversions.first to (leftInversions.second + rightInversions.second + splittedInversions.second)
}

/**
 * merges 2 sorted arrays into one and counts the number of spplited inversions
 */
internal fun mergeAndCountSpplited(sortedLeftSide: List<Int>, sortedRightSide: List<Int>): Pair<List<Int>, Int> {
    var i = 0
    var j = 0
    val n = sortedLeftSide.size + sortedRightSide.size
    var count = 0
    val merged = mutableListOf<Int>()
    for (k in 0 until n) {

        if (i == sortedLeftSide.size) {
            merged.add(sortedRightSide[j])
            j++
        } else if (j == sortedRightSide.size) {
            merged.add(sortedLeftSide[i])
            i++
        } else if (sortedLeftSide[i] < sortedRightSide[j]) {
            merged.add(sortedLeftSide[i])
            i++
        } else {
            merged.add(sortedRightSide[j])
            j++
            count += sortedLeftSide.size - i
        }
    }
    return merged to count
}

internal fun split(a: List<Int>): Pair<List<Int>, List<Int>> {
    val size = if (a.size % 2 == 0) a.size / 2 else (a.size + 1) / 2
    val splitted = a.chunked(size)
    return splitted.first() to splitted.last()
}