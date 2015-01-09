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

                if(edge != null) {
                    edgesOfPath.add(edge);
                }

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

    public List<T> dijkstraShortestPath(T source, T target) {

        Map<T, Double> distances = getDefaultDistances(source);
        Map<T, T> predecessors = new HashMap<T, T>();

        //PriorityQueue<T> vertexQueue = new PriorityQueue<T>();
        Queue<T> vertexQueue = new LinkedList<T>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            T u = vertexQueue.poll();

            for (Edge<T> e : adjacentTo(u)) {
                T v = e.getEndVertex();

                double distanceThroughU = distances.get(u) + e.getWeight();
                if (distanceThroughU < distances.get(v)) {

                    // Vertex is removed in order to makes sure that the vertex does not
                    // exists in the priority queue before adding the vertex.
                    // The vertex needs to be added if it does not exists in the queue.
                    vertexQueue.remove(v);

                    distances.put(v, distanceThroughU);
                    predecessors.put(v, u);
                    vertexQueue.add(v);
                }
            }
        }

        System.out.println("DIJKSTRA'S SHORTEST DISTANCE: " + distances.get(target));

        List<T> path = new ArrayList<T>();
        for (T vertex = target; vertex != null; vertex = predecessors.get(vertex)) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    private Map<T, Double> getDefaultDistances(T source) {
        Map<T, Double> distanceMap = new HashMap<T, Double>();
        for (T vertex : adjacentList.keySet()) {
            if(vertex.equals(source)) {
                distanceMap.put(vertex, Double.valueOf(0));
            }
            else {
                distanceMap.put(vertex, Double.MAX_VALUE);
            }
        }
        return distanceMap;
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

        dijkstraExample();
    }

    private static void dijkstraExample() {
        Graph G = new Graph(GraphType.UNDIRECTED_GRAPH);

        G.addEdge("A", "B", 2.0);
        G.addEdge("A", "D", 7.0);
        G.addEdge("A", "F", 12.0);
        G.addEdge("A", "O", 2.0);

        G.addEdge("B", "C", 1.0);
        G.addEdge("B", "D", 4.0);
        G.addEdge("B", "E", 3.0);
        G.addEdge("B", "O", 5.0);

        G.addEdge("D", "E", 1.0);
        G.addEdge("D", "T", 5.0);

        G.addEdge("F", "T", 3.0);

        G.addEdge("O", "C", 4.0);

        G.addEdge("C", "E", 4.0);
        G.addEdge("E", "T", 7.0);

        System.out.println(G.dijkstraShortestPath("O", "T"));
    }
}

interface Function<Arg> {
    public void apply(Arg arg);
}
