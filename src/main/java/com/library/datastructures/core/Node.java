package com.library.datastructures.core;

public class Node<E> {
	public E element;
	public Node<E> next;
	
	public Node() {
		this(null, null);
	}
	
	public Node(E e, Node<E> n) {
		element = e;
		next = n;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

