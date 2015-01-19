package com.library.datastructures.core;

public class Node<E> {
	private E element;
	private Node<E> next;
	
	public Node() {
		this(null, null);
	}
	
	public Node(E e, Node<E> n) {
		element = e;
		next = n;
	}
	
	public E getElement() {
		return element;
	}
	
	public Node<E> getNext() {
		return next;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}
}

