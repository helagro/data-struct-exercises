package collections.graph;

public class Edge<E extends Comparable<E>> implements Comparable<Edge<E>>{
    public Vertex<E> start;
    public Vertex<E> end;
    public double weight;

    public Edge(Vertex<E> start, Vertex<E> end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge<E> o) {
        if(weight > o.weight) return 1;
        if(weight < o.weight) return -1;
        return 0;
    }
}