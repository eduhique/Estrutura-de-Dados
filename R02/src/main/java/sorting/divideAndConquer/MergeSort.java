package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {


	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length != 1 && leftIndex < rightIndex && array != null && leftIndex >= 0
				&& rightIndex < array.length) {
			int middle = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[] result = Arrays.copyOf(array, array.length);

		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

		while (i <= middle && j <= rightIndex) {
			if (result[i].compareTo(result[j]) <= 0) {
				array[k] = result[i];
				i++;
			} else {
				array[k] = result[j];
				j++;
			}

			k++;
		}

		while (i <= middle) {
			array[k] = result[i];
			i++;
			k++;
		}
	}
}
