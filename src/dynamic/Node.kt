package dynamic

class Node(var id: Int, var neighbors: ArrayList<Int>) {


    fun printNeighbors() {

        for (element in neighbors)
            print("$element ")

    }

}