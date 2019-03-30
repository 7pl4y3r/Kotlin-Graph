package matrix


import java.util.Scanner


private val scn = Scanner(System.`in`)



fun main() {

    print("n = ")
    val n = scn.nextInt()
    val mat = Array(n) {IntArray(n)}

    readMatrix(mat, n)

    println("The matrix is:")
    printMatrix(mat, n)

    println("The ranks are:")
    printRanks(mat, n)

}


fun readMatrix(mat: Array<IntArray>, n: Int) {


    for (i in 0 until n)
        for (j in 0 until n) {
            print("m[$i][$j] = ")
            mat[i][j] = scn.nextInt()
        }

}


fun printMatrix(mat: Array<IntArray>, n: Int) {

    for (i in 0 until n) {
        for (j in 0 until n)
            print("${mat[i][j]} ")

        println()
    }

}


fun printRanks(mat: Array<IntArray>, n: Int) {

    for (i in 0 until n)
        println("Node $i has rank ${getRank(mat, n, i)}")

}


private fun getRank(mat: Array<IntArray>, n: Int, i: Int): Int {

    var s = 0
    for (j in 0 until n)
        s += mat[i][j]

    return s

}


fun DF(mat: Array<IntArray>, vis: BooleanArray, n: Int, x: Int) {

    print("$x ")
    vis[x] = true
    for (i in 0 until n)
        if (mat[x][i] == 1 && !vis[i])
            DF(mat, vis, n, i)

}


fun BF(mat: Array<IntArray>, n: Int, start: Int) {

    val queue = Array(n) {0}
    val vis = Array(n) {false}

    var f = 1
    var l = 1
    var x = -1

    queue[f] = start
    vis[f] = true

    while (f <= l) {

        x = queue[f]
        for (i in 0 until n) {
            if (mat[x][i] == 1 && !vis[i]) {
                l++
                queue[l] = i
                vis[i] = true
            }
        }


        print("${queue[f]} ")
        f++

    }

}
