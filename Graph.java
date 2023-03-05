import java.util.LinkedList;

public class Graph <E extends Comparable<E>>{
    int vertexCount = 0;
    int edgeCount = 0;
    RedBlackTree<Vertex<E>> vertecies = new RedBlackTree<Vertex<E>>();

    void addVertex(Vertex<E> vertex){
        vertecies.add(vertex);
        vertexCount++;
    }

    void addEdge(Edge<E> edge){
        edge.start.edges.add(edge);
        edge.end.edges.add(edge);
        edgeCount++;
    }
}

class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>>{
    E elem;
    LinkedList<Edge<E>> edges = new LinkedList<Edge<E>>();

    @Override
    public int compareTo(Vertex<E> o) {
        if(o == null) return 0;
        return elem.compareTo(o.elem);
    }

    LinkedList<Edge<E>> outgoing(){
        LinkedList<Edge<E>> outgoing = new LinkedList<>();

        for(Edge<E> edge : edges){
            if(edge.start == this) outgoing.add(edge);
        }

        return outgoing;
    }

}

class Edge<E extends Comparable<E>>{
    Vertex<E> start;
    Vertex<E> end;
}
