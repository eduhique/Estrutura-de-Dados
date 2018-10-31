package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

    protected SingleLinkedListNode<T> head;

    public SingleLinkedListImpl() {
        this.head = new SingleLinkedListNode<T>();
    }

    @Override
    public boolean isEmpty() {
        return this.getHead().isNIL();
    }

    @Override
    public int size() {
        int size = 0;
        if (!this.isEmpty()) {
            SingleLinkedListNode<T> aux = getHead();
            while (!aux.isNIL()) {
                size++;
                aux = aux.getNext();
            }
        }
        return size;
    }

    @Override
    public T search(T element) {
        T result = null;

        if (element != null && !this.isEmpty()) {
            SingleLinkedListNode<T> aux = getHead();
            while (!aux.isNIL() && result == null) {
                if (aux.getData().equals(element)) {
                    result = aux.getData();
                }
                aux = aux.getNext();
            }
        }
        return result;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            if (this.isEmpty()) {
                SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
                this.setHead(newHead);
            } else {
                SingleLinkedListNode<T> auxHead = getHead();
                while (!auxHead.isNIL()) {
                    auxHead = auxHead.getNext();
                }
                auxHead.setData(element);
                auxHead.setNext(new SingleLinkedListNode<T>());
            }
        }
    }


    @Override
    public void remove(T element) {
        if (!this.isEmpty() && element != null) {

            if (this.getHead().getData().equals(element)) {
                this.setHead(this.getHead().next);
            } else {
                SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
                SingleLinkedListNode<T> aux = this.getHead();

                while (!aux.isNIL() && !aux.getData().equals(element)) {
                    previous = aux;
                    aux = aux.getNext();
                }
                if (!aux.isNIL()) {
                    previous.next = aux.next;
                }
            }
        }
    }

    @Override
    public T[] toArray() {
        SingleLinkedListNode<T> aux = this.getHead();
        List<T> listAux = new ArrayList<T>();
        while (!aux.isNIL()) {
            listAux.add(aux.getData());
            aux = aux.getNext();
        }
        T[] result = (T[]) listAux.toArray();
        return result;
    }

    public SingleLinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(SingleLinkedListNode<T> head) {
        this.head = head;
    }

    public int indexOf(T element) {
        int result = -1;
        if (element != null && !this.isEmpty()) {
            SingleLinkedListNode<T> aux = getHead();
            int i = 0;
            while (!aux.isNIL()) {
                if (element.equals(aux.getData())) {
                    result = i;
                    break;
                }
                i++;
                aux = aux.getNext();
            }
        }
        return result;
    }

    public int lastIndexOf(T element) {
        int result = -1;
        if (element != null && !this.isEmpty()) {
            SingleLinkedListNode<T> aux = getHead();
            int i = 0;
            while (!aux.isNIL()) {
                if (element.equals(aux.getData())) {
                    result = i;
                }
                i++;
                aux = aux.getNext();
            }
        }
        return result;
    }
}
