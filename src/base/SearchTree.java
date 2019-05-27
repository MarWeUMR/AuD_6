package base;

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

    public void insertion(T val) {

        Node node = new Node(val);

        Node y = null;
        Node x = this.treeRoot;

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

    public Node deletion(Node<T> node, T val) {
            // search the val
            if (node == null) return node;
            else if (val.compareTo(node.value) < 0) node.leftChild = deletion(node.leftChild, val);
            else if (val.compareTo(node.value) > 0) node.rightChild = deletion(node.rightChild, val);
            else {
                // the val has been found, now delete it

                // case 1: node is a leaf node
                if (node.leftChild == null && node.rightChild == null) {
                    node = null;
                }

                // case 2: node has only one child
                else if (node.leftChild == null) {
                    Node temp = node;
                    node = node.rightChild;
                }

                else if (node.rightChild == null) {
                    Node temp = node;
                    node = node.leftChild;
                }

                // case 3: has both children
                else {
                    //Node temp = minimum(node.rightChild);
                    //node.value = temp.value;
                    //node.rightChild = deletion(node.rightChild, temp.value);
                }

            }
            return node;
        }

    public void search() {

    }

    public void min() {

    }

    public void max() {

    }

    public void toSortedArrayList() {

    }


}
