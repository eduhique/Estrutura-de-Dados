package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected RecursiveDoubleLinkedListImpl<T> previous;

   public RecursiveDoubleLinkedListImpl() {

   }

   public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
         RecursiveDoubleLinkedListImpl<T> previous) {
      super(data, next);
      this.previous = previous;
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         if (this.isEmpty()) {
            this.setPrevious(this.getPrevious());
            this.setData(element);
            RecursiveDoubleLinkedListImpl<T> nill = new RecursiveDoubleLinkedListImpl<T>(null, new RecursiveDoubleLinkedListImpl<>(), this);
            this.setNext(nill);
         } else {
            this.next.insert(element);
         }
      }
   }

   @Override
   public void remove(T element) {
      if (isEmpty() || element == null) {

      } else {
         if (getData().equals(element)) {
            if (this.getPrevious() != null && getPrevious().isEmpty() && getNext().isEmpty()) {
               this.setData(this.getNext().getData());
               this.getPrevious().setData(this.getNext().getData());
            } else {
               this.setData(this.getNext().getData());
               this.setNext(this.getNext().getNext());
               if (!this.getNext().isEmpty()) {
                  ((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
               }
            }
         } else {
            this.getNext().remove(element);
         }
      }
   }

   @Override
   public void insertFirst(T element) {
      if (element != null) {
         RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>(this.getData(), this.getNext(),
               this);
         this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
         this.setData(element);
         this.setNext(newNode);
      }
   }

   @Override
   public void removeFirst() {
      if (!isEmpty()) {
         RecursiveDoubleLinkedListImpl newThis = ((RecursiveDoubleLinkedListImpl) this.getNext());
         this.setData((T) newThis.getData());
         this.setNext(newThis.getNext());
         ((RecursiveDoubleLinkedListImpl) newThis.getNext()).setPrevious(this);
      }

   }

   @Override
   public void removeLast() {
      if(!this.isEmpty()) {
         if (this.getNext().isEmpty()) {
            this.setData(null);
         } else {
            ((RecursiveDoubleLinkedListImpl) this.getNext()).removeLast();
         }
      }
   }

   public RecursiveDoubleLinkedListImpl<T> getPrevious() {
      return previous;
   }

   public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
      this.previous = previous;
   }

}
