// This class was written by Tazrin Khalid (251294394). It represents a binary search tree data structure.

public class BinarySearchTree {

    // Private member representing the root of the binary search tree
    private BSTNode root;

    // Constructor initializes an empty binary search tree
    public BinarySearchTree() {
        this.root = null;
    }

    // Getter method for accessing the root of the tree
    public BSTNode getRoot() {
        return this.root;
    }

    // Get method to retrieve a node with a specified key from the tree
    public BSTNode get(BSTNode r, Key k) {
        if (r == null || k.compareTo(r.getRecord().getKey()) == 0) {
            return r;
        }

        if (k.compareTo(r.getRecord().getKey()) < 0) {
            return get(r.getLeftChild(), k);
        } else {
            return get(r.getRightChild(), k);
        }
    }

    // Insert method to add a new node with the given record to the tree
    public void insert(BSTNode r, Record d) throws DictionaryException {
        if (r == null) {
            this.root = new BSTNode(d);
            return;
        }

        int keyComparison = d.getKey().compareTo(r.getRecord().getKey());

        if (keyComparison < 0) {
            if (r.getLeftChild() == null) {
                r.setLeftChild(new BSTNode(d));
                r.getLeftChild().setParent(r);
            } else {
                insert(r.getLeftChild(), d);
            }
        } else if (keyComparison > 0) {
            if (r.getRightChild() == null) {
                r.setRightChild(new BSTNode(d));
                r.getRightChild().setParent(r);
            } else {
                insert(r.getRightChild(), d);
            }
        } else {
            // Throw an exception if a node with a duplicate key is found
            throw new DictionaryException("Duplicate key found.");
        }
    }

    // Remove method to delete a node with the specified key from the tree
    public void remove(BSTNode r, Key key) throws DictionaryException {
        BSTNode node = get(r, key);

        if (node == null) {
            // Throw an exception if the key is not found in the tree
            throw new DictionaryException("Key not found.");
        }

        if (node.isLeaf()) {
            // Handle the case where the node is a leaf
            if (node.getParent() == null) {
                this.root = null;
            } else {
                if (node.getParent().getLeftChild() == node) {
                    node.getParent().setLeftChild(null);
                } else {
                    node.getParent().setRightChild(null);
                }
            }
        } else if (node.getLeftChild() != null && node.getRightChild() != null) {
            // Handle the case where the node has two children
            BSTNode successor = smallest(node.getRightChild());
            node.setRecord(successor.getRecord());
            remove(successor, successor.getRecord().getKey());
        } else {
            // Handle the case where the node has one child
            BSTNode child = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();
            BSTNode parent = node.getParent();

            if (parent == null) {
                this.root = child;
                child.setParent(null);
            } else {
                if (parent.getLeftChild() == node) {
                    parent.setLeftChild(child);
                } else {
                    parent.setRightChild(child);
                }
                child.setParent(parent);
            }
        }
    }

    // Successor method to find the node with the smallest key greater than the specified key
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode node = get(r, k);

        if (node == null) {
            return null;
        }

        if (node.getRightChild() != null) {
            return smallest(node.getRightChild());
        }

        BSTNode parent = node.getParent();
        while (parent != null && node == parent.getRightChild()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    // Predecessor method to find the node with the largest key smaller than the specified key
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode node = get(r, k);

        if (node == null) {
            return null;
        }

        if (node.getLeftChild() != null) {
            return largest(node.getLeftChild());
        }

        BSTNode parent = node.getParent();
        while (parent != null && node == parent.getLeftChild()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    // Smallest method to find the node with the smallest key in the subtree
    public BSTNode smallest(BSTNode r) {
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        return r;
    }

    // Largest method to find the node with the largest key in the subtree
    public BSTNode largest(BSTNode r) {
        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }
        return r;
    }
}
