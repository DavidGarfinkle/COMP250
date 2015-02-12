package Sorting;

import java.util.Random;
import java.util.Scanner;
import java.lang.Object;

public class SortBenchmark {
	
	public static void main(String[] args) {
	
		Scanner cdInput = new Scanner(System.in); // to get input from commandline
		System.out.println("Please enter array length"); //prompt
		int n = cdInput.nextInt(); //getting input, storing in memory
	
		Integer[] array = new Integer[n]; //allocate array memory for n integer objects
	
		//initialize integer array with random numbers generated using class Random
		Random ralph = new Random(4); //use a particular seed so that when we reinitialize with random numbers, we can make it the same
		for(int i = 0; i<n; i++) {
			array[i] = ralph.nextInt();
		}
		
		//SelectionSort the array; measure time
		long selSortBefore = System.currentTimeMillis();
		System.out.println("Before SelectionSort: " + selSortBefore);
		MergeSort.sort(array);
		long selSortAfter = System.currentTimeMillis();
		System.out.println("After SelectionSort: " + selSortAfter);
		System.out.println("SELECTIONSORT TIME: " + (selSortAfter - selSortBefore));
		//Reinitialize array using the same seed for our random number generator: 4
		ralph.setSeed(4);
		for(int i = 0; i<n; i++) {
			array[i] = ralph.nextInt();
		}
			
		//MergeSort the array; measure time
		long mergSortBefore = System.currentTimeMillis();
		System.out.println("Before MergeSort: " + mergSortBefore);
		MergeSort.sort(array);
		long mergSortAfter = System.currentTimeMillis();
		System.out.println("After MergeSort: " + mergSortAfter);
		System.out.println("MERGSORT TIME: " + (mergSortAfter - mergSortBefore));
		//Reinitialize array using the same seed for our random number generator: 4
		ralph.setSeed(4);
		for(int i = 0; i<n; i++) {
			array[i] = ralph.nextInt();
		}
		
		//quickSort the array; measure time taken
		long quickSortBefore = System.currentTimeMillis();
		System.out.println("Before quickSort: " + quickSortBefore);
		quickSort.sort(array);
		long quickSortAfter = System.currentTimeMillis();
		System.out.println("After quickSort: " + quickSortAfter);
		System.out.println("QUICKSORT TIME: " + (quickSortAfter - quickSortBefore));
		//Reinitialize array using the same seed for our random number generator: 4
		ralph.setSeed(4);
		for(int i = 0; i<n; i++) {
			array[i] = ralph.nextInt();
		}
	}
}