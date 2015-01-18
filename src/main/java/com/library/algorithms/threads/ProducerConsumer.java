package com.library.algorithms.threads;

/**
 * Producer Consumer pattern
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

  public static void main (String [] args) throws InterruptedException {
	  
	// Use BlockingQueue buffer for more throughput
	BlockingQueue<Integer> buffer =  new LinkedBlockingQueue<Integer>();
    
    // create new producer and consumer threads
	Producer producer = new Producer(buffer);
	Consumer consumer = new Consumer(buffer);
    
    // starting producer consumer threads
	producer.start();
	consumer.start();
    
    // wait for 10 seconds
    Thread.sleep(10*1000); 
    
    // terminate the execution of the Threads
    producer.terminate();
    consumer.terminate();
  }
} 

/** The Producer Class */
class Producer extends Thread {
	
  	private BlockingQueue<Integer> producerBuffer;
    protected boolean done = false;
  	
  	public Producer (BlockingQueue<Integer> buf) {
  		producerBuffer = buf;
    }

    public void run() {
    	while (!done) {
    	    try {
				// Generate a Random number and sleep for random milliseconds
				int random = (int) (Math.random() * 100);
				Thread.sleep(random);

    	    	// Add the random number to the Queue
    	    	producerBuffer.put(random);
    	    	System.out.println("Produced Value = " + random);

    	    } catch (InterruptedException e) {
				return;
			}
    	}
    }
    
  	// set the flag to terminate the thread
    public void terminate() {
    	done = true;
    }
}

/** The Consumer Class */
class Consumer extends Thread {
  	private BlockingQueue<Integer> consumerBuffer;
    protected boolean done = false;
  	
  	public Consumer (BlockingQueue<Integer> buffer) {
  		consumerBuffer = buffer;
    }
    
    public void run() {
    	int value;
    	while (!done) {
    		try {
    			// Consume the random number from the Queue
    			value = consumerBuffer.take();
    			System.out.println("Consumed Value = " + value);
    		} catch (InterruptedException e) {
				return;
			}
    	}
    }
    
  	// set the flag to terminate the thread
    public void terminate() {
    	done = true;
    }
}
