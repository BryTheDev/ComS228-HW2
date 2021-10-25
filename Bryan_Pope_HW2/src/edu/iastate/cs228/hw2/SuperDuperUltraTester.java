package edu.iastate.cs228.hw2;
import org.junit.*;

public class SuperDuperUltraTester {
	Point f = new Point(-2,2);
	Point a = new Point(8,2);
	Point b = new Point(-2,8);
	Point c = new Point(2,0);
	Point d = new Point(-2,2);
	Point e = new Point(-2,2);
	Point[] j = {a,b,c,d,e,f};
	
	@Test
	public void testXYPoint(){
		Point p = new Point(1,2);
		Assert.assertEquals(1,p.getX());
		Assert.assertEquals(2,p.getY());
	}
	@Test
	public void testBlankPoint(){
		Point p = new Point();
		Assert.assertEquals(0,p.getX());
		Assert.assertEquals(0,p.getY());
	}
	@Test
	public void testCopyPoint(){
		Point p = new Point(10,2);
		Point j = new Point(p);
		Assert.assertEquals(10,j.getX());
		Assert.assertEquals(2,j.getY());
	}
	@Test
	public void testXGreaterThanY(){
		Point p = new Point(1,2);
		Point q = new Point(0,0);
		p.setXorY(true);
		p.compareTo(q);
		Assert.assertEquals(1,p.compareTo(q));
	}
	
	@Test
	public void testXLessThanY(){
		Point p = new Point(-1,-2);
		Point q = new Point(0,0);
		p.setXorY(true);
		p.compareTo(q);
		Assert.assertEquals(-1,p.compareTo(q));
	}
	@Test
	public void testXEqualToY(){
		Point p = new Point(0,0);
		Point q = new Point(0,0);
		p.setXorY(true);
		p.compareTo(q);
		Assert.assertEquals(0,p.compareTo(q));
	}
	@Test
	public void testYGreaterThanX(){
		Point p = new Point(1,2);
		Point q = new Point(0,0);
		p.setXorY(false);
		p.compareTo(q);
		Assert.assertEquals(1,p.compareTo(q));
	}
//	@Test
//	public void testAbstractCompareEqual(){
//		Point p = new Point(0,0);
//		Point q = new Point(0,0);
//		AbstractSorterConcrete j = new AbstractSorterConcrete();
//		j.setComparator(0);
//		j.pointComparator.compare(p, q);
//		Assert.assertEquals(0,p.compareTo(q));
//	}
//	@Test
//	public void testAbstractCompareGreater(){
//		Point p = new Point(1,2);
//		Point q = new Point(0,0);
//		AbstractSorterConcrete j = new AbstractSorterConcrete();
//		j.setComparator(0);
//		j.pointComparator.compare(p, q);
//		Assert.assertEquals(1,p.compareTo(q));
//	}
	@Test
	public void testSelectionSort(){ 
		SelectionSorter p = new SelectionSorter(j);
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
		System.out.println();
		p.setComparator(0);
		p.sort();
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
		
	}
	@Test
	public void testInsertionSort(){ 
		AbstractSorter p = new InsertionSorter(j);
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
		System.out.println();
		p.setComparator(1);
		p.sort();
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
		
	}
	
	@Test
	public void testMergeSortX(){ 
		AbstractSorter p = new MergeSorter(j);
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
		System.out.println();
		p.setComparator(0);
		p.sort();
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
		
	}
	
	@Test
	public void testMergeSortY(){ 
		AbstractSorter p = new MergeSorter(j);
		for(int i =0; i < j.length; i++) {
		//	System.out.println(p.points[i].toString());
		}
		System.out.println();
		p.setComparator(1);
		p.sort();
		for(int i =0; i < j.length; i++) {
			//System.out.println(p.points[i].toString());
		}
	}
	@Test
	public void testQuickSortX(){ 
		AbstractSorter p = new QuickSorter(j);
		for(int i =0; i < j.length; i++) {
		//	System.out.println(p.points[i].toString());
		}
		System.out.println();
		p.setComparator(0);
		p.sort();
		for(int i =0; i < j.length; i++) {
		//	System.out.println(p.points[i].toString());
		}
		
	}
//	@Test
//	public void testFileInput(){ 
//	try {	PointScanner p = new PointScanner("test.txt",Algorithm.MergeSort);
//		for(int i =0; i < p.points.length; i++) {
//			System.out.println(p.points[i].toString());
//		}
//	}
//	catch(Exception e){
//		int i = 0;
//		while(i > 250) {
//		System.out.println("FUCK");
//		i++;
//		}
//	}
//	}
	@Test
	public void testFileOutput(){ 
	try {	PointScanner p = new PointScanner("test.txt",Algorithm.MergeSort);
	Point point = new Point(-420,69);
	p.medianCoordinatePoint = point;
	p.writeMCPToFile();
	}
	catch(Exception e){
		int i = 0;
		while(i > 250) {
		System.out.println("FUCK");
		i++;
		}
	}
	}
		
	

}
