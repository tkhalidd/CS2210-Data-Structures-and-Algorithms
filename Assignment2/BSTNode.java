// This class was written by Tazrin Khalid (251294394). This class represents a node in a binary search tree.

public class BSTNode {

    // Instance variables to store record information and node connections
    private Record item;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;

    // Constructor to initialize the BSTNode object with a record
    public BSTNode(Record item) {
        // Set the record for the node, and initialize child and parent references to null
        this.item = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    // Getter method to retrieve the record of the node
    public Record getRecord() {
        return item;
    }

    // Setter method to update the record of the node
    public void setRecord(Record d) {
        this.item = d;
    }

    // Getter methods to retrieve left child, right child, and parent of the node
    public BSTNode getLeftChild() {
        return leftChild;
    }

    public BSTNode getRightChild() {
        return rightChild;
    }

    public BSTNode getParent() {
        return parent;
    }

    // Setter methods to update left child, right child, and parent of the node
    public void setLeftChild(BSTNode u) {
        this.leftChild = u;
    }

    public void setRightChild(BSTNode u) {
        this.rightChild = u;
    }

    public void setParent(BSTNode u) {
        this.parent = u;
    }

    // Method to check if the node is a leaf (has no children)
    public boolean isLeaf() {
        return (rightChild == null && leftChild == null);
    }
}
