package com.library.algorithms.threads;

import java.util.LinkedList;
import java.util.List;

/**
 * http://java.dzone.com/news/java-concurrency-blocking-queu
 */
public class BlockingQueue<T> {

    private List<T> queue = new LinkedList<T>();
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.isEmpty()) {
            notifyAll();
        }
        this.queue.add(item);
    }

    public synchronized T dequeue() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }

        return this.queue.remove(0);
    }
}
