package org.inego.brum.base


interface MapGraphNode {
    val name: String
}


class MapGraph {

    val nodes = mutableSetOf<MapGraphNode>()

    val connections = mutableMapOf<MapGraphNode, MutableSet<MapGraphNode>>()

    fun add(newNodes: List<MapGraphNode>) {
        nodes.addAll(newNodes)
    }

    fun add(newNode: MapGraphNode, vararg newNodeConnections: MapGraphNode) {

        nodes.add(newNode)

        for (connection in newNodeConnections) {
            addConnectionBetween(newNode, connection)
        }
    }

    private fun addConnectionBetween(node1: MapGraphNode, node2: MapGraphNode) {
        addConnectionFromTo(node1, node2)
        addConnectionFromTo(node2, node1)
    }

    private fun addConnectionFromTo(nodeFrom: MapGraphNode, nodeTo: MapGraphNode) {
        connections.computeIfAbsent(nodeFrom) { mutableSetOf() }.add(nodeTo)
    }
}
