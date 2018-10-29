package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verifica(array, leftIndex, rightIndex)) {

		} else {
			int gap = rightIndex - leftIndex + 1;
			boolean swap = true;
			while (gap != 1 || swap) {
				gap = (int) (gap / 1.25);
				if (gap < 1) {
					gap = 1;
				}
				int i = leftIndex;
				swap = false;
				while (i + gap <= rightIndex) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						swap = true;
					}
					i++;
				}
			}
		}
	}

	private boolean verifica(T[] array, int leftIndex, int rightIndex) {
		boolean result = true;
		if (array != null && leftIndex < rightIndex && rightIndex >= 0 && leftIndex < array.length
				&& array.length > 1) {
			result = false;
		}
		return result;
	}
}
