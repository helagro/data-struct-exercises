import java.util.Random;

public class Heap <E extends Comparable<E>>{
    static final int MIN_SIZE = 5;
    static final boolean IS_MAX_HEAP = false;
    static final double GROWTH_FACTOR = 1.5;
    static final double MIN_FILL_FACTOR = 0.3;
    
    private int size = 0;
    private E[] array = (E[]) new Comparable[MIN_SIZE];

    Heap(){}

    Heap(E[] array){
        size = array.length;
        this.array = array;

        for(int i = array.length/2 -1; i >= 0; i--){
            sink(i);
        }
    }


    int size(){
        return size;
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


    void add(E elem){
        if(size == array.length) grow();

        array[size] = elem;
        floatUp(size);
        size ++;
    }

    E getFirst(){
        return array[0];
    }

    E removeFirst(){
        if(size == 0) return null;
        if(size < array.length * MIN_FILL_FACTOR) shrink();
        
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

        if(child < size && isBefore(array[child].compareTo(array[node]))){
            swap(node, child);
            sink(child);
        }
    }



    private boolean isBefore(int comparison){
        return IS_MAX_HEAP ? comparison > 0 : comparison < 0;
    }

    private void swap(int j, int k){
        final E elemJ = array[j];
        array[j] = array[k];
        array[k] = elemJ;
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



    private void grow(){
        final int newSize = (int) Math.floor(array.length * GROWTH_FACTOR);
        resize(newSize);
    }

    private void shrink(){
        int newSize = (int) Math.floor(array.length / GROWTH_FACTOR);
        if(newSize < MIN_SIZE) newSize = MIN_SIZE;
        resize(newSize);
    }

    private void resize(int newSize){
        final E[] newArr = (E[]) new Comparable[newSize];
        System.arraycopy(array, 0, newArr, 0, size);

        array = newArr;
    }
}



class TestHeap{
    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Heap<Integer> heap = new Heap<>();

        final Random r = new Random();
        for(int i = 0; i < 20; i++){
            heap.add(r.nextInt(100));
        }
        System.out.println(heap);

        Integer elem;
        do{
            elem = heap.removeFirst();
            System.out.print(elem + ", ");
        } while(elem != null);
        System.out.println();

        System.out.println(heap);
    }

    public static void test2() {
        Integer[] arr = new Integer[20];

        final Random r = new Random();
        for(int i = 0; i < 20; i++){
            arr[i] = r.nextInt(100);
        }

        Heap<Integer> heap = new Heap<>(arr);

        System.out.println(heap);

        Integer elem;
        do{
            elem = heap.removeFirst();
            System.out.print(elem + ", ");
        } while(elem != null);
        System.out.println();
    }
}
