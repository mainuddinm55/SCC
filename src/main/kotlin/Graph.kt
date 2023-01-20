import java.util.LinkedList

class Graph(val v: Int) {
    val edges: LinkedList<Int> = LinkedList()
    var startTime: Int? = null
    var endTime: Int? = null

    fun addEdge(edge: Int) {
        edges.add(edge)
    }
}