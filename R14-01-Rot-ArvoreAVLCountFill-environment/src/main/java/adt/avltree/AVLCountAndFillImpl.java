package adt.avltree;

import adt.bst.BSTNode;

import java.util.*;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl() {

   }

   @Override
   public int LLcount() {
      return LLcounter;
   }

   @Override
   public int LRcount() {
      return LRcounter;
   }

   @Override
   public int RRcount() {
      return RRcounter;
   }

   @Override
   public int RLcount() {
      return RLcounter;
   }

   protected void rebalance(BSTNode<T> node) {
      int balance = calculateBalance(node);
      if (!node.isEmpty() && !(Math.abs(balance) <= 1)) {
         if (balance > 0) {
            int banlaceFilho = calculateBalance((BSTNode<T>) node.getLeft());
            if (banlaceFilho < 0) {
               leftRotation((BSTNode<T>) node.getLeft());
               LRcounter++;
            } else {
               LLcounter++;
            }
            rightRotation(node);
         } else {
            int banlaceFilho = calculateBalance((BSTNode<T>) node.getRight());
            if (banlaceFilho > 0) {
               rightRotation((BSTNode<T>) node.getRight());
               RLcounter++;
            } else {
               RRcounter++;
            }
            leftRotation(node);
         }
      }
   }

	@Override
	public void fillWithoutRebalance(T[] array) {
		T[] bst = this.preOrder();
		int aLen = bst.length;
		int bLen = array.length;

		T[] result = Arrays.copyOf(bst, aLen + bLen);
		System.arraycopy(array, 0, result, aLen, bLen);

		this.setRoot(new BSTNode<T>());
		resetCounters();
		Arrays.sort(result);

		Queue<List<Integer>> queue = new LinkedList<>();
		List<Integer> interval = new ArrayList<>();
		Collections.addAll(interval, 0, result.length - 1);
		queue.add(interval);

		while (!queue.isEmpty()) {
			List<Integer> list = queue.remove();
			int left = list.get(0);
			int right = list.get(1);
			int middle = (left + right) / 2;
			if (left < right) {
				List<Integer> leftInterval = new ArrayList<>();
				Collections.addAll(leftInterval, left, middle - 1);
				queue.add(leftInterval);
				List<Integer> rightInterval = new ArrayList<>();
				Collections.addAll(rightInterval, middle + 1, right);
				queue.add(rightInterval);
				super.insert(result[middle]);
			} else {
				super.insert(result[left]);
			}
		}

	}

	private void resetCounters() {
		this.LLcounter = 0;
		this.LRcounter = 0;
		this.RLcounter = 0;
		this.RRcounter = 0;
	}

}
