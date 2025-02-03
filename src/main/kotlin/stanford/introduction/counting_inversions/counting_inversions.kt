package stanford.introduction.counting_inversions

/**
 * Sorts A and return it and the number of inversions
 */
internal fun sortAndCountInversions(a: List<Int>): Pair<List<Int>, UInt> {

    // base case
    if (a.size <= 1) {
        return a to 0u
    }

    if (a.size == 2) {
        val isInverted = a[0] > a[1]
        return if (isInverted) a.reversed() to 1u else a to 0u
    }

    val split = split(a)
    val leftSide = split.first
    val rightSide = split.second

    val leftInversions = sortAndCountInversions(leftSide)
    val rightInversions = sortAndCountInversions(rightSide)
    val splitInversions = mergeAndCountSplit(leftInversions.first, rightInversions.first)
    val total = leftInversions.second + rightInversions.second + splitInversions.second
    return splitInversions.first to total
}

/**
 * merges 2 sorted arrays into one and counts the number of split inversions
 */
internal fun mergeAndCountSplit(sortedLeftSide: List<Int>, sortedRightSide: List<Int>): Pair<List<Int>, UInt> {
    var i = 0
    var j = 0
    val n = sortedLeftSide.size + sortedRightSide.size
    var count = 0u
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
            count += (sortedLeftSide.size - i).toUInt()
        }
    }
    return merged to count
}

internal fun split(a: List<Int>): Pair<List<Int>, List<Int>> {
    val size = if (a.size % 2 == 0) a.size / 2 else (a.size + 1) / 2
    val split = a.chunked(size)
    return split.first() to split.last()
}