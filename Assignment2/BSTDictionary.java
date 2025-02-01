public class BSTDictionary implements BSTDictionaryADT {

    private BinarySearchTree bst;

    // Constructor
    public BSTDictionary() {
        this.bst = new BinarySearchTree();
    }

    // get method
    @Override
    public Record get(Key k) {
    	BSTNode node = bst.get(bst.getRoot(), k);
    	return (node != null) ? node.getRecord() : null;

    }

    // put method
    @Override
    public void put(Record d) throws DictionaryException {
        try {
            bst.insert(bst.getRoot(), d);
        } catch (DictionaryException e) {
            throw new DictionaryException("A duplicate key has been found!");
        }
    }

    // remove method
    @Override
    public void remove(Key k) throws DictionaryException {
        try {
            bst.remove(bst.getRoot(), k);
        } catch (DictionaryException e) {
            throw new DictionaryException("Cannot find key!");
        }
    }

    // successor method
    @Override
    public Record successor(Key k) {
        BSTNode successorNode = bst.successor(bst.getRoot(), k);
        return (successorNode != null) ? successorNode.getRecord() : null;
    }


    // predecessor method
    @Override
    public Record predecessor(Key k) {
        BSTNode predecessorNode = bst.predecessor(bst.getRoot(), k);
        return (predecessorNode != null) ? predecessorNode.getRecord() : null;
    }

    // smallest method
    @Override
    public Record smallest() {
        BSTNode smallestNode = bst.smallest(bst.getRoot());
        return (smallestNode != null) ? smallestNode.getRecord() : null;
    }


    // largest method
    @Override
    public Record largest() {
        BSTNode largestNode = bst.largest(bst.getRoot());
        return (largestNode != null) ? largestNode.getRecord() : null;
    }


}