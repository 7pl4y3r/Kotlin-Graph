package dynamic


import java.util.Scanner
import kotlin.math.cos


//Used to test the code
fun main() {

    val graph = Graph(3)

    graph.setCostExample()
    graph.setCostsForKruskal()

}


class Graph(private var n: Int) {


    private val nodeList = ArrayList<Node>()
    private val costNodeList = ArrayList<CostNode>()
    private val scn = Scanner(System.`in`)


    fun setGraphExample() {

        n = 5

        val list0 = ArrayList<Int>()
        val list1 = ArrayList<Int>()
        val list2 = ArrayList<Int>()
        val list3 = ArrayList<Int>()
        val list4 = ArrayList<Int>()

        list0.add(1)
        list0.add(2)
        list0.add(4)

        list1.add(0)
        list1.add(2)

        list2.add(0)
        list2.add(1)
        list2.add(3)

        list3.add(2)
        list3.add(4)

        list4.add(0)
        list4.add(3)


        nodeList.add(Node(0, list0))
        nodeList.add(Node(1, list1))
        nodeList.add(Node(2, list2))
        nodeList.add(Node(3, list3))
        nodeList.add(Node(4, list4))

    }


    fun setCostExample() {

        n = 5

        val list0 = ArrayList<Costs>()
        val list1 = ArrayList<Costs>()
        val list2 = ArrayList<Costs>()
        val list3 = ArrayList<Costs>()
        val list4 = ArrayList<Costs>()

        list0.add(Costs(1, 2))
        list0.add(Costs(2, 4))
        list0.add(Costs(4, 5))

        list1.add(Costs(0, 2))
        list1.add(Costs(2, 1))

        list2.add(Costs(0, 4))
        list2.add(Costs(1, 2))
        list2.add(Costs(3, 1))

        list3.add(Costs(2, 1))
        list3.add(Costs(4, 6))

        list4.add(Costs(0, 5))
        list4.add(Costs(3, 6))


        costNodeList.add(CostNode(0, list0))
        costNodeList.add(CostNode(1, list1))
        costNodeList.add(CostNode(2, list2))
        costNodeList.add(CostNode(3, list3))
        costNodeList.add(CostNode(4, list4))

    }


    fun readGraph() {

        var id = -1
        var num = 0

        for (i in 0 until n) {

            print("id = ")
            id = scn.nextInt()

            print("Number of neighbors = ")
            num = scn.nextInt()

            nodeList.add(Node(id, readNeighbors(num)))

        }

    }


    private fun readNeighbors(num: Int): ArrayList<Int> {

        val list = ArrayList<Int>()
        var id = -1

        println("Reading neighbors")
        for (i in 0 until num) {

            print("Neighbor $i id = ")
            id = scn.nextInt()

            list.add(id)

        }

        return list
    }


    fun printGraph() {

        for (element in nodeList) {

            print("${element.id} -> ")
            element.printNeighbors()
            println()

        }

    }


    fun printAllRanks() {

        for (i in 0 until nodeList.size)
            println("Node $i rank is ${nodeList[i].neighbors.size}")

    }

    fun getRankById(targetId: Int): Int {

        var i = 0

        while (targetId != nodeList[i].id && i < nodeList.size)
            i++


        return if (i in 0 until nodeList.size)
            nodeList[i].neighbors.size else -1
    }


    fun bft(start: Int) {

        val queue = ArrayList<Int>()
        val visited = BooleanArray(n) {false}

        queue.add(start)
        visited[start] = true

        var i = 0
        while (i < queue.size) {

            for (element in nodeList[queue[i]].neighbors) {
                if (!visited[element]) {

                    queue.add(element)
                    visited[element] = true

                }

            }

            print("${queue[i]} ")
            i++

        }

    }


    fun dft(visited: BooleanArray, x: Int) {

        print("$x ")
        visited[x] = true

        for (element in nodeList[x].neighbors) {
            if (!visited[element])
                dft(visited, element)
        }

    }


    fun dijkstra(start: Int) {

        val visited = BooleanArray(n)
        val tree = IntArray(n)
        val cost = IntArray(n)

        var minimumIndex = -1
        var currentCost = -1


        visited[start] = true
        for (i in 0 until costNodeList[start].neighbors.size) {

            cost[i] = costNodeList[start].neighbors[i].cost
            if (cost[i] < Int.MAX_VALUE)
                tree[i] = start

        }


        for (i in 0 until (n - 1)) {

            for (j in 0 until n) {
                if (!visited[j] && cost[j] < Int.MAX_VALUE)
                    minimumIndex = j
            }


            visited[minimumIndex] = true
            for (j in 0 until costNodeList[minimumIndex].neighbors.size) {

                    currentCost = costNodeList[minimumIndex].neighbors[j].cost
                    if (!visited[j] &&
                        cost[j] > cost[minimumIndex] + currentCost && currentCost < Int.MAX_VALUE
                    ) {

                        cost[j] = cost[minimumIndex] + currentCost
                        tree[j] = minimumIndex

                    }
            }

        }

        printDijkstra(tree, start)
    }


    private fun printDijkstra(tree: IntArray, index: Int) {

        if (tree[index] != 0) {
            printDijkstra(tree, tree[index])
            print("$index ")
        }

    }


    fun setCostsForKruskal() {

        val costList = ArrayList<Cost>()
        n = 7

        costList.add(Cost(1, 2, 5))
        costList.add(Cost(1, 3, 8))
        costList.add(Cost(2, 3, 11))
        costList.add(Cost(2, 4, 10))
        costList.add(Cost(3, 4, 15))
        costList.add(Cost(3, 6, 9))
        costList.add(Cost(4, 5, 2))
        costList.add(Cost(4, 6, 7))
        costList.add(Cost(5, 6, 10))
        costList.add(Cost(5, 7, 4))
        costList.add(Cost(7, 6, 12))

        sortList(costList, 0, costList.size - 1)
        kruskal(costList)

    }


    private fun partition(list: ArrayList<Cost>, low: Int, high: Int): Int {

        val pivot = list[high].cost
        var i = low - 1
        var temp: Cost

        for (j in low until high) {

            if (list[j].cost <= pivot) {
                i++

                temp = list[i]
                list[i] = list[j]
                list[j] = temp

            }

        }

        temp = list[i + 1]
        list[i + 1] = list[high]
        list[high] = temp

        return i + 1
    }

    private fun sortList(list: ArrayList<Cost>, low: Int, high: Int) {

        if (low < high) {

            val pi = partition(list, low, high)

            sortList(list, low, pi - 1)
            sortList(list, pi + 1, high)

        }

    }

    private fun kruskal(costList: ArrayList<Cost>) {

        val visited = IntArray(costList.size)
        var totalCost = 0

        for (i in 0 until costList.size)
            visited[i] = i

        var i = 0
        var k = 0
        while (k < n - 1) {

            if (visited[costList[i].x] != visited[costList[i].y]) {

                println("${costList[i].x} ${costList[i].y}")
                totalCost += costList[i].cost
                k++

                for (j in 0 until n) {
                    if (visited[j] == costList[i].x)
                        visited[j] = costList[i].y
                }

            }

            i++
        }

        println("Total cost -> $totalCost")
    }

}