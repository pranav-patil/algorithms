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

    /**
     * When a thread invokes a synchronized method, it automatically acquires the intrinsic lock
     * for that method's object and releases it when the method returns. Hence two synchronized
     * methods CANNOT be executed parallely by multiple threads.
     *
     * @param item
     * @throws InterruptedException
     */
    public synchronized void enqueue(T item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            // current thread releases ownership of this monitor and waits until
            // another thread notifies threads waiting on this object's monitor to wake up.
            wait();
        }

        // If notifyAll wakes all waiting threads to acquirethe lock then Why its called before add item ?
        // The awakened threads will not be able to proceed until the current thread
        // relinquishes the lock on this object.

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
