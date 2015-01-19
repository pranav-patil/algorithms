package com.library.datastructures.linkedlist;

import com.library.datastructures.core.Node;

public class SinglyLinkedList<E> {

	private Node<E> head;

	public SinglyLinkedList() {
		head = null;
	}

	public int length() {
		int count = 0;
		Node<E> current = this.head;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	public void insert(E element, int position) {

		if (position < 0) {
			throw new IllegalArgumentException("Position cannot be negative.");
		}

		Node<E> newNode = new Node(element, null);

		if (position == 0 || head == null) {
			newNode.next = head;
			head = newNode;
		} else {
			Node<E> traverse = head;
			Node<E> temp = null;
			int currentPosition = 0;

			// Point temp to the node before the position th node.
			while (traverse != null && (currentPosition < position)) {
				currentPosition++;
				temp = traverse;
				traverse = traverse.next;
			}

			// New node comes after the node before the position th node.
			temp.next = newNode;
			// The next node after the new node is the position th node.
			newNode.next = traverse;
		}
	}

	public E remove(int position) {

		if (position < 0) {
			throw new IllegalArgumentException("Position cannot be negative.");
		}

		int currentPosition = 0;
		Node<E> traverse = null, temp = null;

		if (head == null) {
			throw new RuntimeException("Linked List Empty !");
		}

		traverse = head;
		E element = null;

		if (position == 0) {
			traverse = head;
			head = head.next;
			element = traverse.element;
			traverse = null; // free node
		} else {
			while ((traverse != null) && (currentPosition < position)) {
				currentPosition++;
				temp = traverse;
				traverse = traverse.next;
			}

			if (traverse == null) {
				throw new RuntimeException("Position does not exist.");
			} else {
				// Set the next node of the previous node to the position, to be
				// the next node of the position th node. i.e. skip the position node.
				temp.next = traverse.next;

				// return the element which is removed.
				element = traverse.element;

				// set traverse to null to free the position th node.
				traverse = null;
			}
		}

		return element;
	}

	public Node reverse() {
		Node<E> temp = null;
		Node<E> nextNode = null;

		while (head != null) {

			// Set next node of the head
			nextNode = head.next;

			// We are creating a new list with temp and assigning to head->next reverses the list.
			head.next = temp;
			temp = head;

			// Head now points to the next node of the original list
			head = nextNode;
		}

		head = temp;
		return head;
	}

	/**
	 * Floyd Cycle Finding Algorithm
	 * <p/>
	 * Use two pointers, one fast pointer which jump by 2 nodes, while
	 * other slow pointer which jumps by one node for an iteration.
	 * If the list has loop then eventually the fast and slow pointer will
	 * meet, if no loop exists then both will reach end of the list.
	 *
	 * NOTE: When fast pointer reaches the end of the list, the slow pointer
	 * reaches the middle as fast pointer is twice as fast compared to slow pointer.
	 */
	public boolean hasLoop() {

		Node<E> slowPtr = this.head;
		Node<E> fastPtr = this.head;

		while (slowPtr != null && fastPtr != null) {

			fastPtr = fastPtr.next;

			if (fastPtr == slowPtr) {
				return true;
			}

			if (fastPtr == null) {
				return false;
			}

			fastPtr = fastPtr.next;

			if (fastPtr == slowPtr) {
				return true;
			}

			slowPtr = slowPtr.next;
		}
		return false;
	}

	/**
	 * Find the nth element from the end of the linked list with O(n).
	 */
	public E elementFromEnd(int position) {

		if (position < 0) {
			throw new IllegalArgumentException("Position cannot be negative.");
		}

		int count = 0;
		Node<E> traverse = head, temp = null;

		// The traverse pointer moves only after temp makes n (i.e. position) moves.
		for (temp = head; temp != null; temp = temp.next) {
			if(position < count) {
				traverse = traverse.next;
			}
			count++;
		}

		// count should be greater than position for the nth element to exist from the end.
		if(count >= position) {
			return traverse.element;
		}
		return null;
	}

	@Override
	public String toString() {
		Node<E> current = this.head;
		StringBuilder builder = new StringBuilder("\nLinkedList = [");

		while (current != null) {
			builder.append(current.element);
			current = current.next;

			if(current != null) {
				builder.append(",");
			}
		}

		return builder.append("]").toString();
	}

	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		list.insert("A", 0);
		list.insert("D", 0);
		list.insert("G", 1);
		list.insert("B", 2);
		list.insert("X", 3);
		list.insert("Z", 14);
		System.out.println(list);

		System.out.println("\nRemoved from LinkedList: " + list.remove(5));
		System.out.println(list);

		list.reverse();
		System.out.println(list);

		int position = 0;
		System.out.printf(position + " element from the end of linked list: " + list.elementFromEnd(position));

		System.out.println("\nLinkedList Has Loop: " + list.hasLoop());

		// Add a loop to linked list
		Node<String> traverse = list.head;
		Node<String> temp = null;
		while(traverse!=null) {
			temp = traverse;
			traverse = traverse.next;
		}

		// set the last node of the list to point to the head thus adding a loop.
		temp.next = list.head;
		System.out.println("\nAdding loop to the Linked List");

		System.out.println("\nLinkedList Has Loop: " + list.hasLoop());
	}
}
