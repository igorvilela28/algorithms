package stanford.course02.median_maintenance

import java.nio.file.Files.size
import java.util.PriorityQueue
import kotlin.comparisons.compareByDescending
import kotlin.math.absoluteValue


internal class MedianMaintenance() {


    fun getMediansSum(numbers: List<Int>): Int {

        var sum = 0

        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        for (n in numbers) {

            val maxHeapValue = maxHeap.peek()
            val minHeapValue = minHeap.peek()

            // both heaps are empty
            if (maxHeapValue == null && minHeapValue == null) {
                maxHeap.add(n)
                sum += n
                continue
            }

            if (n > maxHeapValue) {
                minHeap.add(n)
            } else {
                maxHeap.add(n)
            }

            // balancing
            val sizeMaxHeap = maxHeap.size
            val sizeMinHeap = minHeap.size

            if ((sizeMaxHeap - sizeMinHeap).absoluteValue > 1) {

                if (sizeMaxHeap > sizeMinHeap) {
                    val max = maxHeap.remove()
                    minHeap.add(max)
                } else {
                    val min = minHeap.remove()
                    maxHeap.add(min)
                }
            }

            sum += getMedian(maxHeap, minHeap)

        }

        return sum

    }

    private fun getMedian(maxHeap: PriorityQueue<Int>, minHeap: PriorityQueue<Int>): Int {

        val sizeMaxHeap = maxHeap.size
        val sizeMinHeap = minHeap.size
        val size = sizeMaxHeap + sizeMinHeap

        if (size % 2 == 0 || sizeMaxHeap > sizeMinHeap) {
            return maxHeap.peek()
        }

        return minHeap.peek()
    }
}