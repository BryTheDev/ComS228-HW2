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
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public SelectionSorter(Point[] pts) {
		super(pts);
		this.algorithm = "Selection Sort";
	}

	/**
	 * Apply selection sort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		int i, j;
		int pointIndex;
		Point minPoint;
		for (i = 0; i < points.length - 1; i++) {
			minPoint = this.points[i];
			pointIndex = i;
			// this goes through the array, comparing each point to the minpoint
			for (j = 1 + i; j < points.length; j++) {
				// if the point is smaller than the minPoint, this.points[j] becomes the
				// minpoint
				if (this.pointComparator.compare(this.points[j], minPoint) == -1) {
					minPoint = this.points[j];
					pointIndex = j;
				}
			}
			// swaps the two points
			this.points[pointIndex] = this.points[i];
			this.points[i] = minPoint;
		}
	}
}
