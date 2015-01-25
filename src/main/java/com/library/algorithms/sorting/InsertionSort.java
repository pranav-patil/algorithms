package com.library.algorithms.sorting;

public class InsertionSort {

	/**
	 * Insertion sort of an array of characters into non-decreasing order
	 */
	public static void sort(char[] a) {

		for (int i = 1; i < a.length; i++) { 	// index from the second character in a
			char cur = a[i]; 					// the current character to be inserted
			int j = i - 1; 						// start comparing with cell left of i

			System.out.println("\na[j] > cur : " + a[j] + " > " + cur);

			while ((j >= 0) && (a[j] > cur))	// while a[j] is out of order with cur
			{
				System.out.println("\na[j] > cur : " + a[j] + " > " + cur);
				System.out.print("\nbefore = " + new String(a));
				a[j + 1] = a[j--]; 				// move a[j] right and decrement j
				System.out.print("\nafter = " + new String(a));
			}
			a[j + 1] = cur; 					// this is the proper place for cur
			System.out.print("\ncurrent = " + new String(a));
		}
	}
	
	public static void main(String[] args) {

		char[] sample = new char[] {'C', 'F', 'G', 'Z', 'A', 'K'};
		System.out.println("\nBEFORE: " + new String(sample));

		sort(sample);

		System.out.println("\n\nSORTED: " + new String(sample));
	}
}
