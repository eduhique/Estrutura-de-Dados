package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

    protected BSTNode<T> root;

    public BSTImpl() {
        root = new BSTNode<T>();
    }

    public BSTNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public int height() {
        return this.height(this.getRoot());
    }

    private int height(BSTNode<T> node) {
        int result = 0;
        if (node.isEmpty()) {
            result = -1;
        } else {
            result = Math.max(this.height((BSTNode<T>) getRoot().getLeft()), this.height((BSTNode<T>) this.getRoot().getRight())) + 1;
        }
        return result;
    }

    @Override
    public BSTNode<T> search(T element) {
        return this.search(element, getRoot());
    }

    private BSTNode<T> search(T element, BSTNode<T> node) {
        BSTNode<T> result = new BSTNode();
        if(element != null || !node.isEmpty()){
            if (node.getData().compareTo(element) > 0) {
                result = this.search(element, (BSTNode<T>) node.getLeft());
            } else {
                result = this.search(element, (BSTNode<T>) node.getRight());
            }
        }
        return result;
    }

    @Override
    public void insert(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public BSTNode<T> maximum() {
        return maximum(getRoot());

    }

    @Override
    public BSTNode<T> minimum() {
        return minimum(getRoot());
    }

    private BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> result = null;
        if (node.getRight().isEmpty()) {
            result = (BSTNode<T>) node.getData();
        } else {
            maximum((BSTNode<T>) node.getRight());
        }
        return result;
    }

    private BSTNode<T> minimum(BSTNode<T> node) {
        BSTNode<T> result = null;
        if (node.getLeft().isEmpty()) {
            result = (BSTNode<T>) node.getData();
        } else {
            minimum((BSTNode<T>) node.getLeft());
        }
        return result;
    }

    @Override
    public BSTNode<T> sucessor(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public BSTNode<T> predecessor(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public void remove(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public T[] preOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public T[] order() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public T[] postOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * This method is already implemented using recursion. You must understand
     * how it work and use similar idea with the other methods.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode<T> node) {
        int result = 0;
        // base case means doing nothing (return 0)
        if (!node.isEmpty()) { // indusctive case
            result = 1 + size((BSTNode<T>) node.getLeft())
                    + size((BSTNode<T>) node.getRight());
        }
        return result;
    }

}
