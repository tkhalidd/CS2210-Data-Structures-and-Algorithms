// This is written by Tazrin Khalid. This code creates a graph with nodes (vertices) and edges.

import java.util.*;

public class Graph {
    private int numNodes;

    private GraphNode[] graph;
    private GraphEdge[][] edges;

    // Constructor for the Graph class
    public Graph(int n) {
        numNodes = n;
        graph = new GraphNode[n];
        edges = new GraphEdge[n][n];

        // Initialize graph nodes
        for (int i = 0; i < n; i++) {
            graph[i] = new GraphNode(i);
        }
    }

    // Method to insert an edge between two nodes
    public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
        // Check if the nodes are valid
        if (nodeu.getName() >= numNodes || nodeu.getName() < 0 || nodev.getName() >= numNodes || nodev.getName() < 0) {
            throw new GraphException("Invalid node given to insert");
        }

        // Create edges between the two nodes
        edges[nodev.getName()][nodeu.getName()] = new GraphEdge(nodeu, nodev, type, label);
        edges[nodeu.getName()][nodev.getName()] = new GraphEdge(nodeu, nodev, type, label);
    }

    // Method to get a specific node in the graph
    public GraphNode getNode(int u) throws GraphException {
        // Check if the node is valid
        if (u >= numNodes || u < 0) {
            throw new GraphException("This node does not exist within your graph");
        }
        return graph[u];
    }

    // Method to get the incident edges of a node
    public Iterator incidentEdges(GraphNode u) throws GraphException {
        // Check if the node is valid
        if (u.getName() >= numNodes || u.getName() < 0) {
            throw new GraphException("Invalid nodes given");
        }

        Stack edgeStack = new Stack();
        int i = 0;

        // Push non-null edges onto the stack
        while (i < edges[u.getName()].length) {
            if (edges[u.getName()][i] != null) {
                edgeStack.push(edges[u.getName()][i]);
            }
            i++;
        }

        return edgeStack.iterator();
    }

    // Method to get a specific edge between two nodes
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        int uName = u.getName();
        int vName = v.getName();

        // Check if the nodes and the edge are valid
        if (uName >= numNodes || uName < 0 || vName >= numNodes || vName < 0
                || (edges[uName][vName] == null && edges[vName][uName] == null) || !areAdjacent(u, v)) {
            throw new GraphException("Edge not found between given nodes");
        }

        return edges[uName][vName];
    }

    // Method to check if two nodes are adjacent
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        int uName = u.getName();
        int vName = v.getName();

        // Check if the nodes are valid
        if (isInvalidNode(uName) || isInvalidNode(vName)) {
            throw new GraphException("Invalid nodes given to check for adjacency");
        }

        return edges[uName][vName] != null;
    }

    // Helper method to check if a node is invalid
    private boolean isInvalidNode(int nodeName) {
        return nodeName >= numNodes || nodeName < 0;
    }
}
