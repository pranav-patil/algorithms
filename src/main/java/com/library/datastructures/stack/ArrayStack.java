package com.library.datastructures.stack;

/**
 * Implementation of the stack ADT using a fixed-length array. An
 * exception is thrown if a push operation is attempted when the size
 * of the stack is equal to the length of the array.
 * Includes all the main methods of the built-in java.util.Stack.
 */
public class ArrayStack<E> {

    // The actual capacity of the stack array
    protected int capacity;
    // index for the top of the stack
    protected int top = -1;
    protected E elements[];

    public static final int CAPACITY = 10;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int cap) {
        capacity = cap;
        elements = (E[]) new Object[capacity];
    }

    public int size() {
        return (top + 1);
    }

    public boolean isEmpty() {
        return (top < 0);
    }

    public void push(E element) {
        if (size() == capacity)
            throw new RuntimeException("Stack is full. ");

        // first increment the top and then add to the array
        elements[++top] = element;
    }

    public E top() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty. ");

        return elements[top];
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. ");
        }

        E element = elements[top];
        elements[top--] = null; // dereference S[top] for garbage collection.
        return element;
    }

    @Override
    public String toString() {
        String s = "[";

        if (size() > 0) {
            s += elements[0];
        }

        if (size() > 1) {
            for (int i = 1; i <= size() - 1; i++) {
                s += ", " + elements[i];
            }
        }

        return s + "]";
    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<String>(10);
        arrayStack.push("A");
        arrayStack.push("D");
        arrayStack.push("G");
        arrayStack.push("S");
        arrayStack.push("J");
        System.out.println(arrayStack);
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }
}
