
public class RedBlackTree<E extends Comparable<E>>{
    Node<E> root;
    int size = 0;



    public E[] toArray() {
        final E[] array = (E[]) new Comparable[size];
        toArrayHelper(array, 0, root);
        return array;
    }

    private int toArrayHelper(E[] array, int size, Node<E> node){
        if(node == null) return size;
        size = toArrayHelper(array, size, node.leftChild);
        array[size] = node.value;
        size++;
        size = toArrayHelper(array, size, node.rightChild);
        return size;
    }


    public boolean add(E e) {
        root = addHelper(e, root);
        size ++;
        return true;
    }

    private Node<E> addHelper(E e, Node<E> node){
        if(node == null) return new Node<E>(e);
        Node<E> child = node.leftChild;
        if(child == null || node.rightChild.compareTo(node.leftChild) >= 0){
            child = node.rightChild;
        }

        return addHelper(e, child);
    }


    @Override
    public String toString() {
        E[] array = toArray();
        String res = "[ ";

        for(E elem : array){
            res += elem + ", ";
        }

        res += "]";
        return res;
    }
    
}

class Node<E extends Comparable<E>> implements Comparable<Node<E>>{
    Node<E> leftChild;
    Node<E> rightChild;
    E value;

    Node(E value){
        this.value = value;
    }

    @Override
    public int compareTo(Node<E> other) {
        if(other.value == null) return 0;
        return value.compareTo(other.value);
    }
}

class RedBlackTreeTester{
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
        tree.add(3);
        System.out.println(tree);
    }
}
