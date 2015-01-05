package com.library.algorithms.graph;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

import java.util.Objects;

public class Edge<V extends Comparable<V>> implements Comparable<Edge<V>> {
    private final V startVertex;
    private final V endVertex;
    private final double weight;

    public Edge(final V startVertex, final V endVertex, final double weight){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
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
        return MoreObjects.toStringHelper("Edge")
                .add("startVertex", startVertex)
                .add("endVertex", endVertex)
                .add("weight", weight)
                .toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Edge)) {
            return false;
        }
        final Edge<V> edge = (Edge<V>)other;
        return Objects.equals(edge.startVertex, this.startVertex) &&
               Objects.equals(edge.endVertex, this.endVertex) &&
               Objects.equals(edge.getWeight(), this.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(startVertex, endVertex, weight);
    }

    @Override
    public int compareTo(Edge<V> edge) {
        return ComparisonChain.start()
                .compare(this.getStartVertex(), edge.getStartVertex())
                .compare(this.getEndVertex(), edge.getEndVertex())
                .compare(this.getWeight(), edge.getWeight())
                .result();
    }
}
