import java.util.Arrays;

public class OrdenacaoRecursiva {

	public static void main(String[] args) {
		OrdenacaoRecursiva b = new OrdenacaoRecursiva();
		Integer[] array1 = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };
		System.out.println(Arrays.toString(array1));
		b.insertionSort(array1, 0, array1.length - 1);
		System.out.println(Arrays.toString(array1));

	}

	public void bubbleSort(Integer[] array, int leftIndex, int rightIndex) {
		if (rightIndex == 0) {

		} else {
			for (int j = leftIndex; j < rightIndex; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
				}
			}
			bubbleSort(array, leftIndex, rightIndex - 1);
		}

	}

	public void selctionSort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex == array.length - 1) {

		} else {
			int min = leftIndex;
			for (int j = leftIndex + 1; j <= rightIndex; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			swap(array, leftIndex, min);
			selctionSort(array, leftIndex + 1, rightIndex);
		}
	}

	public void insertionSort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex == array.length - 1) {

		} else {
			Integer temp = array[leftIndex];
			int j = leftIndex - 1;
			while (j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j -= 1;
			}
			array[j + 1] = temp;
			insertionSort(array, leftIndex + 1, rightIndex);
		}
	}

	private static void swap(Object[] array, int i, int j) {
		if (array == null)
			throw new IllegalArgumentException();

		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}