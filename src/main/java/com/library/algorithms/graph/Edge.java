package com.emprovise.library.algorithms.graph;

public class Edge<V>{
    private V startVertex;
    private V endVertex;
    private double weight;

    public Edge(V startV, V endV, double c){
        this.startVertex = startV;
        this.endVertex = endV;
        weight = c;
    }

    public V getStartVertex() {
        return startVertex;
    }

    public V getEndVertex() {
        return endVertex;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge [startVertex=" + startVertex + ", endVertex=" + endVertex + ", weight=" + weight + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge<V> edge = (Edge<V>)o;
        return  edge.startVertex.equals(this.startVertex) &&
                edge.endVertex.equals(this.endVertex) &&
                edge.getWeight() == this.getWeight();
    }
}

