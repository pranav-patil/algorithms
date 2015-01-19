package com.library.datastructures.stack;

import com.library.datastructures.core.Node;

public class LinkedListStack<E> {

    private Node<E> top;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {

        if (top == null) {
            return true;
        }

        return false;
    }

    public void push(E element) {
        Node<E> node = new Node<E>(element, top);
        top = node;
        size++;
    }

    public E top() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. ");
        }
        return top.getElement();
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. ");
        }

        E element = top.getElement();
        top = top.getNext();
        size--;
        return element;
    }

    @Override
    public String toString() {
        String text = "Stack = [";
        Node<E> iterate = top;

        while (iterate != null) {
            text = text + iterate.getElement();
            iterate = iterate.getNext();

            if (iterate != null) {
                text += ", ";
            }
        }

        return text + "]";
    }

    public static void main(String[] args) {
        LinkedListStack<String> linkedListStack = new LinkedListStack<String>();
        linkedListStack.push("A");
        linkedListStack.push("F");
        linkedListStack.push("S");
        linkedListStack.push("Z");
        linkedListStack.push("W");
        System.out.println(linkedListStack);
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack);
    }
}
