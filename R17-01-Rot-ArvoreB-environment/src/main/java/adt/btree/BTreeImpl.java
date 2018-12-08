package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

    protected BNode<T> root;
    protected int order;

    public BTreeImpl(int order) {
        this.order = order;
        this.root = new BNode<T>(order);
    }

    @Override
    public BNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    @Override
    public int height() {
        int result = -1;
        if (!this.isEmpty()) {
            result = height(this.root);
        }
        return result;
    }

    private int height(BNode<T> node) {
        int result = 0;
        if (node.isLeaf()) {
            result += 0;
        } else {
            result += 1 + height(node.getChildren().getFirst());
        }

        return result;
    }

    @Override
    public BNode<T>[] depthLeftOrder() {
        BNode<T>[] arrayResult = new BNode[this.sizeNodes(getRoot())];
        depthLeftOrder(arrayResult, 0, this.getRoot());
        return arrayResult;
    }

    private int depthLeftOrder(BNode<T>[] arrayResult, int index, BNode<T> node) {
        if (!node.isEmpty()) {
            arrayResult[index++] = node;
            for (int i = 0; i < node.getChildren().size(); i++) {
                index = depthLeftOrder(arrayResult, index, node.getChildren().get(i));

            }
        }
        return index;
    }

    private int sizeNodes(BNode<T> node) {
        int result = 0;
        if (!node.isEmpty()) {
            result += 1;

            for (int i = 0; i < node.getChildren().size(); i++) {
                result += sizeNodes(node.getChildren().get(i));
            }
        }
        return result;
    }

    @Override
    public int size() {
        return this.size(getRoot());
    }

    private int size(BNode<T> node) {
        int result = 0;
        if (!node.isEmpty()) {
            result = node.size();
            for (int i = 0; i < node.getChildren().size(); i++) {
                result += size(node.getChildren().get(i));
            }
        }

        return result;
    }

    @Override
    public BNodePosition<T> search(T element) {
        BNodePosition<T> result = new BNodePosition<T>();
        if (element != null) {
            result = search(element, this.getRoot());
        }
        return result;
    }

    private BNodePosition<T> search(T element, BNode<T> node) {
        int index = 0;
        BNodePosition<T> result = new BNodePosition<T>();
        while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
            index++;
        }

        if (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) == 0) {
            result = new BNodePosition<>(node, index);
        } else if (!node.isLeaf()) {
            result = search(element, node.getChildren().get(index));
        }
        return result;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            insert(element, this.getRoot());
        }

    }

    private void insert(T element, BNode<T> node) {
        int index = 0;
        while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
            index++;
        }
        if (node.isLeaf()) {
            node.addElement(element);
            split(node);
        } else {
            BNode<T> aux = node.getChildren().get(index);
            insert(element, aux);
        }
    }


    private void split(BNode<T> node) {
        while (node.size() > node.getMaxKeys()) {
            node = split2(node);
            if (node.getParent() == null) {
                root = node;
            }
        }
    }

    private BNode<T> split2(BNode<T> node) {
        int size = node.size();
        int median = size / 2;
        BNode<T> left = addChildrensWChild(node, 0, median);
        BNode<T> right = addChildrensWChild(node, median, size);
        BNode<T> parent = node.getParent();

        if (parent == null) {
            parent = new BNode<T>(order);
            parent.getChildren().add(node);
        }

        int i = parent.indexOfChild(node);
        parent.removeChild(node);

        left.setParent(parent);
        right.setParent(parent);
        parent.addChild(i, left);
        parent.addChild(i + 1, right);
        promote(right);
        return parent;
    }

    private BNode<T> addChildrens(BNode<T> node, int init, int end) {
        BNode<T> result = new BNode<>(order);

        for (int i = init; i != end; i++) {
            result.addElement(node.getElementAt(i));
        }
        int size = node.getChildren().size();
        for (int i = init; i != init + Math.min(end, size); i++) {
            if (i < node.getChildren().size()) result.addChild(i - init, node.getChildren().get(i));
        }

        return result;
    }

    private BNode<T> addChildrensWChild(BNode<T> node, int init, int end) {
        BNode<T> result = new BNode<>(order);

        for (int i = init; i != end; i++) {
            result.addElement(node.getElementAt(i));
        }
        return result;
    }

    private void promote(BNode<T> node) {
        T element = node.getElements().removeFirst();
        node.getParent().addElement(element);
        int size = node.size();
        int median = size / 2;
        BNode<T> left = addChildrensWChild(node, 0, median);
        BNode<T> right = addChildrensWChild(node, median, size);
        BNode<T> parent = node.getParent();
        if (parent == null) {
            parent = new BNode<T>(order);
            parent.getChildren().add(node);
        }

        int size2 = node.getChildren().size();
        for (int i = 0; i != size2; i++) {
            if(i <= median && !left.isEmpty()){
                left.addChild(i, node.getChildren().get(i));
            }else if(i <= median && left.isEmpty()){
                right.addChild(i, node.getChildren().get(i));
            }else{
                right.addChild(i, node.getChildren().get(i));
            }
        }

        int i = parent.indexOfChild(node);
        parent.removeChild(node);
        if (!left.isEmpty()) {
            left.setParent(parent);
            parent.addChild(i, left);
            right.setParent(parent);
            parent.addChild(i + 1, right);
        }else {
            right.setParent(parent);
            parent.addChild(i, right);
        }

    }

    // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
    @Override
    public BNode<T> maximum(BNode<T> node) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

    @Override
    public BNode<T> minimum(BNode<T> node) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

    @Override
    public void remove(T element) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

}
