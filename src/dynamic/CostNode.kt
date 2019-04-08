package dynamic

class CostNode(var id: Int, var neighbors: ArrayList<Costs>)

class Costs(var id: Int, var cost: Int)

class CostXY(var x: Int, var y: Int, var cost: Int)