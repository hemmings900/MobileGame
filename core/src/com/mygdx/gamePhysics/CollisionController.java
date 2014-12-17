package com.mygdx.gamePhysics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionController {
	
	public CollisionController(){
		
	}
	//Check if any two rectangles are in a collision
	public boolean isColliding(Rectangle box1, Rectangle box2){
		//Box1 co-ords		
		//Position
		float b1x = box1.getX();
		float b1y = box1.getY();
		//End Dimensions
		float b1Width = box1.getWidth();
		float b1Height = box1.getHeight();
		//Box2 co-ords
		//Position
		float b2x = box2.getX();
		float b2y = box2.getY();
		//End Dimensions
		float b2Width = box2.getWidth();
		float b2Height = box2.getHeight();	
		
		if(b1x < b2x + b2Width &&
		   b1x + b1Width > b2x &&
		   b1y < b2y + b2Height &&
		   b1Height + b1y > b2y){
		   return true;
		} 
		else return false;		
	}
	
	public boolean isColliding(Point p, Rectangle box){
		if(p.x > box.x &&
		   p.x < box.width &&
		   p.y > box.y &&
		   p.y < box.height){
		   return true;
		}
		else return false;
	}
	
}
