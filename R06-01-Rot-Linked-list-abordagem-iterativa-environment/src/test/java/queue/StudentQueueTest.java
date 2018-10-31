package queue;


import adt.queue.QueueDoubleLinkedListImpl;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentQueueTest {

	public QueueDoubleLinkedListImpl<Integer> queue1;
	public QueueDoubleLinkedListImpl<Integer> queue2;
	public QueueDoubleLinkedListImpl<Integer> queue3;
	public QueueDoubleLinkedListImpl<Integer> cq;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueDoubleLinkedListImpl<>(4);
		queue2 = new QueueDoubleLinkedListImpl<>(2);
		queue3 = new QueueDoubleLinkedListImpl<>(4);
		cq = new QueueDoubleLinkedListImpl<>(5);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(new Integer(1), queue1.head());
	}

	@Test
	public void testHead2() {
		assertEquals(null, queue3.head());
	}

	@Test
	public void testHead3() throws QueueUnderflowException {
		queue1.dequeue();
		assertEquals(new Integer(2), queue1.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
	}
	
	@Test
	public void testIsEmpty2() throws QueueOverflowException, QueueUnderflowException {
		cq.enqueue(new Integer(1));
		cq.enqueue(new Integer(2));
		cq.enqueue(new Integer(3));
		cq.enqueue(new Integer(4));
		cq.enqueue(null);
		cq.enqueue(new Integer(5));
		cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		assertNull(cq.head() );
		assertTrue(cq.isEmpty());
		
	}
	
	@Test
	public void testeFinal() throws QueueOverflowException, QueueUnderflowException {
		cq.enqueue(new Integer(1));
		cq.enqueue(new Integer(2));
		cq.enqueue(new Integer(3));
		cq.enqueue(new Integer(4));
		cq.enqueue(null);
		cq.enqueue(new Integer(5));
		cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		cq.dequeue();
		assertNull(cq.head() );
		assertTrue(cq.isEmpty());
		cq.enqueue(new Integer(9));
		cq.enqueue(new Integer(1));
		cq.enqueue(new Integer(2));
		cq.enqueue(new Integer(3));
		cq.enqueue(new Integer(4));
		assertEquals(new Integer(9), cq.head());
		assertFalse(cq.isEmpty());
		assertTrue(cq.isFull());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
	}

	@Test
	public void testIsFull2() {
		assertTrue(queue2.isFull());
	}

	@Test
	public void testIsFull3() throws QueueOverflowException {
		assertFalse(queue1.isFull());
		queue1.enqueue(3);
		assertTrue(queue1.isFull());

	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testEnqueue2() throws QueueOverflowException {
		cq.enqueue(new Integer(1));
		cq.enqueue(new Integer(2));
		cq.enqueue(new Integer(3));
		cq.enqueue(new Integer(4));
		cq.enqueue(null);
		cq.enqueue(new Integer(5));
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDequeue2() throws QueueUnderflowException, QueueOverflowException {
		cq.enqueue(new Integer(1));
		cq.enqueue(new Integer(2));
		cq.enqueue(new Integer(3));
		cq.enqueue(new Integer(4));
		cq.enqueue(null);
		cq.enqueue(new Integer(5));
		Integer x = cq.dequeue();
		assertEquals(new Integer(1), x);
		cq.enqueue(new Integer(6));
		Integer y = cq.dequeue();
		Integer z = cq.dequeue();
		assertEquals(new Integer(2), y);
		assertEquals(new Integer(3), z);
		cq.enqueue(new Integer(7));
		cq.enqueue(null);
		Integer a = cq.head();
		assertEquals(new Integer(4), a);
		assertFalse(cq.isEmpty());
		assertFalse(cq.isFull());
		cq.enqueue(new Integer(8));
		assertTrue(cq.isFull());
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		assertEquals(new Integer(1), queue3.dequeue()); // vai depender do
														// tamanho que a fial
														// foi iniciada!!!
	}
}