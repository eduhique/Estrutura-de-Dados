package produto;

public interface RepositorioProduto <T extends Produto>{
	public abstract boolean existe(int codigo);

	public abstract void inserir(T produto);

	public abstract void atualizar(T produto);

	public abstract void remover(int codigo);

	public abstract Produto procurar(int codigo);

}
