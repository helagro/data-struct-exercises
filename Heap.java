import java.util.Random;

public class Heap <E extends Comparable<E>>{
    static final int MIN_SIZE = 15;
    static final boolean IS_MAX_HEAP = false;
    
    private int size = 0;
    private E[] array = (E[]) new Comparable[MIN_SIZE];


    int size(){
        return size;
    }

    void add(E elem){
        array[size] = elem;
        floatUp(size);
        size ++;
    }

    E getFirst(){
        return array[0];
    }

    E removeFirst(){
        if(size == 0) return null;
        
        size--;
        swap(0, size);
        sink(0);

        final E elem = array[size];
        array[size] = null;
        return elem;
    }

    private void floatUp(int node){
        if(node < 1) return; 
        final int parent = getParent(node);
        final int comparison = array[node].compareTo(array[parent]);
        final boolean isAbove = isBefore(comparison);
        
        if(isAbove){
            swap(node, parent);
            floatUp(parent);
        }
    }

    private void sink(int node){
        final int left = getLeft(node);
        int child = getRight(node);

        if(left >= size) return;
        if(child >= size || isBefore(array[left].compareTo(array[child]))) child = left;

        if(child < size && array[node].compareTo(array[child]) > 0){
            swap(node, child);
            sink(child);
        }
    }

    private int getParent(int node){
        return (int) (Math.ceil(node / 2d) - 1);
    }

    private int getLeft(int node){
        return 2* node +1; 
    }   
    
    private int getRight(int node){
        return 2* node +2; 
    }

    private boolean isBefore(int comparison){
        return IS_MAX_HEAP ? comparison > 0 : comparison < 0;
    }

    private void swap(int j, int k){
        final E elemJ = array[j];
        array[j] = array[k];
        array[k] = elemJ;
    }

    @Override
    public String toString() {
        String res = "[ ";

        for(E elem : array){
            res += elem + ", ";
        }

        res += "]";
        return res;
    }
}

class TestHeap{
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();

        final Random r = new Random();
        for(int i = 0; i < 8; i++){
            heap.add(r.nextInt(100));
        }
        System.out.println(heap);

        Integer elem;
        do{
            elem = heap.removeFirst();
            System.out.print(elem + ", ");
        } while(elem != null);
        System.out.println();

    }
}
