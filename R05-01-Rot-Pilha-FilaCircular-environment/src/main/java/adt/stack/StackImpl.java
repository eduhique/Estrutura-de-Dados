package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		this.array = (T[]) new Object[size];
		this.top = -1;
	}

	@Override
	public T top() {
		T result = null;
		if (!this.isEmpty()) {
			result = this.array[this.top];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		if (top == -1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		if (top == array.length - 1) {
			result = true;
		}
		return result;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		}else if (element != null) {
			this.array[++this.top] = element;			
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result;
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		result = this.array[top--];
		return result;
	}

}
