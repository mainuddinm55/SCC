import java.util.*

/*
9
0 1
1 2
2 3
3 0
2 4
4 5
5 6
6 7
6 4
*/
private var graphs: LinkedList<Graph> = LinkedList()
private var transposeGraphs: LinkedList<Graph> = LinkedList()
private var time = 0
private val stack = Stack<Int>()

private fun dfs(graphs: LinkedList<Graph>, source: Int, isScc: Boolean = false) {
    ++time
    val graph = graphs.find { it.v == source }
    if (graph != null) {
        if (isScc) stack.push(graph.v)
        graph.startTime = time
        for (edge in graph.edges) {
            if ((graphs.find { it.v == edge })?.startTime == null) {
                dfs(graphs, edge,isScc)
            }
        }
        ++time
        graph.endTime = time
    }
}

private fun addEdge(graphs: LinkedList<Graph>, u: Int, v: Int) {
    val uGraph = graphs.find { it.v == u } ?: Graph(u)
    val vGraph = graphs.find { it.v == v } ?: Graph(v)

    uGraph.addEdge(v)
    graphs.indexOf(uGraph).let {
        if (it == -1) {
            graphs.add(uGraph)
        } else {
            graphs.set(it, uGraph)
        }
    }
    graphs.indexOf(vGraph).let {
        if (it == -1) {
            graphs.add(vGraph)
        } else {
            graphs.set(it, vGraph)
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val numOfEdges = scanner.nextInt()

    for (i in 1..numOfEdges) {
        val u = scanner.nextInt()
        val v = scanner.nextInt()
        addEdge(graphs, u, v)
        addEdge(transposeGraphs, v, u)
    }
    scanner.close()

    for (graph in graphs) {
        if (graph.startTime == null) {
            dfs(graphs, graph.v)
        }
    }
    println()
    val temp = mutableListOf<Pair<Int, Int?>>()
    for (graph in graphs) {
        temp.add(Pair(graph.v, graph.endTime))
    }
    temp.sortByDescending { it.second }
    time = 0
    for (pair in temp) {
        val graph = transposeGraphs.find { it.v == pair.first }
        if (graph?.startTime == null) {
            dfs(transposeGraphs, pair.first, true)
            print("SCC are: ")
            while (stack.isNotEmpty()) {
                print(" ${stack.pop()},")
            }
            println()
        }
    }
}