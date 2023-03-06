package collections.graph;

import java.util.LinkedList;

public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>>{
    public E value;
    public LinkedList<Edge<E>> edges = new LinkedList<Edge<E>>();

    public Vertex(E value) {
        this.value = value;
    }

    @Override
    public int compareTo(Vertex<E> o) {
        if(o == null) return 0;
        return value.compareTo(o.value);
    }

    public LinkedList<Edge<E>> outgoing(){
        LinkedList<Edge<E>> outgoing = new LinkedList<>();

        for(Edge<E> edge : edges){
            if(edge.start == this) outgoing.add(edge);
        }

        return outgoing;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}