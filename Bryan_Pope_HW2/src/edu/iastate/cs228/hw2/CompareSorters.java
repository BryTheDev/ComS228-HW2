package edu.iastate.cs228.hw2;

/**
 *  
 * @author Bryan Pope
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		// Generates the scanners array, as well as a scanner and a random object
		PointScanner[] scanners = new PointScanner[4];
		Scanner scnr = new Scanner(System.in);
		Random rand = new Random();

		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");
		int trialCounter = 1, keyNumber = 0, randPts = 0;
		// The loop keeps going until the user decides to quit
		while (keyNumber != 3) {
			System.out.println(String.format("Trial: %d", trialCounter));
			keyNumber = scnr.nextInt();
			// If the user decides on random points
			if (keyNumber == 1) {
				System.out.println("Enter number of random points: ");
				randPts = scnr.nextInt();

				try {
					Point[] randPoints = generateRandomPoints(randPts, rand);

					// Populates the PointScanner array with pointscanners
					scanners[0] = new PointScanner(randPoints, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(randPoints, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(randPoints, Algorithm.MergeSort);
					scanners[3] = new PointScanner(randPoints, Algorithm.QuickSort);
				}
				// Loops back to the beginning if an exception is caught
				catch (IllegalArgumentException e) {
					continue;
				}
			}
			// if the user chooses file
			if (keyNumber == 2) {
				System.out.println("Points from a file");
				System.out.println("File name: ");
				String fileName = scnr.next();
				try {
					scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
					scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
				}
				// If there's an input mismatch or FileNotFoundException
				catch (Exception e) {
					System.out.println("Issue with file, try again, or with a different file");
					continue;
				}
			}
			if (keyNumber == 3) {
				break;
			}

			for (int i = 0; i < scanners.length; i++) {
				scanners[i].scan();
			}

			System.out.println("algorithm   size  time (ns)");
			System.out.println("----------------------------------");
			for (int i = 0; i < scanners.length; i++) {
				System.out.println(scanners[i].stats());
			}
			System.out.println("----------------------------------");
			System.out.println();
			trialCounter += 1;

		}
		//
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round,
		// set up scanning as follows:
		//
		// a) If asked to scan random points, calls generateRandomPoints() to initialize
		// an array
		// of random points.
		//
		// b) Reassigns to the array scanners[] (declared below) the references to four
		// new
		// PointScanner objects, which are created using four different values
		// of the Algorithm type: SelectionSort, InsertionSort, MergeSort and QuickSort.
		//
		//

		// For each input of points, do the following.
		//
		// a) Initialize the array scanners[].
		//
		// b) Iterate through the array scanners[], and have every scanner call the
		// scan()
		// method in the PointScanner class.
		//
		// c) After all four scans are done for the input, print out the statistics
		// table from
		// section 2.
		//
		// A sample scenario is given in Section 2 of the project description.

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] × [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] points = new Point[numPts];
		for (int i = 0; i < numPts; i++) {
			int newX = rand.nextInt(101) - 51; // randomly generates a new x variable between -50 and 50
			int newY = rand.nextInt(101) - 51; // Randomly generates a new y variable between -to and 50
			Point p = new Point(newX, newY);
			points[i] = p;
		}
		return points;
	}

}
