package com.mygdx.gameObjects;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gamePhysics.BoundingBox;

/*
 * 
 */
public abstract class GameObject {
	private int objectX;
	private int objectY;
	private Sprite objectSprite;
	private int objectSpeed;
	//private BoundingBox boundingBox;
	
	public GameObject(int startX, int startY, int startMoveSpeed,Sprite startSprite){
		objectX = startX;
		objectY = startY;
		objectSpeed = startMoveSpeed;
		setObjectSprite(startSprite);
		//makeDefaultBoundingBox();
	}
	
	//Run this when the player collides with object.
	public void CollideWithObject(){}
	
	/*
	 * ################################################
	 * ##             Movement Logic                 ##
	 * ################################################
	 */
	public void FollowPoint(Point pointToFollow){
      	//See how how many pixels away from point the player is.
    	int differenceX = pointToFollow.x - objectX;
    	int differenceY = pointToFollow.y - objectY;
    	//If the difference is a negative value change to a positive value.
    	if (differenceX < 0){
    		differenceX *= -1;
    	}
    	if (differenceY < 0){
    		differenceY *= -1;
    	}
    	
    	if (differenceX < objectSpeed){
    		objectX = pointToFollow.x;
    	}
    	
    	if (differenceY < objectSpeed){
    		objectY = pointToFollow.y;
    	}
    	
    	//Check if X location is lesser or greater than the point, move if either.
    	if (pointToFollow.x < objectX)
    		setObjectX(objectX-objectSpeed);
    	else if (pointToFollow.x > objectX)
    		setObjectX(objectX+objectSpeed);
    	//Check if Y location is lesser or greater than the point, move if either.
    	if (pointToFollow.y < objectY)
    		setObjectY(objectY-objectSpeed);
    	else if (pointToFollow.y > objectY)
    		setObjectY(objectY+objectSpeed);      	
    	//updateBoundingBoxPosition();
    }
	
	/*
	 * #############################################################
	 * #              BOUNDING BOX OPERATIONS					   #
	 * #############################################################
	 */
	
	//Get a rectangle based on dimensions of image
		public Rectangle getRect(){
			Rectangle rect = new Rectangle(objectX,objectY,
					objectSprite.getImage().getWidth(),objectSprite.getImage().getHeight());
			return rect;
		}
	//Make bounding Box based on default Sprite Size
//	private void makeDefaultBoundingBox(){
//			Point p1 = new Point(objectX,objectY);
//			Point p2 = new Point(objectX + objectSprite.getImage().getWidth(), 
//								 objectY + objectSprite.getImage().getHeight());
//			boundingBox = new BoundingBox(p1,p2);
//	}
	
	//Move boundingBox
//	private void updateBoundingBoxPosition(){
//		boundingBox.setP1(new Point(objectX,objectY));
//		boundingBox.setP2(new Point(objectX+boundingBox.getWidth(),
//									objectY+boundingBox.getHeight()));
//	}

	//Get and set Bounding Box
	
	//Make bounding Box based on points specified
//	public void SetBoundingBox(Point p1, Point p2){
//		boundingBox = new BoundingBox(p1,p2);
//	}
	
	//Return current Bounding Box
//	public BoundingBox getBoundingBox(){
//		return boundingBox;
//	}

		
	/*###################################################
	 * Setters and getters for GameObject properties.#####
	 ###################################################*/

//Get and set postion values based on X and Y location;
	public int getObjectX() {
		return objectX;
	}

	public void setObjectX(int objectX) {
		this.objectX = objectX;
//		updateBoundingBoxPosition();
	}

	public int getObjectY() {
		return objectY;
	}

	public void setObjectY(int objectY) {
		this.objectY = objectY;
//		updateBoundingBoxPosition();
	}
//Get and set movement speed
	public int getObjectMoveSpeed() {
		return objectSpeed;
	}

	public void setObjectMoveSpeed(int objectMoveSpeed) {
		this.objectSpeed = objectMoveSpeed;
	}
//Get and set sprite used
	public Sprite getObjectSprite() {
		return objectSprite;
	}

	public void setObjectSprite(Sprite objectSprite) {
		this.objectSprite = objectSprite;
	}	
}
