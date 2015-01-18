package com.library.datastructures.queue;

/**
 * Problem with Array queue: When we dequeue element we need to either:
 * 1) Shift all values to front of array and update front and back.
 * 2) Make the array as circular.
 * <p/>
 * Here we will be shifting the elements to make the array queue work.
 * Now we don't need a front pointer in such case as front is mostly pointing to the 0'th element
 * and is set to -1 when rear becomes -1.
 *
 * Referenced from http://faculty.washington.edu/moishe/javademos/ArrayQueue.java
 */
public class ArrayQueue<E> {

    private static final int INITIAL_DEFAULT_CAPACITY = 10;
    private int capacity;
    private E elements[];
    private int rear = -1;

    public ArrayQueue() {
        this(INITIAL_DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        super();
        this.capacity = capacity;
        this.elements = (E[]) new Object[capacity];
    }

    public void enqueue(E entry) {

        if (rear + 1 == elements.length) {
            throw new RuntimeException("Queue is full.");
        }

        rear++;
        elements[rear] = entry;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        } else {

            if (rear == 0) {
                System.out.println(rear);
            }
            E value = elements[0];

            // shift the elements
            for (int scan = 0; scan < rear; scan++) {
                elements[scan] = elements[scan + 1];
            }

            elements[rear] = null;
            rear--;

            return value;
        }
    }

    private boolean isEmpty() {
        return (rear < 0);
    }

    @Override
    public String toString() {
        String text = "\nQueue = ";

        for (E element : elements) {
            text = text + element + " ";
        }

        return text;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(7);
        System.out.println(queue);
        System.out.println("Get Queue = " + queue.dequeue());
        queue.enqueue(9);
        System.out.println(queue);
        System.out.println("Get Queue = " + queue.dequeue());
        System.out.println(queue);
        System.out.println("Get Queue = " + queue.dequeue());
        System.out.println(queue);
    }
}
