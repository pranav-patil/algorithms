package com.library.algorithms.graph;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

    private T label;

    public Vertex(T v) {
        label = v;
    }

    public T getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + label.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return label.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        return ((Vertex<T>)o).label.equals(this.label);
    }

    @Override
    public int compareTo(Vertex<T> vertex) {
        if(this.label != null) {
            return this.label.compareTo(vertex.label);
        }

        return -1;
    }
}
