package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	protected T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T result = null;
		if (!this.isEmpty()) {
			result = array[0];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		if (tail == -1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		if (tail == array.length - 1) {
			result = true;
		}
		return result;
	}

	private void shiftLeft() {
		for (int i = 0; i < this.tail; i++) {
			array[i] = array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		if (element != null) {
			array[++tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T result = array[0];
		this.shiftLeft();
		tail--;
		return result;
	}

}
