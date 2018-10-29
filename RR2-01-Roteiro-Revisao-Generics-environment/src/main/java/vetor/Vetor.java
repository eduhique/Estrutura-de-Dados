package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T extends Comparable<T>> {

   // O array interno onde os objetos manipulados são guardados
   private T[] arrayInterno;

   // O tamanho que o array interno terá
   private int tamanho;

   // Indice que guarda a proxima posição vazia do array interno
   private int indice;

   // O Comparators a serem utilizados
   private Comparator<T> comparadorMaximo;
   private Comparator<T> comparadorMinimo;

   public Vetor(int tamanho) {
      super();
      this.tamanho = tamanho;
      this.indice = -1;
   }

   public void setComparadorMaximo(Comparator<T> comparadorMaximo) {
      this.comparadorMaximo = comparadorMaximo;
   }

   public void setComparadorMinimo(Comparator<T> comparadorMinimo) {
      this.comparadorMinimo = comparadorMinimo;
   }

   // Insere um objeto no vetor
   public void inserir(T o) {
      this.arrayInterno[++indice] = o;
   }

   // Remove um objeto do vetor
   public T remover(T o) {
      T retorno = null;
      boolean teste = true;
      int i = 0;
      while (1 <= indice && teste) {
         if (arrayInterno[i].equals(o)) {
            retorno = arrayInterno[i];
            arrayInterno[i] = arrayInterno[indice];
            arrayInterno[indice] = null;
            indice--;
            teste = true;
         }
         i++;
      }
      return retorno;

   }

   // Procura um elemento no vetor
   public T procurar(T o) {
      T retorno = null;
      boolean teste = true;
      int i = 0;
      while (1 <= indice && teste) {
         if (arrayInterno[i].equals(o)) {
            retorno = arrayInterno[i];
            teste = true;
         }
         i++;
      }
      return retorno;
   }

   // Diz se o vetor está vazio
   public boolean isVazio() {
      return this.indice == -1;
   }

   // Diz se o vetor está cheio
   public boolean isCheio() {
      boolean retorno = false;
      if (this.indice == this.tamanho - 1) {
         retorno = true;
      }
      return retorno;
   }

   public T maximo() {
      T retorno = arrayInterno[0];
      for (int i = 0; i < arrayInterno.length; i++) {
         if (!isVazio()) {
            if (comparadorMaximo.compare(retorno, arrayInterno[i]) > 0) {
               retorno = arrayInterno[i];
            }
         }
      }
      return retorno;

   }

   public T minimo() {
      T retorno = arrayInterno[0];
      for (int i = 0; i < arrayInterno.length; i++) {
         if (!isVazio()) {
            if (comparadorMinimo.compare(retorno, arrayInterno[i]) < 0) {
               retorno = arrayInterno[i];
            }
         }
      }
      return retorno;
   }
}

class ComparadorMaximo implements Comparator<Aluno> {

   @Override
   public int compare(Aluno o1, Aluno o2) {
      return (int) (o1.getMedia() - o2.getMedia());
   }

}

class ComparadorMinimo implements Comparator<Aluno> {

   @Override
   public int compare(Aluno o1, Aluno o2) {
      return (int) (o2.getMedia() - o1.getMedia());
   }

}
