import java.util.NoSuchElementException;

public class MyQueue <E>{
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private static final double MIN_LOAD_FACTOR = 0.5;
    private static final int MIN_CAPACITY = 8;

    private E[] arr = (E[]) new Object[MIN_CAPACITY];
    private int front = 0;
    private int rear = -1;
    private int size = 0;


    public void enqueue(E elem){
        if(size +2 > arr.length) expand();
        size++;
        rear = (rear +1) % arr.length;

        arr[rear] = elem;
    }

    private void expand(){
        final int newCapacity = (int) (arr.length * CAPACITY_MULTIPLIER);
        final E[] newArr = (E[]) new Object[newCapacity];

        for(int i = 0; i < size; i++)
            newArr[i] = arr[(i + front) % arr.length];

        arr = newArr;
    }


    public E dequeue(){
        if(size == 0) throw new NoSuchElementException();
        if(size -1 < arr.length * MIN_LOAD_FACTOR) shrink();

        final E elem = arr[front];
        arr[front] = null;

        front = (front +1) % arr.length;
        size--;

        return elem;
    }

    private void shrink(){
        int newCapacity = (int) (arr.length / CAPACITY_MULTIPLIER);
        if(newCapacity < MIN_CAPACITY) newCapacity = MIN_CAPACITY;
        final E[] newArr = (E[]) new Object[newCapacity];

        for(int i = 0; i < size; i++)
            newArr[i] = arr[(i + front) % arr.length];

        arr = newArr;
    }


    public void print(){
        System.out.printf("{ ");
        for (final E elem : arr){
            String elemStr = elem == null ? "null" : elem.toString();
            System.out.printf("%s, ", elemStr);
        }
        System.out.printf("}\n");
    }


    public static void main(String[] args) { TestQueue.main(); }
}

class TestQueue{
    public static void main() {
        final MyQueue<Integer> queue = new MyQueue<Integer>();

        addItems(queue, 100);
        queue.print();

        rmItems(queue, 90);
        queue.print();
    }

    private static void addItems(MyQueue<Integer> queue, int amt){
        for (int i = 0; i<amt; i++){
            queue.enqueue(2);
        }
    }

    private static void rmItems(MyQueue<Integer> queue, int amt){
        for (int i = 0; i<amt; i++){
            queue.dequeue();
        }
    }
}
