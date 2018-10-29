package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verifica(array, leftIndex, rightIndex)) {

		} else {

			int pivot = leftIndex + 1;
			while (pivot <= rightIndex) {
				if (array[pivot].compareTo(array[pivot - 1]) >= 0) {
					pivot++;
				} else {
					Util.swap(array, pivot, pivot - 1);
					if (pivot > leftIndex + 1) {
						pivot--;
					} else {
						pivot++;
					}
				}
			}
		}
	}

	private boolean verifica(T[] array, int leftIndex, int rightIndex) {
		boolean result = true;
		if (array != null && leftIndex < rightIndex && rightIndex >= 0 && leftIndex < array.length && array.length > 0
				&& rightIndex < array.length) {
			result = false;
		}
		return result;
	}
}
