package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTestHashtableOpenAddressQuadraticProbing {
    protected final int PROPOSED_SIZE = 10;
    protected AbstractHashtableOpenAddress<HashtableElement> table1;
    protected AbstractHashtableOpenAddress<HashtableElement> table2;

    @Before
    public void setUp() throws Exception {
        table1 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
                PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
        // o tamanho real utilizado vai ser PROPOSED_SIZE
        table1.insert(new HashtableElement(10)); // coloca no slot indexado com
        // 0
        table1.insert(new HashtableElement(15)); // coloca no slot indexado com
        // 5
        table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
        table1.insert(new HashtableElement(12)); // coloca no slot indexado com
        // 8, teve 2 colisoes
        table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
        table1.insert(new HashtableElement(8)); // coloca no slot indexado com
        // 6, teve 1 colisao

        table2 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
                PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
    }

    @Test
    public void testInsert() {
        assertEquals(3, table1.getCOLLISIONS());
        table1.insert(new HashtableElement(11)); // nao tem colisao. coloca no
        // slot indexado com 1
        assertEquals(3, table1.getCOLLISIONS());
        assertEquals(1, table1.indexOf(new HashtableElement(11)));

        table1.insert(new HashtableElement(21)); // tem 1 colisao. coloca no
        // slot indexado com 9
        assertEquals(4, table1.getCOLLISIONS());
        assertEquals(9, table1.indexOf(new HashtableElement(21)));
        table1.remove(new HashtableElement(11));
        assertNull(table1.search(new HashtableElement(11)));
        table1.insert(new HashtableElement(11)); // nao tem colisao. coloca no
        // slot indexado com 1
        table1.insert(new HashtableElement(11));
        assertEquals(new HashtableElement(11), table1.search(new HashtableElement(11)));


    }

    @Test(expected = HashtableOverflowException.class)
    public void testInsertError() {
        table1.insert(new HashtableElement(1)); // enche a tabela
        table1.insert(new HashtableElement(23));
        table1.insert(new HashtableElement(37));
        table1.insert(new HashtableElement(49));
        table1.insert(new HashtableElement(48));
    }

    @Test
    public void testRemove() {
        table1.remove(new HashtableElement(17)); // elemento inexistente
        assertEquals(6, table1.size());

        table1.remove(new HashtableElement(12)); // elemento existente
        assertEquals(5, table1.size());
        assertNull(table1.search(new HashtableElement(12)));
        assertEquals(5, table1.size());
        table1.remove(new HashtableElement(10));
        table1.remove(new HashtableElement(15)); // esvazia
        table1.remove(new HashtableElement(2));
        table1.remove(new HashtableElement(12));
        table1.remove(new HashtableElement(4));
        table1.remove(new HashtableElement(8));
        assertEquals(0, table1.size());
        assertTrue(table1.isEmpty());
    }

    @Test
    public void testSearch() {
        assertEquals(new HashtableElement(4),
                table1.search(new HashtableElement(4))); // elemento que existe
        assertNull(table1.search(new HashtableElement(14))); // elemento que nao
        // existe
        table1.remove(new HashtableElement(10));
        table1.remove(new HashtableElement(15)); // esvazia
        table1.remove(new HashtableElement(2));
        table1.remove(new HashtableElement(12));
        table1.remove(new HashtableElement(4));
        assertEquals(new HashtableElement(8),
                table1.search(new HashtableElement(8)));

    }

    @Test
    public void testIsEmpty() {
        assertFalse(table1.isEmpty());
        table1.remove(new HashtableElement(15)); // esvazia
        table1.remove(new HashtableElement(8));
        table1.remove(new HashtableElement(12));
        table1.remove(new HashtableElement(2));
        table1.remove(new HashtableElement(10));
        table1.remove(new HashtableElement(4));
        assertTrue(table1.isEmpty());

        assertTrue(table2.isEmpty());
        table1.insert(new HashtableElement(15));
        assertFalse(table1.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(table1.isFull());
        table1.insert(new HashtableElement(1)); // enche a tabela
        table1.insert(new HashtableElement(23));
        table1.insert(new HashtableElement(37));
        table1.insert(new HashtableElement(49));
        assertTrue(table1.isFull());

        assertFalse(table2.isFull());
        table1.remove(new HashtableElement(1));
        assertFalse(table1.isFull());
    }

    @Test
    public void testSize() {
        assertEquals(6, table1.size());
        table1.insert(new HashtableElement(23));
        assertEquals(7, table1.size());
    }

}
