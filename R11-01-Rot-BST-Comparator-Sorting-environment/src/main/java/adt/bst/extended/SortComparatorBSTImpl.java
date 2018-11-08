package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

   private Comparator<T> comparator;

   public SortComparatorBSTImpl(Comparator<T> comparator) {
      super();
      this.comparator = comparator;
   }

   @Override
   public T[] sort(T[] array) {
      SortComparatorBSTImpl<T> auxBST = new SortComparatorBSTImpl<>(getComparator());
      for (T element : array) {
         auxBST.insert(element);
      }
      T[] arrayResult = (T[]) new Comparable[auxBST.size()];
      SortOrder(arrayResult, 0, auxBST.getRoot());
      return arrayResult;
   }

   private int SortOrder(T[] arrayResult, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         index = SortOrder(arrayResult, index, (BSTNode<T>) node.getLeft());
         arrayResult[index++] = node.getData();
         index = SortOrder(arrayResult, index, (BSTNode<T>) node.getRight());
      }
      return index;
   }

   @Override
   public T[] reverseOrder() {
      T[] arrayResult = (T[]) new Comparable[size()];
      ReverseOrder(arrayResult, 0, this.getRoot());
      return arrayResult;
   }

   private int ReverseOrder(T[] arrayResult, int index, BSTNode<T> node) {
      if (!node.isEmpty()) {
         index = ReverseOrder(arrayResult, index, (BSTNode<T>) node.getRight());
         arrayResult[index++] = node.getData();
         index = ReverseOrder(arrayResult, index, (BSTNode<T>) node.getLeft());
      }
      return index;
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
         } else if (getComparator().compare(node.getData(), element) > 0) {
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
            if (getComparator().compare(node.getData(), element) < 0) {
               insert(element, (BSTNode<T>) node.getRight());
            } else {
               insert(element, (BSTNode<T>) node.getLeft());
            }
         }
      }

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

   private BSTNode<T> sucessor(BSTNode<T> node) {
      BSTNode<T> result = super.minimum((BSTNode<T>) node.getRight());

      if (result == null) {
         result = (BSTNode<T>) node.getParent();
         while (result != null && getComparator().compare(node.getData(), result.getData()) > 0) {
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
      BSTNode<T> result = super.maximum((BSTNode<T>) node.getLeft());

      if (result == null) {
         result = (BSTNode<T>) node.getParent();
         while (result != null && getComparator().compare(node.getData(), result.getData()) < 0) {
            result = (BSTNode<T>) result.getParent();
         }
      }
      return result;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

}
