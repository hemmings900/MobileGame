package com.mygdx.gameMenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class GameButton {
	private Rectangle buttonRect;
	private int buttonX;
	private int buttonY;
	private String buttonText;
	private boolean isPressed;
	private Color buttonColor;
	
	public GameButton(int newX,int newY,String newText,Rectangle newRect){
		buttonX = newX;
		buttonY = newY;
		buttonText = newText;
		buttonRect = newRect;		
		buttonColor = Color.LIGHT_GRAY;
	}
	/*
	 * ############################################
	 * ##	GETTERS AND SETTERS					 ##
	 * ############################################
	 */
	public Rectangle getButtonRect() {
		return buttonRect;
	}

	public void setButtonRect(Rectangle buttonRect) {
		this.buttonRect = buttonRect;
	}

	public int getButtonX() {
		return buttonX;
	}

	public void setButtonX(int buttonX) {
		this.buttonX = buttonX;
	}

	public int getButtonY() {
		return buttonY;
	}

	public void setButtonY(int buttonY) {
		this.buttonY = buttonY;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}
	
	
}
