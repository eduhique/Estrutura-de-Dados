package produto;

import java.util.ArrayList;

/**
 * Classe que representa um repositório de produtos usando ArrayList como
 * estrutura sobrejacente. Alguns métodos (atualizar, remover e procurar) ou
 * executam com sucesso ou retornam um erro. Para o caso desde exercício, o erro
 * será representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 *
 * @author Adalberto
 */
public class RepositorioProdutoArrayList implements RepositorioProduto<Produto>{

	/**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar por
	 * enquanto com o uso de generics em ArrayList.
	 */
	private ArrayList<Produto> produtos;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList<Produto>();
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou -1
	 * caso ele nao se encontre no array. Esse método é util apenas na implementacao
	 * com arrays por questoes de localizacao. Outras classes que utilizam outras
	 * estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int retorno = -1;
		for (int i = 0; i < produtos.size(); i++) {
			if ((((Produto) produtos.get(i)).getCodigo()) == codigo) {
				retorno = i;
			}
		}
		return retorno;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		boolean retorno = false;
		if (procurarIndice(codigo) != -1) {
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(Produto produto) {
		this.produtos.add(produto);
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao esteja
	 * no array. Note que, para localizacao, o código do produto será utilizado.
	 */
	public void atualizar(Produto produto) {
		if(!produtos.contains(produto)) {
			throw new RuntimeException("produto não cadastrado");
		} else {
			produtos.remove(produto);
			produtos.add(produto);
		}
		
		
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um erro,
	 * caso contrário. Note que a remoção NÃO pode deixar "buracos" no array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		if(!existe(codigo)) {
			throw new RuntimeException("produto não cadastrado");
		}
		produtos.remove(procurarIndice(codigo));		
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o produto
	 * nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		if(!existe(codigo)) {
			throw new RuntimeException("produto não cadastrado");
		}
		return (Produto) produtos.get(procurarIndice(codigo));
	}
}
