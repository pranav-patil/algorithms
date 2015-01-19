package com.library.datastructures.queue;

import com.library.datastructures.core.Node;

public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> front;
    private Node<E> rear;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public E front() {
        return front.getElement();
    }

    public void enqueue(E element) {
        Node<E> node = new Node<E>();
        node.setElement(element);
        node.setNext(null);

        if (isEmpty()) {
            front = node;
        } else {
            rear.setNext(node);
        }
        rear = node;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }

        E element = front.getElement();
        front = front.getNext();
        size--;

        if (isEmpty()) {
            rear = null;
        }
        return element;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String text = "Queue = [";
        Node<E> iterator = this.front;

        while (iterator != null) {
            text = text + iterator.getElement();
            iterator = iterator.getNext();

            if (iterator != null) {
                text += ", ";
            }
        }

        return text + "]";
    }

    public static void main(String[] args) {
        LinkedListQueue<String> linkedListQueue = new LinkedListQueue<String>();
        linkedListQueue.enqueue("A");
        linkedListQueue.enqueue("G");
        linkedListQueue.enqueue("J");
        linkedListQueue.enqueue("S");
        linkedListQueue.enqueue("E");
        System.out.println(linkedListQueue);
        System.out.println(linkedListQueue.dequeue());
        System.out.println(linkedListQueue.dequeue());
        System.out.println(linkedListQueue);
    }
}
