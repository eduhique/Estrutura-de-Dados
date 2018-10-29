package recursao;

public class MetodosRecursivos {

	public static void main(String[] args) {

		MetodosRecursivos m = new MetodosRecursivos();
		System.out.println("calcula fartorial: " + System.lineSeparator());
		m.calcularFatorial(12);

		System.out.println(System.lineSeparator() + "calcula fibonacci: " + System.lineSeparator());
		m.calcularFibonacci(12);

		System.out.println(System.lineSeparator() + "count not null: " + System.lineSeparator());
		Object[] s = { 1, 2, null, null, 3 };
		System.out.println(m.countNotNull(s));

		System.out.println(System.lineSeparator() + "potencia de 2: " + System.lineSeparator());
		System.out.println(m.potenciaDe2(4));
		
		System.out.println(System.lineSeparator() + "PA: " + System.lineSeparator()+ m.progressaoAritmetica(1, 0.5, 10));

		
		System.out.println(System.lineSeparator() + "PG: " + System.lineSeparator()+ m.progressaoGeometrica(1, 0.5, 1000));


	}

	public int calcularSomaArray(int[] array) {
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		return result;
	}

	public long calcularFatorial(int n) {
		long result = 1;
		if (n == 0) {
			System.out.println(n + "!= " + result);
		} else {
			result = n * calcularFatorial(n - 1);
			System.out.println(n + "!= " + result);
		}
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		if (n == 1 || n == 2) {
			//
		} else {
			result = calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
		}
		System.out.println(result);
		return result;
	}

	public int countNotNull(Object[] array) {
		return countNotNull(array, 0);
	}

	public int countNotNull(Object[] array, int index) {
		int result = 0;
		if (array[index] != null) {
			result++;
		}
		if (index != array.length - 1) {
			result += countNotNull(array, index + 1);
		}
		return result;
	}

	public long potenciaDe2(int expoente) {
		long result = 1;
		if (expoente == 0) {

		} else {
			result = 2 * potenciaDe2(expoente - 1);
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if (n == 1) {
			//
		} else {
			result = progressaoAritmetica(termoInicial, razao, n - 1) + razao;
		}
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if (n == 1) {
			//
		} else {
			result = progressaoGeometrica(termoInicial, razao, n - 1) * razao;
		}
		return result;
	}

}
