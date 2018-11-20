package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   private BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   protected void setRoot(BSTNode<T> node){
      root = node;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      return this.height(this.getRoot());
   }

   protected int height(BSTNode<T> node) {
      int result = 0;
      if (node.isEmpty()) {
         result = -1;
      } else {
         result = Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
      }
      return result;
   }

   @Override
   public BSTNode<T> search(T element) {
      return this.search(element, getRoot());
   }

   private BSTNode<T> search(T element, BSTNode<T> node) {
      BSTNode<T> result = new BSTNode();
      if (element != null && !node.isEmpty()) {
         if (node.getData().equals(element)) {
            result = node;
         } else if (node.getData().compareTo(element) > 0) {
            result = this.search(element, (BSTNode<T>) node.getLeft());
         } else {
            result = this.search(element, (BSTNode<T>) node.getRight());
         }
      }
      return result;
   }

   @Override
   public void insert(T element) {
      insert(element, getRoot());
   }

   private void insert(T element, BSTNode<T> node) {
      if (element != null) {
         if (node.isEmpty()) {
            node.setData(element);
            node.setLeft(new BSTNode<T>());
            node.setRight(new BSTNode<T>());
            node.getLeft().setParent(node);
            node.getRight().setParent(node);
         } else {
            if (element.compareTo(node.getData()) > 0) {
               insert(element, (BSTNode<T>) node.getRight());
            } else {
               insert(element, (BSTNode<T>) node.getLeft());
            }
         }
      }

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
      if (node.isEmpty()) {

      } else if (node.getRight().isEmpty()) {
         result = node;
      } else {
         result = maximum((BSTNode<T>) node.getRight());
      }
      return result;
   }

   private BSTNode<T> minimum(BSTNode<T> node) {
      BSTNode<T> result = null;
      if (node.isEmpty()) {

      } else if (node.getLeft().isEmpty()) {
         result = node;
      } else {
         result = minimum((BSTNode<T>) node.getLeft());
      }
      return result;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> node = search(element);

      if (node.isEmpty()) {
         node = null;
      } else {
         node = sucessor(node);
      }
      return node;
   }

   protected BSTNode<T> sucessor(BSTNode<T> node) {
      BSTNode<T> result = minimum((BSTNode<T>) node.getRight());

      if (result == null) {
         result = (BSTNode<T>) node.getParent();
         while (result != null && result.getData().compareTo(node.getData()) < 0) {
            result = (BSTNode<T>) result.getParent();
         }
      }
      return result;
   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> node = search(element);

      if (node.isEmpty()) {
         node = null;
      } else {
         node = predecessor(node);
      }
      return node;
   }

   private BSTNode<T> predecessor(BSTNode<T> node) {
      BSTNode<T> result = maximum((BSTNode<T>) node.getLeft());

      if (result == null) {
         result = (BSTNode<T>) node.getParent();
         while (result != null && result.getData().compareTo(node.getData()) > 0) {
            result = (BSTNode<T>) result.getParent();
         }
      }
      return result;
   }

   @Override
   public void remove(T element) {
      BSTNode<T> node = search(element);
      if (!node.isEmpty()) {
         remove(node);
      }
   }

   private void remove(BSTNode<T> node) {
      if (node.isLeaf()) {
         node.setData(null);
      } else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
         if (node.getParent() == null) {
            node.getLeft().setParent(null);
            root = (BSTNode<T>) node.getLeft();
         } else if (node.getParent().getLeft().equals(node)) {
            node.getParent().setLeft(node.getLeft());
         } else {
            node.getParent().setRight(node.getLeft());
         }
         node.getLeft().setParent(node.getParent());
      } else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
         if (node.getParent() == null) {
            node.getRight().setParent(null);
            root = (BSTNode<T>) node.getRight();
         } else if (node.getParent().getLeft().equals(node)) {
            node.getParent().setLeft(node.getRight());
         } else {
            node.getParent().setRight(node.getRight());
         }
         node.getRight().setParent(node.getParent());
      } else {
         BSTNode<T> nodeAux = sucessor(node);
         node.setData(nodeAux.getData());
         remove(nodeAux);
      }
   }

   @Override
   public T[] preOrder() {
      T[] arrayResult = (T[]) new Comparable[size()];
      preOrder(arrayResult, 0, this.getRoot());
      return arrayResult;
   }

   private int preOrder(T[] arrayResult, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         arrayResult[index++] = node.getData();
         index = preOrder(arrayResult, index, (BSTNode<T>) node.getLeft());
         index = preOrder(arrayResult, index, (BSTNode<T>) node.getRight());
      }
      return index;
   }

   @Override
   public T[] order() {
      T[] arrayResult = (T[]) new Comparable[size()];
      order(arrayResult, 0, this.getRoot());
      return arrayResult;
   }

   private int order(T[] arrayResult, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         index = order(arrayResult, index, (BSTNode<T>) node.getLeft());
         arrayResult[index++] = node.getData();
         index = order(arrayResult, index, (BSTNode<T>) node.getRight());
      }
      return index;
   }

   @Override
   public T[] postOrder() {
      T[] arrayResult = (T[]) new Comparable[size()];
      postOrder(arrayResult, 0, this.getRoot());
      return arrayResult;
   }

   private int postOrder(T[] arrayResult, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         index = postOrder(arrayResult, index, (BSTNode<T>) node.getLeft());
         index = postOrder(arrayResult, index, (BSTNode<T>) node.getRight());
         arrayResult[index++] = node.getData();
      }
      return index;
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
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

}
