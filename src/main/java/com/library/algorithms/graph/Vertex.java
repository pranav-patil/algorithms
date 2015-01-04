package com.emprovise.library.algorithms.graph;

public class Vertex<T> {

    private T label;

    public Vertex(T v) {
        label = v;
    }

    public T getLabel() {
        return label;
    }

    public int hashCode()
    {
        return label.hashCode();
    }

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
}
