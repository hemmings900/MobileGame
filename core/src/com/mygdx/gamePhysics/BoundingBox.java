package com.mygdx.gamePhysics;

import com.badlogic.gdx.math.Rectangle;
/*
 * Object that holds bounding box properties.
 * Mainly used as storage for points to be operated 
 * on in collision controller methods.
 */
public class BoundingBox {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	//Constructor defining int values for x-2 and y-2.
	public BoundingBox(int startX1, int startY1, int startX2, int startY2){
		x = startX1;
		y = startY1;
		width = startX2;
		height = startY2;
	}
	
	
	//Constructor defining int values for point1 and point 2
	public BoundingBox(Point startP1, Point startP2){
		x = startP1.x;
		y = startP1.y;
		width = startP2.x;
		height = startP2.y;
	}
	
	/*
	 * Getters and Setters for both points of the bounding rectangle.
	 */
	
	public Rectangle getAsRectangle(){
		Rectangle rect = new Rectangle(x,y,width,height);
		return rect;
	}
	
	public Point getP1(){
		Point p1 = new Point(x,y);
		return p1;
	}
	
	public Point getP2(){
		Point p2 = new Point(width,height);
		return p2;
	}
	
	public void setP1(Point newP1){
		x = newP1.x;
		y = newP1.y;
	}
	
	public void setP2(Point newP2){
		width = newP2.x;
		height = newP2.y;
	}
	
	/*
	 * Getters and setters for individual X and Y co-ordinates of each point.
	 */
	public int getX() {
		return x;
	}

	public void setX(int x1) {
		this.x = x1;
	}

	public int getY() {
		return y;
	}

	public void setY(int y1) {
		this.y = y1;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int x2) {
		this.width = x2;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int y2) {
		this.height = y2;
	}
	
	/*
	 * ToString Override
	 */
	
	public String ToString(){
		String tString = "X: " + x + " Y: " + y + " Width: " + width + " Height: " + height;
		return tString;
	}
	
}
