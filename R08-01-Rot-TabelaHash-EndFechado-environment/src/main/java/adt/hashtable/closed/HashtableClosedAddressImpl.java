package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A table3 table with closed address works with a table3 function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size (or the given size, if it is already prime). 
    * For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number.
    * If the given number is prime, it is returned. 
    * You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {
      int primoResult = number;

      if (primoResult % 2 == 0) primoResult++;

      while (!Util.isPrime(primoResult)){
         primoResult += 2;
      }

      return primoResult;
   }

   @Override
   public void insert(T element) {
      if(element != null){
         int indiceHash = getHashFunction().hash(element);
         boolean verficaElmenet = isVerficaElmenet(element);

         if(!verficaElmenet){
            if(!getCelula(indiceHash).isEmpty()){
               COLLISIONS++;
            }

            getCelula(indiceHash).add(element);
            elements++;
         }
      }
   }


   @Override
   public void remove(T element) {
     if(element != null && !this.isEmpty()){

        int indiceHash = getHashFunction().hash(element);

        if(isVerficaElmenet(element)){
           getCelula(indiceHash).remove(element);
           elements--;
        }
     }
   }

   @Override
   public T search(T element) {
      T result = null;
      if (element != null) {
         int indice = indexElement(element);
         if (indice != -1) {
            int indiceHash = getHashFunction().hash(element);
            result = getCelula(indiceHash).get(indice);
         }
      }
      return result;
   }

   @Override
   public int indexOf(T element) {
      int indice = -1;
      if(element!= null && !this.isEmpty()){
         int hashCode = getHashFunction().hash(element);
         if(isVerficaElmenet(element)){
            indice = hashCode;
         }
      }
      return indice;
   }

   @Override
   public HashFunctionClosedAddress<T> getHashFunction() {
      return (HashFunctionClosedAddress<T>) super.getHashFunction();
   }

   private boolean isVerficaElmenet(T element) {
      boolean result = false;
      int indiceHash = getHashFunction().hash(element);

      for (T elem: getCelula(indiceHash)) {
         if (elem.hashCode() == element.hashCode()) {
            result = true;
            break;
         }
      }
      return result;
   }

   private LinkedList<T> getCelula(int indice) {
      if(super.table[indice] == null) super.table[indice] = new LinkedList<T>();
      return (LinkedList<T>) super.table[indice];
   }

   private int indexElement(T element){
      int indice = -1;
      if(element!= null && !this.isEmpty()){
         int indiceHash = getHashFunction().hash(element);
         int cont = 0;
         for (T elem: getCelula(indiceHash)) {
            if(elem.hashCode() == element.hashCode()){
               break;
            }
            cont++;
         }
         if (!(cont == getCelula(indiceHash).size())){
            indice = cont;
         }
      }
      return indice;
   }
}

