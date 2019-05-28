package base;

import java.util.ArrayList;

public class SearchTree<T extends Comparable<T>> {

    private Node treeRoot;


    public SearchTree() {
        treeRoot = null;
    }

    private class Node<T extends Comparable<T>> {

        private T value;
        private Node ancestor = null;
        private Node leftChild = null;
        private Node rightChild = null;

        public Node(T val) {
            value = val;
        }

        public void setLeft(Node n) {
            leftChild = n;
        }

        public void setRight(Node n) {
            rightChild = n;
        }
    }

    public void insertion(T val){
        insertion_recursive(val);
    }

    private void insertion_recursive(T val) {

        Node node = new Node(val);

        Node y = null;
        Node x = treeRoot;

        while (x != null) { // wenn elemente im tree
            y = x; // temporär root element sichern
            if (node.value.compareTo(x.value) < 0) {
                x = x.leftChild;
            } else {
                x = x.rightChild;
            }
        }

        node.ancestor = y; // ancestor auf ggf. gesicherten ancestor y setzen
        if (y == null) { // treeRoot setten, wenn noch keine Elemente vorhanden sind
            treeRoot = node;
        } else if (node.value.compareTo(y.value) < 0) {
            y.leftChild = node;
        } else {
            y.rightChild = node;
        }
    }

    public void deletion(T val) {
        treeRoot = deletion_recursive(treeRoot, val); // Ancestor backup
    }

    private Node deletion_recursive(Node<T> node, T val) {

        // Suche nach Wert
        if (node == null) return node;
        else if (val.compareTo(node.value) < 0) {
            node.leftChild = deletion_recursive(node.leftChild, val);
        } else if (val.compareTo(node.value) > 0) {
            node.rightChild = deletion_recursive(node.rightChild, val);
        } else { // Wert gefunden

            // blatt löschen
            if (node.leftChild == null && node.rightChild == null) {
                node = null;
            } else if (node.leftChild == null) {// knoten mit einem kind
                Node temp = node;
                node = node.rightChild;
            } else if (node.rightChild == null) {
                Node temp = node;
                node = node.leftChild;
            }

            // knoten mit zwei kindern
            else {
                Node<T> temp = min_recursive(node.rightChild);
                node.value = temp.value;
                node.rightChild = deletion_recursive(node.rightChild, temp.value);
            }
        }
        return node;
    }

    public boolean search(T val) {
        return search_recursive(treeRoot, val);
    }

    private boolean search_recursive(Node<T> node, T val) {
        if (node == null) return false;
        if (node.value.equals(val)) return true;
        if (val.compareTo(node.value) < 0) { // linkes kind wenn kleiner
            return search_recursive(node.leftChild, val);
        } else { // rechtes kind wenn größer
            return search_recursive(node.rightChild, val);
        }
    }

    public T min() {
        return (T) min_recursive(treeRoot).value;
    }

    public T max() {
        return (T) max_recursive(treeRoot).value;
    }

    private Node min_recursive(Node node) {
        return (node.leftChild != null) ? min_recursive(node.leftChild) : node;
    }

    private Node max_recursive(Node node) {
        return (node.rightChild != null) ? max_recursive(node.rightChild) : node;
    }

    public ArrayList<T> toSortedArrayList() {
        ArrayList<T> l = new ArrayList<>();
        return inorderTreeWalk(l, treeRoot);
    }

    private ArrayList<T> inorderTreeWalk(ArrayList<T> l, Node<T> node) {

        if (node != null) {
            inorderTreeWalk(l, node.leftChild);
            l.add(node.value);
            inorderTreeWalk(l, node.rightChild);
        }
        return l;
    }


}
