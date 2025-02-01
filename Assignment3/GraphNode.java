// This is written by Tazrin Khalid. This program creates a node, like a vertex, for a graph.


public class GraphNode {
    // Private members to store information about the node
    private int name;      // Name or identifier of the node
    private boolean marked; // Flag to indicate whether the node is marked or not

    // Constructor to initialize the node with a given name and unmarked state
    public GraphNode(int name) {
        this.name = name;
        this.marked = false;
    }

    // Method to mark or unmark the node
    public void mark(boolean mark) {
        this.marked = mark;
    }

    // Method to check if the node is marked
    public boolean isMarked() {
        return marked;
    }

    // Method to get the name of the node
    public int getName() {
        return name;
    }
}
