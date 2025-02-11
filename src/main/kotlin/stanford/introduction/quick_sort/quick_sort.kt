package stanford.introduction.quick_sort


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

        val i = choosePivotStrategy.choosePivotPosition(a, l, r)
        swapPivotElement(a, l, i)
        val j = partition(a, l, r)
        sort(a, l, j - 1)
        sort(a, j + 1, r)
    }

    /**
     *  the index j keeps track of which elements have
     * been processed, while i keeps track of the boundary between processed
     * elements that are less than and greater than the pivot
     */
    internal fun partition(a: IntArray, l: Int, r: Int): Int {
        val p = a[l] // sets pivot as 1st element
        var i = l + 1 // sets boundary index as 1st index after pivot index
        for (j in l + 1.. r) {
            val currentValue = a[j]
            if (currentValue < p) {
                val leftBoundary = a[i]
                a[i] = currentValue
                a[j] = leftBoundary
                i++
            }
        }
        swapPivotElement(a, l, i - 1)
        return i - 1
    }

    private fun swapPivotElement(a: IntArray, l: Int, i: Int) {
        val pivotValue = a[i]
        val currentLValue = a[l]
        a[l] = pivotValue
        a[i] = currentLValue
    }
}

// Used to select the choose pivot strategy
internal interface ChoosePivotStrategy {
    fun choosePivotPosition(a: IntArray, l: Int, r: Int): Int
}

internal object ChooseFirstAsPivotStrategy : ChoosePivotStrategy {
    override fun choosePivotPosition(a: IntArray, l: Int, r: Int): Int {
        return l
    }
}

internal object ChooseLastAsPivotStrategy : ChoosePivotStrategy {
    override fun choosePivotPosition(a: IntArray, l: Int, r: Int): Int {
        return r
    }
}


internal object ChooseMidAsPivotStrategy : ChoosePivotStrategy {
    override fun choosePivotPosition(a: IntArray, l: Int, r: Int): Int {

        val n = r - l + 1

        if (n == 0 || n == 1 || n == 2) {
            return l
        }

        var midPosition = if (n % 2 == 0) {
            (n / 2) - 1
        } else (n / 2)

        midPosition += l // shift mid to be after L

        val lValue = a[l]
        val rValue = a[r]
        val midValue = a[midPosition]

        val list = listOf(lValue to l, rValue to r, midValue to midPosition).sortedBy {
            it.first
        }

        val median = list[1]
        return median.second
    }
}