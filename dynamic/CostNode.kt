package dynamic

class CostNode(var id: Int, var neighbors: ArrayList<Costs>)

class Cost(var x: Int, var y: Int, var cost: Int)

class Costs(var id: Int, var cost: Int)