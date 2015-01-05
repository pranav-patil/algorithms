package com.library.algorithms.graph;

public class Edge<V extends Comparable<V>> implements Comparable<Edge<V>> {
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

    public Double getWeight() {
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

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + startVertex.hashCode();
        result = 31 * result + endVertex.hashCode();
        result = 31 * result + (int) weight;
        return result;
    }

    @Override
    public int compareTo(Edge<V> edge) {

        int result = 0;

        if(this.startVertex != null) {
            result = this.startVertex.compareTo(edge.startVertex);
        }

        if(result == 0 && this.endVertex != null) {
            result = this.endVertex.compareTo(edge.endVertex);
        }

        if(result == 0) {
            result = (int) (this.weight - edge.weight);
        }
        return result;
    }
}

