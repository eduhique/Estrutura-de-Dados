
package produto;

public class RepositorioProdutosArray<T extends Produto> implements RepositorioProduto<T> {

	private T[] produtos;

	private int index = -1;

	public RepositorioProdutosArray(int size) {
		super();
		this.produtos = (T[]) new Object[size];
	}

	private int procurarIndice(int codigo) {
		int retorno = -1;
		boolean teste = true;
		int i = 0;
		while (i <= index && teste) {
			if (produtos[i].getCodigo() == codigo) {
				retorno = i;
				teste = false;
			}
			i++;
		}
		return retorno;
	}

	public boolean existe(int codigo) {
		boolean retorno = false;
		if (procurarIndice(codigo) != -1) {
			retorno = true;
		}
		return retorno;
	}

	public void inserir(T produto) {
		produtos[++this.index] = produto;
	}

	public void atualizar(T produto) {
		int indiceProd = procurarIndice(produto.getCodigo());
		if (indiceProd == -1) {
			throw new RuntimeException("produto não cadastrado");
		} else {
			produtos[indiceProd] = produto;
		}
	}

	public void remover(int codigo) {
		int indiceRemv = procurarIndice(codigo);
		if (indiceRemv == -1) {
			throw new RuntimeException("produto não cadastrado");
		} else {
			produtos[indiceRemv] = produtos[index];
			produtos[index] = null;
			index--;
		}
	}

	public T procurar(int codigo) {
		T retorno = null;
		int indice = procurarIndice(codigo);
		if (indice == -1) {
			throw new RuntimeException("produto não cadastrado");
		} else {
			retorno = produtos[indice];
		}
		return retorno;
	}

}
