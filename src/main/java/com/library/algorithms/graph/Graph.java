package com.emprovise.library.algorithms.graph;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Graph<T> {
    private Map<Vertex, Set<Edge<Vertex<T>>>> adjacentList;
    private Map<T, Vertex> vertices;
    private GraphType graphType;

    public Graph(GraphType graphType) {
        this.graphType = graphType;
        adjacentList = new ConcurrentHashMap<Vertex, Set<Edge<Vertex<T>>>>();
        vertices = new ConcurrentHashMap<T, Vertex>();
    }

    public Vertex addVertex(T label) {
        Vertex v;
        v = vertices.get(label);
        if (v == null) {
            v = new Vertex<T>(label);
            vertices.put(label, v);
            adjacentList.put(v, new HashSet<Edge<Vertex<T>>>());
        }
        return v;
    }

    public Vertex getVertex(T label) {
        return vertices.get(label);
    }

    public boolean hasVertex(T label) {
        return vertices.containsKey(label);
    }

    public boolean hasEdge(T from, T to) {
        if (!hasVertex(from) || !hasVertex(to))
            return false;

        Set<Edge<Vertex<T>>> edges = adjacentList.get(vertices.get(from));
        for (Edge<Vertex<T>> edge : edges) {
            if(edge.getEndVertex().equals(vertices.get(to))) {
                return true;
            }
        }
        return false;
    }

    public void addEdge(T from, T to) {
        this.addEdge(from, to, 0);
    }

    public void addEdge(T from, T to, double weight) {
        Vertex v, w;
        if (hasEdge(from, to))
            return;

        if ((v = getVertex(from)) == null)
            v = addVertex(from);
        if ((w = getVertex(to)) == null)
            w = addVertex(to);

        adjacentList.get(v).add(new Edge<Vertex<T>>(v, w, weight));

        if(graphType.equals(GraphType.UNDIRECTED_GRAPH)) {
            adjacentList.get(w).add(new Edge<Vertex<T>>(w, v, weight));
        }
    }

    public Iterable<Edge<Vertex<T>>> adjacentTo(T label) {
        if (!hasVertex(label))
            return new LinkedHashSet<Edge<Vertex<T>>>();
        return adjacentList.get(getVertex(label));
    }

    public Iterable<Edge<Vertex<T>>> adjacentTo(Vertex v) {
        if (!adjacentList.containsKey(v))
            return new LinkedHashSet<Edge<Vertex<T>>>();
        return adjacentList.get(v);
    }

    public Iterable<Vertex> getVertices() {
        return vertices.values();
    }

    public int numberOfVertices() {
        return vertices.size();
    }

    public int numberOfEdges() {
        int size = 0;
        for (Set<Edge<Vertex<T>>> edges : adjacentList.values()) {
            size += edges.size();
        }
        return size;
    }

    public Set<Edge<Vertex<T>>> getPath(T from, T to) {
        return getPath(new Vertex<T>(from), new Vertex<T>(to));
    }

    // List of edges between 2 vertices (path doesn’t have to be optimal)
    public Set<Edge<Vertex<T>>> getPath(Vertex<T> from, Vertex<T> to) {
        Set<Edge<Vertex<T>>> edges = new LinkedHashSet<Edge<Vertex<T>>>();
        Set<Vertex<T>> visited = new LinkedHashSet<Vertex<T>>();
        Stack<Edge<Vertex<T>>> stack = new Stack<Edge<Vertex<T>>>();
        Vertex<T> vertex = from;

        do {
            Edge<Vertex<T>> edge = null;

            if(!stack.isEmpty()) {
                edge = ((Edge<Vertex<T>>) stack.pop());
                vertex = edge.getEndVertex();
            }

            if(!visited.contains(vertex)) {
                visited.add(vertex);

                if(edge != null) {
                    edges.add(edge);
                }

                if(vertex.equals(to)) {
                    return edges;
                }

                for(Edge<Vertex<T>> adjEdge : adjacentTo(vertex)) {
                    stack.push(adjEdge);
                }
            }
            else {
                edges.remove(edge);
            }
        } while(!stack.isEmpty());

        return null;
    }

    /*
     * Returns adjacency-list representation of graph
     */
    public String toString() {
        String string = "";
        for (Vertex v : vertices.values()) {
            string += v + ": ";
            for (Edge edge : adjacentList.get(v)) {
                string += edge.getEndVertex() + " ";
            }
            string += "\n";
        }
        return string;
    }

    public Set<Vertex<T>> breadthFirstTraversal(Vertex<T> vertex) {
        Set<Vertex<T>> visited = new LinkedHashSet<Vertex<T>>();
        Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        queue.add(vertex);

        while(!queue.isEmpty()){
            Vertex<T> v = (Vertex<T>)queue.poll();
            visited.add(vertex);
            System.out.print(v.getLabel() + " ");
            for(Edge<Vertex<T>> adjEdge : adjacentTo(v)){
                if(!visited.contains(adjEdge.getEndVertex())){
                    visited.add(adjEdge.getEndVertex());
                    queue.add(adjEdge.getEndVertex());
                }
            }
        }

        return visited;
    }

    public Set<Vertex<T>> depthFirstTraversal(Vertex<T> vertex) {
        Set<Vertex<T>> visited = new LinkedHashSet<Vertex<T>>();
        Stack<Vertex<T>> s = new Stack<Vertex<T>>();
        s.push(vertex);

        while(!s.isEmpty()){
            Vertex<T> v = (Vertex<T>) s.pop();

            if(!visited.contains(v)) {
                visited.add(v);
                System.out.print(v.getLabel() +  " ");

                for(Edge<Vertex<T>> adjEdge : adjacentTo(v)){
                    s.push(adjEdge.getEndVertex());
                }
            }
        }
        return visited;
    }

    public void convertVertices(Function<Vertex<T>> function) {
        for (Vertex<T> element : getVertices()) {
            function.apply(element);
        }
    }

    public void convertEdges(Function<Edge<Vertex<T>>> function) {
        for (Set<Edge<Vertex<T>>> edges : adjacentList.values()) {
            for (Edge<Vertex<T>> element : edges) {
                function.apply(element);
            }
        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(GraphType.UNDIRECTED_GRAPH);
        G.addEdge("A", "B");
        G.addEdge("A", "S");
        G.addEdge("S", "C");
        G.addEdge("S", "G");
        G.addEdge("C", "D");
        G.addEdge("C", "E");
        G.addEdge("C", "F");
        G.addEdge("G", "F");
        G.addEdge("G", "H");
        G.addEdge("E", "H");

        Vertex vertex = G.getVertex("S");
        System.out.print("\nBREATH FIRST SEARCH: ");
        G.breadthFirstTraversal(vertex);
        System.out.print("\nDEPTH FIRST SEARCH: ");
        G.depthFirstTraversal(vertex);

        String startVertexName = "D";
        String endVertexName = "G";
        System.out.print(String.format("\nPATH BETWEEN VERTICES: %s   %s \n", startVertexName, endVertexName));
        System.out.println(G.getPath(startVertexName, endVertexName));
    }
}

enum GraphType {
    DIRECTED_GRAPH, UNDIRECTED_GRAPH;
};

interface Function<Arg> {
    public void apply(Arg arg);
}
