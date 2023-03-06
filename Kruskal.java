import java.util.HashMap;
import java.util.Stack;

import collections.graph.*;

class Kruskal{
    public static void run(Graph<String> g){
        HashMap<Vertex<?>, Vertex<?>> vertexMap = new HashMap<Vertex<?>, Vertex<?>>();

        for(Comparable elem : g.vertecies.toArray()){
            Vertex<String> v = (Vertex<String>) elem;
            if(v == null) continue;
            v.edges.clear();
            vertexMap.put(v, null);
        }


        Stack<Edge<String>> minEdges = new Stack<>();
        Edge<String> edge = g.edges.removeFirst();
        while(edge != null){
            final Vertex<?> endAncestor = getAncestor(vertexMap, edge.end);
            if(getAncestor(vertexMap, edge.start) != endAncestor){
                minEdges.push(edge);
                vertexMap.put(endAncestor, edge.start);
            }

            edge = g.edges.removeFirst();
        }

        for(Edge<String> edge1 : minEdges){
            g.addEdge(edge1);
            System.out.println(edge1.start + " " + edge1.end + " " + edge1.weight);
        }   
    }

    private static Vertex<?> getAncestor(HashMap<Vertex<?>, Vertex<?>> vertexMap, Vertex<?> v){
        final Vertex<?> parent = vertexMap.get(v);
        if(parent == null) return v;
        return getAncestor(vertexMap, parent);
    }
    

    public static void main(String[] args) {
        Graph<String> g = new Graph<>();

        Vertex<String> a = new Vertex<String>("a");
        Vertex<String> b = new Vertex<String>("b");
        Vertex<String> c = new Vertex<String>("c");
        Vertex<String> d = new Vertex<String>("d");
        Vertex<String> e = new Vertex<String>("e");
        Vertex<String> f = new Vertex<String>("f");

        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);
        g.addVertex(e);
        g.addVertex(f);
        
        Edge<String> ac = new Edge<>(a, c, 7);
        Edge<String> ae = new Edge<>(a, e, 9);
        Edge<String> cb = new Edge<>(c, b, 5);
        Edge<String> cd = new Edge<>(c, d, 1);
        Edge<String> cf = new Edge<>(c, f, 2);
        Edge<String> df = new Edge<>(d, f, 2);
        Edge<String> ef = new Edge<>(e, f, 1);
        Edge<String> fb = new Edge<>(f, b, 6);

        g.addEdge(ac);
        g.addEdge(ae);
        g.addEdge(cb);
        g.addEdge(cd);
        g.addEdge(cf);
        g.addEdge(df);
        g.addEdge(ef);
        g.addEdge(fb);

        run(g);
    }
}