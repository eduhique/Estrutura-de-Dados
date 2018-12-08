package adt.btree;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class StudentTestBTree {

    protected BTree<Integer> tree1;

    @Before
    public void setUp() throws Exception {
        tree1 = new BTreeImpl<Integer>(4);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(tree1.isEmpty());
    }

    @Test
    public void testHeight() {
        assertEquals(-1, tree1.height());
        tree1.insert(2);
        assertEquals(0, tree1.height());
    }

    @Test
    public void testDepthLeftOrder() {
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(6);
        tree1.insert(8);
        try {
            assertEquals("[[6], [2, 4], [8]]",
                    Arrays.toString(tree1.depthLeftOrder()));
        } catch (AssertionError e) {
            assertEquals("[[4], [2], [6, 8]]",
                    Arrays.toString(tree1.depthLeftOrder()));
        }

    }

    @Test
    public void testDepthLeftOrder2() {
        tree1.insert(1);

        assertEquals("[[1]]", Arrays.toString(tree1.depthLeftOrder()));
        assertEquals(1, tree1.size());
        tree1.insert(8);
        assertEquals("[[1, 8]]", Arrays.toString(tree1.depthLeftOrder()));
        assertEquals(2, tree1.size());
        tree1.insert(14);
        assertEquals(3, tree1.size());
        assertEquals("[[1, 8, 14]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(22);
        assertEquals(4, tree1.size());
        assertEquals("[[14], [1, 8], [22]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(26);
        assertEquals(5, tree1.size());
        assertEquals("[[14], [1, 8], [22, 26]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(30);
        assertEquals(6, tree1.size());
        assertEquals("[[14], [1, 8], [22, 26, 30]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(34);
        assertEquals(7, tree1.size());
        assertEquals("[[14, 30], [1, 8], [22, 26], [34]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(16);
        assertEquals(8, tree1.size());
        assertEquals("[[14, 30], [1, 8], [16, 22, 26], [34]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(20);
        assertEquals(9, tree1.size());
        assertEquals("[[14, 22, 30], [1, 8], [16, 20], [26], [34]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(18);
        assertEquals(10, tree1.size());
        assertEquals("[[14, 22, 30], [1, 8], [16, 18, 20], [26], [34]]", Arrays.toString(tree1.depthLeftOrder()));
        tree1.insert(19);
        printTree(tree1);
        //assertEquals(11, tree1.size());
        /*assertEquals(0, tree1.search(1).position);
        assertEquals(1, tree1.search(8).position);
        assertEquals(0, tree1.search(14).position);
        assertEquals(0, tree1.search(22).position);
        assertEquals(0, tree1.search(26).position);
        assertEquals(0, tree1.search(30).position);
        assertEquals(0, tree1.search(34).position);
        assertEquals(0, tree1.search(16).position);
        //assertEquals(0, tree1.search(20).position);
        assertEquals(1, tree1.search(18).position);
        assertEquals(1, tree1.search(19).position);
        assertEquals(0, tree1.search(81).position);
        assertEquals(2, tree1.height());*/
    }


    @Test
    public void testSize() {
        assertEquals(0, tree1.size());
        tree1.insert(18);
        assertEquals(1, tree1.size());
    }

    @Test
    public void testInsert2() {

        tree1.insert(13);
        tree1.insert(9);
        tree1.insert(5);

        assertEquals(tree1.getRoot().getElementAt(0), new Integer(5));
        assertEquals(tree1.getRoot().getElementAt(1), new Integer(9));
        assertEquals(tree1.getRoot().getElementAt(2), new Integer(13));

        assertEquals(tree1.size(), 3);
        assertEquals(tree1.height(), 0);

        tree1.insert(12);

        assertEquals(tree1.size(), 4);
        assertEquals(tree1.height(), 1);

        tree1.insert(122);
        tree1.insert(3);

        assertEquals(tree1.size(), 6);
        assertEquals(tree1.height(), 1);

        tree1.insert(76);

        tree1.insert(10);
        assertEquals(1, tree1.height());

        tree1.insert(7);
        assertEquals(1, tree1.height());
        tree1.insert(8);
        assertEquals(1, tree1.height());

        //printTree(tree1);
        tree1.insert(6);
        //printTree(tree1);

        tree1.insert(1000);
        assertEquals(2, tree1.height());

        for (int i = 0; i < 4332; i++) {
            tree1.insert(null);
        }

    }

    @Test
    public void testAdd() {
        BTreeImpl<Integer> arvere = new BTreeImpl<>(4);
        assertEquals(arvere.size(), 0);

        assertNull(arvere.search(1).node);
        arvere.insert(0);
        arvere.insert(1);
        assertNotNull(arvere.search(1).node);
        assertNull(arvere.search(-1).node);
        arvere.insert(-1);
        assertNotNull(arvere.search(-1).node);

        assertEquals(arvere.size(), 3);
        assertEquals(arvere.height(), 0);

        arvere.insert(2);
        assertEquals(arvere.height(), 1);
        arvere.insert(-2);
        assertEquals(arvere.size(), 5);
        assertEquals(arvere.height(), 1);
        assertNotNull(arvere.search(-2).node);

        arvere.insert(3);
        arvere.insert(-3);
        assertEquals(arvere.height(), 1);
        assertEquals(arvere.size(), 7);

        arvere.insert(4);
        assertEquals(arvere.height(), 1);
        arvere.insert(5);
        arvere.insert(6);
        assertEquals(arvere.height(), 1);
        arvere.insert(7);
        assertEquals(arvere.size(), 11);
        assertEquals(arvere.height(), 1);
        assertNotNull(arvere.search(7).node);
    }

    @Test
    public void testLuciano() {
        BTreeImpl<Integer> arvere = new BTreeImpl<>(4);

        arvere.insert(1);
        arvere.insert(4);
        arvere.insert(18);
        arvere.insert(3);
    }

    private void printTree(BTree<?> t) {
        BNode<?> node = t.getRoot();
        class Pair {
            BNode<?> node;
            int c;
            boolean hasLeft;

            Pair(BNode<?> node, int c, boolean hl) {
                this.node = node;
                this.c = -c;
                this.hasLeft = hl;
            }
        }
        ;
        LinkedList<Pair> lista = new LinkedList<>();
        HashSet<BNode<?>> visitados = new HashSet<>();

        lista.add(new Pair(node, 1, false));
        int ultimaCor = -1;
        while (!lista.isEmpty()) {
            Pair top = lista.removeFirst();
            if (top.c != ultimaCor)
                System.out.println();

            System.out.print(top.node + (top.hasLeft ? "=" : " "));

            for (BNode<?> b : top.node.getChildren()) {
                if (visitados.add(b))
                    lista.addLast(new Pair(b, top.c, !b.equals(top.node.getChildren().getLast())));
            }
            ultimaCor = top.c;
        }
        System.out.println();
    }

    @Test
    public void testSearch() {

        assertEquals(tree1.search(2).node, null);
        tree1.insert(2);
        assertEquals(0, tree1.search(2).position);
        tree1.insert(4);
        assertEquals(1, tree1.search(4).position);
        assertEquals(0, tree1.search(2).position);
        tree1.insert(1);
        assertEquals(0, tree1.search(1).position);
        assertEquals(1, tree1.search(2).position);
        tree1.insert(5);
        //System.out.println(Arrays.toString(tree1.depthLeftOrder()));
        assertEquals(0, tree1.search(5).position);
    }

}
