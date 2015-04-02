package com.library.algorithms.threads.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Processor<T> {

    private volatile boolean isShutdown = false;
    private ExecutorService executor;
    private BlockingQueue<T> requestBuffer;

    /**
     * Processes requests from the queue with no more than maxThreads threads
     * For each request object calls RequestHandler<T>.processRequest(o) only once in a separate thread
     * When the queue is empty and all processing is finished no threads exist.
     *
     * @param requestHandler - an object that handles requests
     * @param maxThreads     - total number of threads
     */
    public Processor(RequestHandler<T> requestHandler, int maxThreads) {
        this.requestBuffer = new LinkedBlockingQueue<T>();
        this.executor = Executors.newFixedThreadPool(maxThreads);

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isShutdown || !requestBuffer.isEmpty()) {
                    try {
                        Runnable worker = new WorkerThread(requestHandler, requestBuffer.take());
                        executor.execute(worker);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Puts the request into a queue, does not wait for the request to complete
     *
     * @param o - request object
     */
    public void addRequest(T o) {
        if (!isShutdown) {
            requestBuffer.add(o);
        }
    }

    /**
     * Asynchronous shutdown, returns immediately.
     * Instructs the processor to stop accepting requests and finish existing tasks
     *
     * @param o – if not null, notifies all waiting threads on
     *          this object upon successful shutdown
     */
    public void shutDown(Object o) {

        this.isShutdown = true;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!requestBuffer.isEmpty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                executor.shutdown();
                while (!executor.isTerminated()) {
                }
            }
        };
        thread.start();
    }

    /**
     * @return true if the processor is shut down
     */
    public boolean isShutDown() {
        return isShutdown;
    }

    public static void main(String[] args) {

        RequestHandler<String> handler = new RequestHandler<String>() {
            @Override
            public void processRequests(String o) throws Exception {
                System.out.println(Thread.currentThread().getName() + " : " + o);
            }
        };

        Processor processor = new Processor(handler, 5);
        processor.addRequest("AAAAA");
        processor.addRequest("BBBBB");
        processor.addRequest("CCCCC");
        processor.addRequest("DDDDD");
        processor.addRequest("EEEEE");
        processor.addRequest("FFFFF");
        processor.addRequest("GGGGG");
        processor.addRequest("HHHHH");
        processor.addRequest("IIIII");
        processor.addRequest("JJJJJ");
        processor.addRequest("KKKKK");
        processor.addRequest("LLLLL");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processor.addRequest("MMMMM");
        processor.addRequest("NNNNN");
        processor.addRequest("OOOOO");
        processor.addRequest("PPPPP");
        processor.addRequest("QQQQQ");
        processor.addRequest("RRRRR");
        processor.addRequest("SSSSS");
        processor.addRequest("TTTTT");
        processor.addRequest("UUUUU");
        processor.addRequest("VVVVV");
        processor.addRequest("WWWWW");
        processor.addRequest("XXXXX");
        processor.addRequest("YYYYY");

        String str = "000000";
        processor.addRequest(str);
        processor.shutDown(str);
        System.out.println("---------------------S---H---U---T---D---O---W---N----------------------------");

        processor.addRequest("ZZZZZ");
        processor.addRequest("111111");
        processor.addRequest("222222");
        processor.addRequest("333333");
        processor.addRequest("444444");
        processor.addRequest("555555");
        processor.addRequest("666666");
        processor.addRequest("777777");
        processor.addRequest("888888");
        processor.addRequest("999999");
    }
}
