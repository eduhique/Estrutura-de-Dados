package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
        boolean result = false;
		if(this.data == null){
			result = true;
		}
		return  result;
	}

	@Override
	public int size() {
	    int result = 0;
		if(!this.isEmpty()){
		    result += 1 + this.getNext().size();
        }
        return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
            if (this.isEmpty() || this.getData() == null) {

            } else {
                if (getData().equals(element)) {
                    result = getData();
                } else {
                    result = this.getNext().search(element);
                }
            }
        }
        return result;
	}

	@Override
	public void insert(T element) {
        if (element != null) {
            if (this.isEmpty()) {
                this.data = element;
                this.next = new RecursiveSingleLinkedListImpl<>();
            } else {
                this.next.insert(element);
            }
        }
	}

	@Override
	public void remove(T element) {
        if (element != null) {
            if (isEmpty()) {

            } else {
                if (getData().equals(element)) {
                    this.setData(this.getNext().getData());
                    this.setNext(this.getNext().getNext());
                } else {
                    this.getNext().remove(element);
                }
            }
        }
	}

	@Override
	public T[] toArray() {
        List<T> saida = new ArrayList<T>();
        toArrayAux(saida, this);
        return (T[]) saida.toArray();
	}

    private void toArrayAux(List<T> saida, RecursiveSingleLinkedListImpl<T> node) {
	    if (!node.isEmpty()){
	        saida.add(node.getData());
	        this.toArrayAux(saida, node.getNext());
        }
    }

    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
