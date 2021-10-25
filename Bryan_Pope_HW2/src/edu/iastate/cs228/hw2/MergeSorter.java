package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Bryan Pope
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		this.algorithm = "mergesort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		mergeSortRec(this.points);

	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		// Base case, telling the recursive method to return
		if (pts.length == 1) {
			return;
		} else {
			// finds the index of the middle array, and creates two arrays of appropriate
			// size
			int middle = pts.length / 2;
			Point[] arr1 = new Point[middle];
			Point[] arr2 = new Point[pts.length - middle];

			// This command copies the halves of the array into new sub arrays.
			System.arraycopy(pts, 0, arr1, 0, middle);
			System.arraycopy(pts, middle, arr2, 0, arr2.length);

			// calls this method on the two arrays
			mergeSortRec(arr1);
			mergeSortRec(arr2);
			// new array to temporarily store the values from the merge
			Point[] temp = new Point[pts.length];
			temp = merge(arr1, arr2);

			// Copies the elements from the temp array into this.pts, which passses the
			// array back up/\.
			for (int i = 0; i < temp.length; i++) {
				pts[i] = temp[i];
			}

		}

	}

	/**
	 * This helper method combines the two arrays together but sorted
	 * 
	 * @param a an array of points
	 * @param b an array of points
	 * @return a sorted and merged single array
	 * 
	 */
	private Point[] merge(Point[] a, Point[] b) {
		int lenA = a.length;
		int lenB = b.length;
		int i = 0, j = 0, k = 0;

		Point[] mergeArr = new Point[lenA + lenB];
		// This loop will continue to execute until one array has been completely used
		while (i < lenA && j < lenB) {
			// This executes if a is not greater than b
			if (this.pointComparator.compare(a[i], b[j]) != 1) {
				mergeArr[k] = a[i];
				i++;
			} else {
				mergeArr[k] = b[j];
				j++;
			}
			k++;
		}

		// copies the remaining elements from a
		while (i < lenA) {
			mergeArr[k] = a[i];
			i++;
			k++;
		}

		// copies the remaining elements from b
		while (j < lenB) {
			mergeArr[k] = b[j];
			j++;
			k++;
		}
		return mergeArr;
	}

}
