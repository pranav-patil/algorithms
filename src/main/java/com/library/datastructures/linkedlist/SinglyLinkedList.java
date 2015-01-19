package com.library.datastructures.linkedlist;

import com.library.datastructures.core.Node;

public class SinglyLinkedList {

	private Node head;
	private int size;

	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	public int length() {
		int count = 0;
		Node current = this.head;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	public void insertInLinkedList(String data, int position) {
		int k = 1;
		Node traverse = head;
		Node temp = null;
		Node newNode = new Node(data, null);

		if (position == 1) {
			newNode.next = head;
			head = newNode;
		} else {
			while (traverse != null && (k < position)) {
				k++;
				temp = traverse;
				traverse = traverse.next;
			}

			if (traverse == null) {
				temp.next = newNode;
				newNode.next = null;
			} else {
				temp.next = newNode;
				newNode.next = traverse;
			}
		}
	}

	public void deleteNodeFromLinkedList(int position) {
		int k = 1;
		Node traverse = null, temp = null;

		if (head == null) {
			throw new RuntimeException("Linked List Empty !");
		}

		traverse = head;

		if (position == 1) {
			traverse = head;
			head = head.next;
			traverse = null; // free node
			return;
		} else {
			while ((traverse != null) && (k < position)) {
				k++;
				temp = traverse;
				traverse = traverse.next;
			}

			if (traverse == null) {
				throw new RuntimeException("Position does not exist.");
			} else {
				temp.next = traverse.next;
			}
		}
	}

	// Floyd Cycle Finding Algorithm
	public boolean doesLinkedListContainsLoop() {

		Node slowPtr = this.head;
		Node fastPtr = this.head;

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

	public Node reverse() {
		Node temp = null;
		Node nextNode = null;

		while (head != null) {
			System.out.println("\nnextNode = " + ((head.next != null) ? head.next.element : null));
			nextNode = head.next;
			System.out.println("head.next = " + ((temp != null) ? temp.element : null));

			// We are creating a new list with temp and assigning to head->next reverses the list.
			head.next = temp;

			System.out.println("temp = " + head.element);
			temp = head;
			System.out.println("head = " + ((nextNode != null) ? nextNode.element : null));
			head = nextNode;
		}

		return temp;
	}

	@Override
	public String toString() {
		return printList(this.head);
	}

	public static String printList(Node current) {
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

	private static void testLinkedListLoop(SinglyLinkedList list) {
		// Add a loop
		Node traverse = list.head;
		Node temp = null;
		while(traverse!=null) {
			temp = traverse;
			traverse = traverse.next;
		}
		
		temp.next = list.head;
		System.out.println("DOES THIS HAS LOOP = " + list.doesLinkedListContainsLoop());
	}
	
	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		list.insertInLinkedList("A", 1);
		list.insertInLinkedList("D", 1);
		list.insertInLinkedList("G", 2);
		list.insertInLinkedList("B", 3);
		list.insertInLinkedList("X", 1);
		list.insertInLinkedList("Z", 1);
		System.out.println(list);

		Node reverse = list.reverse();
		printList(reverse)

		list.deleteNodeFromLinkedList(5);
		System.out.println(list);

		testLinkedListLoop(list);
	}
}
