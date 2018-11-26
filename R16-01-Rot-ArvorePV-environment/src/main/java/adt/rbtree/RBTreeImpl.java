package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements RBTree<T> {

    public RBTreeImpl() {
        this.root = new RBNode<T>();
    }

    protected int blackHeight() {
        return blackHeight((RBNode<T>) this.root);
    }

    private int blackHeight(RBNode<T> node) {
        if (node.isEmpty())
            return 0;

        int height;

        if (node.getColour().equals(Colour.BLACK))
            height = 1;
        else
            height = 0;

        return height + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
    }

    protected boolean verifyProperties() {
        boolean resp = verifyNodesColour() && verifyNILNodeColour()
                && verifyRootColour() && verifyChildrenOfRedNodes()
                && verifyBlackHeight();

        return resp;
    }

    /**
     * The colour of each node of a RB tree is black or red. This is guaranteed
     * by the type Colour.
     */
    private boolean verifyNodesColour() {
        return true; // already implemented
    }

    /**
     * The colour of the root must be black.
     */
    private boolean verifyRootColour() {
        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
        // implemented
    }

    /**
     * This is guaranteed by the constructor.
     */
    private boolean verifyNILNodeColour() {
        return true; // already implemented
    }

    /**
     * Verifies the property for all RED nodes: the children of a red node must
     * be BLACK.
     */
    private boolean verifyChildrenOfRedNodes() {
        return verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
    }

    private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
        boolean result = true;
        if (!node.isEmpty()) {
            if (node.getColour().equals(Colour.RED)) {
                if (!((RBNode<T>) node.getLeft()).getColour().equals(Colour.BLACK) || !((RBNode<T>) node.getRight()).getColour().equals(Colour.BLACK)) {
                    result = false;
                }
            }
            result = this.verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && this.verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
        }
        return result;
    }

    /**
     * Verifies the black-height property from the root.
     */
    private boolean verifyBlackHeight() {
        boolean result = false;
        int leftHeight = verifyBlackHeight((RBNode<T>) this.getRoot().getLeft(), 0);
        int rightHeight = verifyBlackHeight((RBNode<T>) this.getRoot().getRight(), 0);

        if (leftHeight == rightHeight){
            result = true;
        }

        return result;
    }

    private int verifyBlackHeight(RBNode<T> node, int index) {
        if (node != null && !node.isEmpty()) {
            if (node.getColour().equals(Colour.BLACK))
                index++;

            return Math.max(verifyBlackHeight((RBNode<T>) node.getLeft(), index),
                    verifyBlackHeight((RBNode<T>) node.getRight(), index));
        }

        index++;
        return index;
    }

    @Override
    public void insert(T value) {
        insert(value, (RBNode<T>) getRoot());
    }

    private void insert(T element, RBNode<T> node) {
        if (element != null) {
            if (node.isEmpty()) {
                node.setData(element);
                node.setLeft(new RBNode<T>());
                node.setRight(new RBNode<T>());
                node.getLeft().setParent(node);
                node.getRight().setParent(node);
                node.setColour(Colour.RED);
                fixUpCase1(node);
            } else {
                if (element.compareTo(node.getData()) > 0) {
                    insert(element, (RBNode<T>) node.getRight());
                } else {
                    insert(element, (RBNode<T>) node.getLeft());
                }
            }
        }

    }

    @Override
    public RBNode<T>[] rbPreOrder() {
        RBNode<T>[] arrayResult = new RBNode[size()];
        rbPreOrder(arrayResult, 0, (RBNode<T>) this.getRoot());
        return arrayResult;
    }

    private int rbPreOrder(RBNode<T>[] array, int index, RBNode<T> node) {
        if (!node.isEmpty()) {
            array[index++] = node;
            index = rbPreOrder(array, index, (RBNode<T>) node.getLeft());
            index = rbPreOrder(array, index, (RBNode<T>) node.getRight());
        }
        return index;
    }

    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
        if (node.equals(getRoot()))
            node.setColour(Colour.BLACK);
        else
            this.fixUpCase2(node);
    }

    protected void fixUpCase2(RBNode<T> node) {
        if (!((RBNode<T>) node.getParent()).getColour().equals(Colour.BLACK))
            fixUpCase3(node);
    }

    protected void fixUpCase3(RBNode<T> node) {
        RBNode<T> parent = (RBNode<T>) node.getParent();
        RBNode<T> grandParent = (RBNode<T>) parent.getParent();
        RBNode<T> uncle;

        if (parent.equals(parent.getParent().getLeft()))
            uncle = (RBNode<T>) grandParent.getRight();
        else
            uncle = (RBNode<T>) grandParent.getLeft();

        if (!uncle.getColour().equals(Colour.BLACK)) {
            parent.setColour(Colour.BLACK);
            uncle.setColour(Colour.BLACK);
            grandParent.setColour(Colour.RED);
            fixUpCase1(grandParent);
        } else {
            fixUpCase4(node);
        }
    }

    protected void fixUpCase4(RBNode<T> node) {
        RBNode<T> parent = (RBNode<T>) node.getParent();
        RBNode<T> grand = (RBNode<T>) parent.getParent();

        if (grand.getLeft().equals(parent) && parent.getRight().equals(node)) {
            leftRotation(parent);
            fixUpCase5((RBNode<T>) node.getLeft());
        }
        else if (grand.getRight().equals(parent) && parent.getLeft().equals(node)) {
            rightRotation(parent);
            fixUpCase5((RBNode<T>) node.getRight());
        }
        else {
            fixUpCase5(node);
        }
    }

    protected void fixUpCase5(RBNode<T> node) {
        RBNode<T> parent = (RBNode<T>) node.getParent();
        RBNode<T> grandParent = (RBNode<T>) parent.getParent();

        parent.setColour(Colour.BLACK);
        grandParent.setColour(Colour.RED);

        if (node.equals(node.getParent().getLeft()))
            rightRotation(grandParent);
        else
            leftRotation(grandParent);
    }

    private void leftRotation(BSTNode<T> node) {

        BSTNode<T> aux = Util.leftRotation(node);
        if (root.equals(node)) {
            root = aux;
        }
    }

    private void rightRotation(BSTNode<T> node) {

        BSTNode<T> aux = Util.rightRotation(node);
        if (this.root.equals(node)) {
            root = aux;
        }
    }

}
