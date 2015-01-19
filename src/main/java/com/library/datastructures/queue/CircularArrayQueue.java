package com.library.datastructures.queue;

/**
 * Circular queue requires to maintain two pointers front and rear.
 * Dequeue happens using the front pointer while enqueue happens using the
 * rear pointer.
 */
public class CircularArrayQueue<E> implements Queue<E> {

    // This Queue will never hold more than capacity-1 elements to handle the case
    // were f = r which occurs both when f = r = 0 and f = r = N.
    private static final int INITIAL_DEFAULT_CAPACITY = 10;
    private int capacity;
    private E elements[];
    // We set front and rear to 0 instead of -1, to refine computation of queue size
    private int front = 0, rear = 0;

    public CircularArrayQueue() {
        this(INITIAL_DEFAULT_CAPACITY);
    }

    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        elements = (E[]) new Object[this.capacity];
    }

    public int size() {
        if (rear > front) {
            return rear - front;
        }
        return capacity - front + rear;
    }

    public boolean isEmpty() {
        return (front == rear);
    }

    public E front() {

        if (isEmpty()) {
            throw new RuntimeException("Queue Empty !");
        }
        return elements[front];
    }

    public void enqueue(E element) {

        if (size() == (capacity - 1)) {
            throw new RuntimeException("Queue Full !");
        }

        elements[rear] = element;
        rear = (rear + 1) % capacity;
    }

    public E dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("Queue Empty !");
        }

        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        return element;
    }

    @Override
    public String toString() {
        String text = "\nQueue = ";

        for (E element : elements) {
            text = text + element + ", ";
        }
        return text;
    }

    public static void main(String[] args) {
        CircularArrayQueue circularArrayQueue = new CircularArrayQueue();
        circularArrayQueue.enqueue(10);
        circularArrayQueue.enqueue(20);
        circularArrayQueue.enqueue(30);
        circularArrayQueue.enqueue(40);
        circularArrayQueue.enqueue(50);
        circularArrayQueue.enqueue(60);
        circularArrayQueue.enqueue(30);
        circularArrayQueue.enqueue(40);
        circularArrayQueue.enqueue(50);
        System.out.println("Dequeue = " + circularArrayQueue.dequeue());
        System.out.println("Dequeue = " + circularArrayQueue.dequeue());
        System.out.println("Dequeue = " + circularArrayQueue.dequeue());
        System.out.println("Dequeue = " + circularArrayQueue.dequeue());
        System.out.println(circularArrayQueue);
    }
}
