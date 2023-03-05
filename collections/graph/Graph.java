package collections.graph;

import collections.Heap;
import collections.RedBlackTree;


public class Graph <E extends Comparable<E>>{
    int vertexCount = 0;
    int edgeCount = 0;
    Heap<Edge<E>> edges = new Heap<Edge<E>>();
    RedBlackTree<Vertex<E>> vertecies = new RedBlackTree<Vertex<E>>();


    public void addVertex(E value){
        addVertex(new Vertex<E>(value));
    }

    public void addVertex(Vertex<E> vertex){
        vertecies.add(vertex);
        vertexCount++;
    }

    void addEdge(Edge<E> edge){
        edges.add(edge);
        edge.start.edges.add(edge);
        edge.end.edges.add(edge);
        edgeCount++;
    }

}




