package com.library.algorithms.graph;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Graph<T extends Comparable<T>> {

    private final Map<T, Set<Edge<T>>> adjacentList;
    private final GraphType graphType;

    public enum GraphType {
        DIRECTED_GRAPH, UNDIRECTED_GRAPH;
    };

    public Graph(final GraphType graphType) {
        this.graphType = graphType;
        adjacentList = new ConcurrentHashMap<T, Set<Edge<T>>>();
    }

    private boolean hasVertex(final T vertex) {
        Preconditions.checkNotNull(vertex, "Vertex cannot be null");
        return adjacentList.containsKey(vertex);
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

    private Iterable<Edge<T>> adjacentTo(final T vertex) {
        if (!hasVertex(vertex)) {
            return Collections.emptySet();
        }
        return adjacentList.get(vertex);
    }

    public void addVertex(final T vertex) {
        if (!hasVertex(vertex)) {
            adjacentList.put(vertex, new TreeSet<Edge<T>>());
        }
    }

    public void addEdge(final T from, final T to) {
        this.addEdge(from, to, Edge.DEFAULT_EDGE_WEIGHT);
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
        Set<Edge<T>> edgesOfPath = new LinkedHashSet<Edge<T>>();
        Set<T> verticesVisited = new HashSet<T>();
        Stack<Edge<T>> edgesStackToVisit = new Stack<Edge<T>>();
        T vertex = from;

        do {
            Edge<T> edge = null;

            if(!edgesStackToVisit.isEmpty()) {
                edge = edgesStackToVisit.pop();
                vertex = edge.getEndVertex();
            }

            if(!verticesVisited.contains(vertex)) {
                verticesVisited.add(vertex);

                Preconditions.checkNotNull(edge, "Edge cannot be null");
                edgesOfPath.add(edge);

                if(vertex.equals(to)) {
                    return edgesOfPath;
                }

                for(final Edge<T> adjEdge : adjacentTo(vertex)) {
                    edgesStackToVisit.push(adjEdge);
                }
            }
            else {
                edgesOfPath.remove(edge);
            }
        } while(!edgesStackToVisit.isEmpty());

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
        final Set<T> verticesVisited = new HashSet<T>();
        final Queue<T> edgesQueueToVisit = new LinkedList<T>();
        edgesQueueToVisit.add(vertex);

        while(!edgesQueueToVisit.isEmpty()) {
            final T vertexVisited = edgesQueueToVisit.poll();
            verticesVisited.add(vertex);
            System.out.print(vertexVisited + " ");
            for(final Edge<T> adjEdge : adjacentTo(vertexVisited)) {
                if(!verticesVisited.contains(adjEdge.getEndVertex())) {
                    verticesVisited.add(adjEdge.getEndVertex());
                    edgesQueueToVisit.add(adjEdge.getEndVertex());
                }
            }
        }
        return verticesVisited;
    }

    public Set<T> depthFirstTraversal(final T vertex) {
        final Set<T> verticesVisited = new HashSet<T>();
        final Stack<T> edgesStackToVisit = new Stack<T>();
        edgesStackToVisit.push(vertex);

        while(!edgesStackToVisit.isEmpty()) {
            final T vertexVisited = edgesStackToVisit.pop();

            if(!verticesVisited.contains(vertexVisited)) {
                verticesVisited.add(vertexVisited);
                System.out.print(vertexVisited +  " ");

                for(final Edge<T> adjEdge : adjacentTo(vertexVisited)) {
                    edgesStackToVisit.push(adjEdge.getEndVertex());
                }
            }
        }
        return verticesVisited;
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
