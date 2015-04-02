package com.library.algorithms.threads.pool;

public class WorkerThread<E extends RequestHandler<T>, T> implements Runnable {

    private E command;
    private T object;

    public WorkerThread(E command, T object) {
        this.command = command;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            command.processRequests(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command.toString();
    }
}
