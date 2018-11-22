package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int maxHeight;

    protected double PROBABILITY = 0.5;

    public SkipListImpl(int maxHeight) {
        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
    }

    /**
     * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
     * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
     * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
     * metodo deve conectar apenas o forward[0].
     */
    private void connectRootToNil() {
        for (int i = 0; i < maxHeight; i++) {
            root.forward[i] = NIL;
        }
    }

    private int randomLevel() {
        int randomLevel = 1;
        double random = Math.random();
        while (Math.random() <= PROBABILITY && randomLevel < maxHeight) {
            randomLevel = randomLevel + 1;
        }
        return randomLevel;
    }

    @Override
    public void insert(int key, T newValue, int height) {
        SkipListNode<T>[] update = new SkipListNode[maxHeight];
        SkipListNode<T> nodeAux = this.root;

        for (int i = this.maxHeight - 1; i >= 0; i--) {
            while (nodeAux.getForward()[i].getKey() < key) {
                nodeAux = nodeAux.getForward()[i];
            }
            update[i] = nodeAux;
        }
        nodeAux = nodeAux.getForward()[0];

        if (nodeAux.getKey() == key) {
            if (nodeAux.height() == height) {
                nodeAux.setValue(newValue);
            } else {
                remove(key);
                insert(key, newValue, height);
            }
        } else {
            int newLevel = height;

            if (newLevel > this.maxHeight) {
                for (int i = this.maxHeight; i < newLevel; i++) {
                    update[i] = root;
                }

                this.maxHeight = newLevel;
            }

            nodeAux = new SkipListNode<T>(key, newLevel, newValue);
            for (int i = 0; i < newLevel; i++) {
                if (update[i].getForward(i) == null) {
                    nodeAux.getForward()[i] = NIL;
                } else {
                    nodeAux.getForward()[i] = update[i].getForward(i);
                }

                update[i].getForward()[i] = nodeAux;
            }
        }
    }

    @Override
    public void remove(int key) {
        SkipListNode<T>[] update = new SkipListNode[maxHeight];
        SkipListNode<T> nodeAux = this.root;

        for (int i = this.maxHeight - 1; i >= 0; i--) {
            while (nodeAux.getForward()[i].getKey() < key) {
                nodeAux = nodeAux.getForward()[i];
            }
            update[i] = nodeAux;
        }
        nodeAux = nodeAux.getForward()[0];

        if (nodeAux.getKey() == key) {
            for (int i = 0; i < maxHeight; i++) {
                if (!update[i].getForward(i).equals(nodeAux)) {
                    break;
                }
                update[i].getForward()[i] = nodeAux.getForward()[i];

            }
        }

    }

    @Override
    public int height() {
        int result;
        for (result = 0; result < maxHeight; result++) {
            if (root.forward[result] == NIL) {
                break;
            }
        }

        return result;
    }

    @Override
    public SkipListNode<T> search(int key) {
        SkipListNode<T> nodeAux = this.root;

        for (int i = this.maxHeight - 1; i >= 0; i--) {
            while (nodeAux.getForward()[i].getKey() < key) {
                nodeAux = nodeAux.getForward()[i];
            }
        }
        nodeAux = nodeAux.getForward()[0];

        if (nodeAux.getKey() != key) {
            nodeAux = null;
        }
        return nodeAux;
    }

    @Override
    public int size() {
        int size = 0;

        SkipListNode<T> node = this.root.getForward(0);
        while (!node.equals(NIL)) {
            size++;
            node = node.getForward(0);
        }

        return size;
    }

    @Override
    public SkipListNode<T>[] toArray() {
        SkipListNode<T>[] arrayResult = new SkipListNode[size() + 2];

        int i = 0;

        SkipListNode<T> node = this.root;
        while (node != null) {
            arrayResult[i++] = node;
            node = node.getForward(0);
        }

        return arrayResult;
    }

}
