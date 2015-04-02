package com.library.algorithms.threads.pool;

public interface RequestHandler<T> {
    /**
     * A thread-safe method to process a single request
     *
     * @param o - request object
     */
    public void processRequests(T o) throws Exception;
}
