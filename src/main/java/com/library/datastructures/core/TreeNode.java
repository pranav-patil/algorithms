package com.library.datastructures.core;

public class TreeNode<E> {

    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode() {
        this(null);
    }

    public TreeNode(E element) {
        this.element = element;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    public boolean equals(Node n){
        return (this.element.equals(n.element));
    }
}
