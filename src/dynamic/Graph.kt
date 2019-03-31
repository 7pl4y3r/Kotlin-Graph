package dynamic


import java.util.Scanner


//Used to test the code
fun main() {

    val graph = Graph(3)

    graph.setGraphExample()
    graph.printGraph()
    graph.dft(BooleanArray(5) {false}, 1)

}


class Graph(private var n: Int) {


    private val nodeList = ArrayList<Node>()
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

}