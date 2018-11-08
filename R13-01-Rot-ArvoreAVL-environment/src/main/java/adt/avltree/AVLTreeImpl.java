package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
        AVLTree<T> {

    // TODO Do not forget: you must override the methods insert and remove
    // conveniently.

    // AUXILIARY
    protected int calculateBalance(BSTNode<T> node) {
        int size = 0;
        if (!node.isEmpty()) {
            size = super.height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());
        }
        return size;
    }

    // AUXILIARY
    protected void rebalance(BSTNode<T> node) {
        int balance = calculateBalance(node);
        if (!node.isEmpty() && !(Math.abs(balance) <= 1)) {
            if(balance > 0){
                int banlaceFilho = calculateBalance((BSTNode<T>) node.getLeft());
                if(banlaceFilho < 0){
                    Util.leftRotation((BSTNode<T>) node.getLeft());
                }
                Util.rightRotation(node);
            }
        }
    }

    // AUXILIARY
    protected void rebalanceUp(BSTNode<T> node) {
        if (!node.isEmpty()) {

            BSTNode<T> parent = (BSTNode<T>) node.getParent();
            while (!parent.isEmpty()) {
                rebalance(parent);
                parent = (BSTNode<T>) parent.getParent();
            }
        }
    }
}
