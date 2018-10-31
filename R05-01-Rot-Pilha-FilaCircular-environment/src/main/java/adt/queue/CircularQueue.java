package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		if (element != null) {
			if (this.head < 0) {
				head++;
			}
			if (tail == array.length - 1) {
				tail = 0;
			} else {
				tail++;
			}
			elements++;
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T result = array[head];
		if (head == array.length - 1) {
			head = 0;
		} else {
			head++;
		}
		elements--;
		return result;
	}

	@Override
	public T head() {
		T result = null;
		if (!this.isEmpty()) {
			result = array[head];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == array.length;
	}

}
