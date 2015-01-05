package com.library.algorithms.graph;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Graph<T extends Comparable<T>> {
    private Map<T, Set<Edge<T>>> adjacentList;
    private final GraphType graphType;

    public enum GraphType {
        DIRECTED_GRAPH, UNDIRECTED_GRAPH;
    };

    public Graph(final GraphType graphType) {
        this.graphType = graphType;
        adjacentList = new ConcurrentHashMap<T, Set<Edge<T>>>();
    }

    private boolean hasVertex(final T vertex) {
        return vertex != null && adjacentList.containsKey(vertex);
    }

    private boolean hasEdge(final T from, final T to) {

        if (!hasVertex(from) || !hasVertex(to)) {
            return false;
        }

        final Set<Edge<T>> edges = adjacentList.get(from);
        for (final Edge<T> edge : edges) {
            if(edge.getEndVertex().equals(to)) {
                return true;
            }
        }
        return false;
    }

    private Iterable<Edge<T>> adjacentTo(final T v) {
        if (!adjacentList.containsKey(v)) {
            return new TreeSet<Edge<T>>();
        }
        return adjacentList.get(v);
    }

    public void addVertex(final T vertex) {
        if (!hasVertex(vertex)) {
            adjacentList.put(vertex, new TreeSet<Edge<T>>());
        }
    }

    public void addEdge(final T from, final T to) {
        this.addEdge(from, to, 0);
    }

    public void addEdge(final T from, final T to, final double weight) {

        if (hasEdge(from, to)) {
            return;
        }

        addVertex(from);
        addVertex(to);
        adjacentList.get(from).add(new Edge<T>(from, to, weight));

        if(graphType.equals(GraphType.UNDIRECTED_GRAPH)) {
            adjacentList.get(to).add(new Edge<T>(to, from, weight));
        }
    }

    public Set<Edge<T>> getPath(final T from, final T to) {
        Set<Edge<T>> edges = new LinkedHashSet<Edge<T>>();
        Set<T> visited = new LinkedHashSet<T>();
        Stack<Edge<T>> stack = new Stack<Edge<T>>();
        T vertex = from;

        do {
            Edge<T> edge = null;

            if(!stack.isEmpty()) {
                edge = stack.pop();
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

                for(final Edge<T> adjEdge : adjacentTo(vertex)) {
                    stack.push(adjEdge);
                }
            }
            else {
                edges.remove(edge);
            }
        } while(!stack.isEmpty());

        return null;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final T v : adjacentList.keySet()) {
            stringBuilder.append(v).append(": ");
            for (final Edge edge : adjacentList.get(v)) {
                stringBuilder.append(edge.getEndVertex()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Set<T> breadthFirstTraversal(final T vertex) {
        Set<T> visited = new LinkedHashSet<T>();
        Queue<T> queue = new LinkedList<T>();
        queue.add(vertex);

        while(!queue.isEmpty()){
            T v = queue.poll();
            visited.add(vertex);
            System.out.print(v + " ");
            for(final Edge<T> adjEdge : adjacentTo(v)){
                if(!visited.contains(adjEdge.getEndVertex())){
                    visited.add(adjEdge.getEndVertex());
                    queue.add(adjEdge.getEndVertex());
                }
            }
        }

        return visited;
    }

    public Set<T> depthFirstTraversal(final T vertex) {
        Set<T> visited = new LinkedHashSet<T>();
        Stack<T> s = new Stack<T>();
        s.push(vertex);

        while(!s.isEmpty()){
            T v = s.pop();

            if(!visited.contains(v)) {
                visited.add(v);
                System.out.print(v +  " ");

                for(final Edge<T> adjEdge : adjacentTo(v)){
                    s.push(adjEdge.getEndVertex());
                }
            }
        }
        return visited;
    }

    public void convertVertices(final Function<T> function) {
        for (final T element : adjacentList.keySet()) {
            function.apply(element);
        }
    }

    public void convertEdges(final Function<Edge<T>> function) {
        for (final Set<Edge<T>> edges : adjacentList.values()) {
            for (final Edge<T> element : edges) {
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

        System.out.print("\nBREATH FIRST SEARCH: ");
        G.breadthFirstTraversal("S");
        System.out.print("\nDEPTH FIRST SEARCH: ");
        G.depthFirstTraversal("S");

        String startVertexName = "D";
        String endVertexName = "G";
        System.out.print(String.format("\nPATH BETWEEN VERTICES: %s   %s \n", startVertexName, endVertexName));
        System.out.println(G.getPath(startVertexName, endVertexName));
    }
}

interface Function<Arg> {
    public void apply(Arg arg);
}
