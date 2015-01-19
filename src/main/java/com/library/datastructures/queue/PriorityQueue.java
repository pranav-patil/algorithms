package com.library.datastructures.queue;

/**
 * References:
 * http://www.vias.org/javacourse/chap16_07.html
 * http://www.cs.dartmouth.edu/~cs5/lectures/0523/ArrayMinPriorityQueue.java
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private static final int INITIAL_DEFAULT_CAPACITY = 10;
    private int capacity;
    private E elements[];
    private int rear = 0;

    public PriorityQueue() {
        this(INITIAL_DEFAULT_CAPACITY);
    }

    public PriorityQueue(int capacity) {
        super();
        this.capacity = capacity;
        this.elements = (E[]) new Comparable[capacity];
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public boolean isEmpty() {
        return (rear <= 0);
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty !");
        }
        int maxIndex = indexOfMaximum();
        return elements[maxIndex];
    }

    @Override
    public void enqueue(E element) {
        if (rear == elements.length) {
            throw new RuntimeException("Queue is full.");
        }
        elements[rear] = element;
        rear++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty !");
        }

        // get the element with maximum priority from the array
        int maxIndex = indexOfMaximum();
        E result = elements[maxIndex];

        // move the last item into the empty slot
        rear--;
        elements[maxIndex] = elements[rear];
        elements[rear] = null;
        return result;
    }

    /**
     * Search through the entire array for the smallest element.
     */
    private int indexOfMaximum() {
        int maxIndex = 0;

        // find the index of the item with the highest priority
        for (int i = 1; i < rear; i++) {
            if (elements[i].compareTo(elements[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    @Override
    public String toString() {
        String text = "\nPriorityQueue = ";

        for (E element : elements) {
            text = text + element + " ";
        }

        return text;
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
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
