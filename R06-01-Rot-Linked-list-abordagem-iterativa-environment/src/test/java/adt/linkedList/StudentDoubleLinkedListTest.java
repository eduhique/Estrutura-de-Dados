package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListImpl<>();
		lista2 = new DoubleLinkedListImpl<>();
		lista3 = new DoubleLinkedListImpl<>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}
	@Test
	public void testInsertFirst2() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		((DoubleLinkedList<Integer>) lista1).insertFirst(5);
		Assert.assertArrayEquals(new Integer[] { 5, 4, 3, 2, 1 }, lista1.toArray());
	}
	@Test
	public void testInsertFirst3() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(4);
		((DoubleLinkedList<Integer>) lista2).insertFirst(5);
		Assert.assertArrayEquals(new Integer[] { 5, 4 }, lista2.toArray());
		lista2.insert(3);
		Assert.assertArrayEquals(new Integer[] { 5, 4, 3 }, lista2.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst2() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast3() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		((DoubleLinkedList<Integer>) lista1).removeLast();
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista1.toArray());
	}
	@Test
	public void testRemoveLast2() {
		(lista3).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}
}