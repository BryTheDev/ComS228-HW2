package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Bryan pope
 *
 */

/**
 * 
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public InsertionSorter(Point[] pts) {
		super(pts);
		this.algorithm = "Insertion Sorter";
	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 */
	@Override
	public void sort() {
		int i, j;
		for (i = 1; i < this.points.length; i++) {
			j = i - 1;
			// Our later points write over this point, this saves it at a safe spot
			Point selPoint = this.points[i];
			// This loop keeps iterating, moving the points that are greater up until
			// there's one that is less than
			while ((j >= 0) && this.pointComparator.compare(selPoint, this.points[j]) == -1) {
				this.points[j + 1] = this.points[j];
				j--;
			}
			// Sets the point at the correct space
			this.points[j + 1] = selPoint;
		}
	}
}
