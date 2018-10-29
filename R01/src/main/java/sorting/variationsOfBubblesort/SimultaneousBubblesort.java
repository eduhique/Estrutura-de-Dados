package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(array == null || array.length == 0 || leftIndex < 0 || leftIndex > rightIndex
				|| rightIndex > array.length - 1)) {
			for (int i = leftIndex; i < rightIndex; i++) {
				int pass = 0;
				for (int j = leftIndex + pass; j < rightIndex - i; j++) {
					if (array[j].compareTo(array[j + 1]) > 0) {
						Util.swap(array, j, j + 1);
						pass++;
					}
				}
				for (int j = rightIndex - pass; j > i; j--) {
					if (array[j].compareTo(array[j - 1]) < 0) {
						Util.swap(array, j, j - 1);
						pass++;
					}
				}
			}

		}
	}
}
