package istic.fr.Prog_test;

public class Point {
	
	private int x,y;
	
	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public double distance(Point p) {
		
		double v1 = Math.pow((p.getX()-this.x),2);
		double v2 = Math.pow((p.getY()-this.y),2);
		
		double ret = Math.sqrt(v1+v2);
		
		return ret;
	}
	

}
