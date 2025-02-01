// This is written by Tazrin Khalid. This program creates an edge with types and labels for a graph.

public class GraphEdge {
    // Private members to store information about the edge
    private GraphNode u;      // First endpoint
    private GraphNode v;      // Second endpoint
    private int type;         // Type of the edge
    private String label;     // Label of the edge

    // Constructor to initialize the edge with given endpoints, type, and label
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        this.u = u;
        this.v = v;
        this.type = type;
        this.label = label;
    }

    // Method to get the first endpoint of the edge
    public GraphNode firstEndpoint() {
        return u;
    }

    // Method to get the second endpoint of the edge
    public GraphNode secondEndpoint() {
        return v;
    }

    // Method to get the type of the edge
    public int getType() {
        return type;
    }

    // Method to set a new type for the edge
    public void setType(int newType) {
        this.type = newType;
    }

    // Method to get the label of the edge
    public String getLabel() {
        return label;
    }

    // Method to set a new label for the edge
    public void setLabel(String newLabel) {
        this.label = newLabel;
    }
}
