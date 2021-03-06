package com.mygdx.gameMenu;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.Point;

public class GameButton {
	private Sprite buttonSprite;
	private Point buttonLocation;
	private boolean isPressed;
	
	public GameButton(Point newLocation, Sprite newSprite){
		buttonSprite = newSprite;
		buttonLocation = newLocation;
	}
	/*
	 * Gets rectangle of button (This method is also found in Game object)
	 */
	public Rectangle getRect(){
		Rectangle rect = new Rectangle(buttonLocation.x,buttonLocation.y,
									   buttonSprite.getImage().getWidth()+buttonLocation.x,
									   buttonSprite.getImage().getHeight()+buttonLocation.y);
		return rect;
	}
	/*
	 * ############################################
	 * ##	GETTERS AND SETTERS					 ##
	 * ############################################
	 */

	public Sprite getButtonSprite() {
		return buttonSprite;
	}

	public void setButtonSprite(Sprite buttonSprite) {
		this.buttonSprite = buttonSprite;
	}

	public Point getButtonLocation() {
		return buttonLocation;
	}

	public void setButtonLocation(Point buttonLocation) {
		this.buttonLocation = buttonLocation;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	
	
}
