
fun main() {
    val quickFind = QuickFind(10)
    quickFind.isConnected(0, 1)
    quickFind.union(0, 1)
    quickFind.union(1, 2)
    quickFind.isConnected(0, 2)
}

/** Cost model:
 * Number of array accesses (for read or write)
 * Initialize: N
 * Union: N
 * connected: 1
 */
class QuickFind(val n: Int) {

    private val ids = Array (n) { it }

    fun isConnected(p: Int, q: Int): Boolean = ids[p] == ids[q]

    /**
     * To merge components containing p and q, change all entries
     * whose id equals id[p] to id[q]
     */
    fun union(p: Int, q: Int) {
        val pID = ids[p]
        val qID = ids[q]
        for (i in 0 until ids.size) {
            if (ids[i] == pID) {
                ids[i] = qID
            }
        }
    }
}