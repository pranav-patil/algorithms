package com.library.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
	
	public static void shellSort(int[] array) {
		int increment = array.length / 2;
		while (increment > 0) {
			for (int i = increment; i < array.length; i++) {
				int j = i;
				int temp = array[i];
				while (j >= increment && array[j - increment] > temp) {
					array[j] = array[j - increment];
					j = j - increment;
				}
				array[j] = temp;
			}
			if (increment == 2) { // because 2 * 5 / 11 is 0.90 which becomes 0
				increment = 1;
			} else {
				increment *= (5.0 / 11);
			}
		}
	}
	
	public static void quickSort(int[] array, int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = array[low + (high - low) / 2];

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (array[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (array[j] > pivot) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the values.
			// As we are done we can increase i and j
			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quickSort(array, low, j);
		if (i < high)
			quickSort(array, i, high);
	}
	
	public static void mergeSort(int[] array, int low, int high) {
		if(low < high) {
			int mid = (low + high)/2;
			mergeSort(array, low, mid);
			mergeSort(array, mid+1, high);
			merge(array, low, mid, high);
		}
	}
	
	private static void merge(int[] array, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int left = low;
		int right = mid + 1;
		int k = 0;
		
		while(left <= mid && right <= high) {
			
			if(array[left] < array[right]) {
				temp[k] = array[left];
				left = left + 1;
			}
			else {
				temp[k] = array[right];
				right = right + 1;
			}
			
			k = k + 1;
		}
		
		// copy over the remaining elements from the unfinished array.
		if(left <= mid) {
			while(left <= mid) {
				temp[k] = array[left];
				left = left + 1;
				k = k + 1;
			}
		}
		else if (right <= high){
			while(right <= high) {
				temp[k] = array[right];
				right = right + 1;
				k = k + 1;
			}
		}
		
		// copy temp to the actual array
		for(int m = 0; m < temp.length; m++) {
			array[low + m] = temp[m];
		}
	}

	/**
	 * Fisher–Yates shuffling algorithm
	 */
	public static void shuffle(int[] array) {
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			// Simple swap
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {34, 67, 12, 81, 11, 34, 56};
		System.out.println("Before Sort: " + Arrays.toString(array));
		mergeSort(array, 0, array.length - 1);
		System.out.println("Merge Sort: " + Arrays.toString(array));
		shuffle(array);
		System.out.println("Before Sort: " + Arrays.toString(array));
		shellSort(array);
		System.out.println("Shell Sort: " + Arrays.toString(array));
		shuffle(array);
		System.out.println("Before Sort: " + Arrays.toString(array));
		quickSort(array,0, array.length-1);
		System.out.println("Quick Sort: " + Arrays.toString(array));
	}
}
