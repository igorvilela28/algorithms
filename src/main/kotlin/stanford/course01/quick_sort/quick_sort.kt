package stanford.course01.quick_sort


internal class QuickSort(private val choosePivotStrategy: ChoosePivotStrategy) {

    var totalComparisons = 0
        private set

    internal fun sort(a: IntArray, l: Int, r: Int) {

        // base case
        if (l >= r) {
            return
        }

        val comparisons = r - l
        totalComparisons += comparisons // if M = length of subarray, add M-1 comparisons

        val pivotIndex = choosePivotStrategy.choosePivotIndex(a, l, r)
        swapPivot(a, l, pivotIndex)
        val pivotFinalIndex = partition(a, l, r)
        sort(a, l, pivotFinalIndex - 1)
        sort(a, pivotFinalIndex + 1, r)
    }

    /**
     * Partitions the array segment around a pivot.
     *
     * This function selects a pivot element and rearranges the elements of the array between the
     * indices `l` and `r` such that:
     * - **All elements less than the pivot** are placed to its left;
     * - **All elements greater than the pivot** are placed to its right.
     *
     * After partitioning, the method returns the **final pivot index**, indicating its correct position
     * in the sorted array.
     *
     * @param a The array to be partitioned.
     * @param l The starting index of the segment to be partitioned.
     * @param r The ending index of the segment to be partitioned.
     * @return The final index of the pivot after partitioning.
     */
    internal fun partition(a: IntArray, l: Int, r: Int): Int {

        /**
         *  the index j keeps track of which elements have
         * been processed, while i keeps track of the boundary between processed
         * elements that are less than and greater than the pivot.
         */
        val p = a[l] // sets pivot as 1st element
        var i = l + 1 // sets boundary index as 1st index after pivot index
        for (j in l + 1..r) {
            val currentValue = a[j]
            if (currentValue < p) {
                val leftBoundary = a[i]
                a[i] = currentValue
                a[j] = leftBoundary
                i++
            }
        }
        swapPivot(a, l, i - 1)
        return i - 1
    }

    /**
     * Sets pivot value as 1st element of array (L index)
     */
    private fun swapPivot(a: IntArray, l: Int, pivotIndex: Int) {
        val pivotValue = a[pivotIndex]
        val currentLValue = a[l]
        a[l] = pivotValue
        a[pivotIndex] = currentLValue
    }
}

// Used to select the choose pivot strategy
internal interface ChoosePivotStrategy {
    fun choosePivotIndex(a: IntArray, l: Int, r: Int): Int
}

internal object ChooseFirstAsPivotStrategy : ChoosePivotStrategy {
    override fun choosePivotIndex(a: IntArray, l: Int, r: Int): Int {
        return l
    }
}

internal object ChooseLastAsPivotStrategy : ChoosePivotStrategy {
    override fun choosePivotIndex(a: IntArray, l: Int, r: Int): Int {
        return r
    }
}


internal object ChooseMidAsPivotStrategy : ChoosePivotStrategy {

    override fun choosePivotIndex(a: IntArray, l: Int, r: Int): Int {

        val n = r - l + 1

        if (n <= 2) return l

        val midPosition = l + (n - 1) / 2

        val values = listOf(a[l] to l, a[r] to r, a[midPosition] to midPosition).sortedBy { it.first }
        val median = values[1]
        return median.second
    }
}