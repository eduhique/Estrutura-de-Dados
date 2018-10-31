package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insert(T element) {
		if (element != null){
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), this.getLast());
			if(isEmpty()){
				this.setHead(newLast);
				this.setLast(newLast);
			} else{
				this.getLast().setNext(newLast);
				this.setLast(newLast);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null){
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
			if(isEmpty()){
				this.setHead(newHead);
				this.setLast(newHead);
			}else{
			DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) this.getHead();
			newHead.setNext(head);
			head.setPrevious(newHead);
			this.setHead(newHead);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()){
			DoubleLinkedListNode<T> elem = (DoubleLinkedListNode<T>) this.getHead().getNext();
			elem.setPrevious(new DoubleLinkedListNode<T>());
			this.setHead(elem);
		} else{
			this.setHead(new DoubleLinkedListNode<T>());
			this.setLast(new DoubleLinkedListNode<T>());
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.size() == 1) {
				this.setHead(new DoubleLinkedListNode<T>());
				this.setLast(new DoubleLinkedListNode<T>());
			} else {
				this.setLast(this.getLast().getPrevious());
				this.getLast().setNext(new DoubleLinkedListNode<T>());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast(){
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
