package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot, particionamos
	 * o array colocando os menores a esquerda do pivot e os maiores a direita do
	 * pivot, e depois aplicamos a mesma estrategia recursivamente na particao a
	 * esquerda do pivot e na particao a direita do pivot.
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do
	 * quicksort pode ser otimizada para lidar de forma mais eficiente com isso.
	 * Essa melhoria eh conhecida como quicksort tree way e consiste da seguinte
	 * ideia: - selecione o pivot e particione o array de forma que * arr[l..i]
	 * contem elementos menores que o pivot * arr[i+1..j-1] contem elementos iguais
	 * ao pivot. * arr[j..r] contem elementos maiores do que o pivot.
	 * 
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh
	 * feito recursivamente.
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && array != null && leftIndex >= 0) {
			int middle = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, middle - 1);
			sort(array, middle + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int pivotIndex = leftIndex;
		int i = leftIndex;
		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(array[pivotIndex]) <= 0) {
				Util.swap(array, ++i, j);
			}
		}
		Util.swap(array, pivotIndex, i);

		int menor = i - 1;
		int maior = i + 1;

		for (int j = leftIndex; j <= rightIndex; j++) {
			if(array[j].compareTo(array[i]) < 0) {
				Util.swap(array, menor--, j);
			}
			if(array[j].compareTo(array[i]) > 0) {
				Util.swap(array, maior++, j);
			}
			
		}
		return i;
	}

}
