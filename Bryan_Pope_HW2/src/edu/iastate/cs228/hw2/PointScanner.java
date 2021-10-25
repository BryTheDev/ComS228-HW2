package edu.iastate.cs228.hw2;

/**
 * 
 * @author Bryan Pope
 *
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	public Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
										// the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}

		this.sortingAlgorithm = algo;
		// Instantiates the points array
		this.points = new Point[pts.length];
		// Copies it to the this.points array
		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		Scanner scnr = new Scanner(new FileReader(inputFileName));
		int index = 0, numElements = 0;
		this.sortingAlgorithm = algo;
		// This counts the number of elements in the list so we can declare an array of
		// the right size
		while (scnr.hasNextInt()) {
			scnr.nextInt();
			numElements++;
		}
		// This throws an exception if there is an odd number of elements
		if (numElements % 2 == 1) {
			throw new InputMismatchException();
		}
		// Instantiates the points array
		this.points = new Point[numElements / 2];

		// Resets the scanner so we can read the file again
		scnr.reset();
		scnr = new Scanner(new FileReader(inputFileName));

		// Puts the integers into points and copies htem to the new array
		while (scnr.hasNextInt()) {
			int newX = scnr.nextInt();
			int newY = scnr.nextInt();
			Point p = new Point(newX, newY);
			this.points[index] = p;
			index++;
		}
		// it's important to close your scanners!
		scnr.close();
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;

		// Selects the correct type of algorithm
		if (this.sortingAlgorithm.equals(Algorithm.SelectionSort)) {
			aSorter = new SelectionSorter(this.points);
		} else if (this.sortingAlgorithm.equals(Algorithm.InsertionSort)) {
			aSorter = new InsertionSorter(this.points);
		} else if (this.sortingAlgorithm.equals(Algorithm.MergeSort)) {
			aSorter = new MergeSorter(this.points);
		} else {
			aSorter = new QuickSorter(this.points);
		}

		// starts the timer
		long start = System.nanoTime();
		int xVal = 0;
		int yVal = 0;
		for (int i = 0; i < 2; i++) {
			// a) call setComparator() with an argument 0 or 1.
			aSorter.setComparator(i);
			// b) call sort().
			aSorter.sort();
			aSorter.getMedian();
			if (i == 0) {
				xVal = aSorter.getMedian().getX();
			} else {
				yVal = aSorter.getMedian().getY();
			}
		}
//	     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		this.medianCoordinatePoint = new Point(xVal, yVal);

		long end = System.nanoTime();
		// e) sum up the times spent on the two sorting rounds and set the instance
		// variable scanTime.
		this.scanTime = end - start;
		// create an object to be{ referenced by aSorter according to sortingAlgorithm.
		// for each of the two
		// rounds of sorting, have aSorter do the following:
	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance, 18 spots selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		return String.format("%-17s %-5d %d", this.sortingAlgorithm, this.points.length, this.scanTime);
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {
		return "MCP: (" + this.medianCoordinatePoint.getX() + ", " + this.medianCoordinatePoint.getY() + ")";
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		String outputFileName = "MCP.txt";
		try {
			FileWriter myWriter = new FileWriter(outputFileName);
			myWriter.write(this.medianCoordinatePoint.toString());
			myWriter.close();
		} catch (IOException e) {
			throw new FileNotFoundException();
		}

	}

}
