package com.mygdx.gamePhysics;

public class Point{
	public int x;
	public int y;
	
	public Point(int newX,int newY){
		x = newX;
		y = newY;
	}
	
	public Point(Point newPoint){
		x = newPoint.x;
		y = newPoint.y;
	}

}
