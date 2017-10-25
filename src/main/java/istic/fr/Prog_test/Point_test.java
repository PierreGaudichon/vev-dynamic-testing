package istic.fr.Prog_test;

import junit.framework.*;

public class Point_test extends TestCase{
	
	public void testConstructor() throws Exception {

		// on initialise les points
		Point p = new Point(1,1);

		// on vérifie les attributs
		assertEquals(1,p.getX());
		assertEquals(1,p.getY());
	}
	
	public void testSetters() throws Exception {

		// on initialise les points
		Point p = new Point(1,1);
		
		// on change les coord du Point
		p.setX(3);
		p.setY(6);

		// on test
		assertEquals(3,p.getX());
		assertEquals(6,p.getY());
	}
	
	public void testDistance() throws Exception {

		// on initialise les points
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,3);
		
		// on calcule la distance
		double test = p1.distance(p2);
		
		// on test avec la valeur qu'on devrait trouver
		assertEquals(Math.sqrt(4),test);
	}

}