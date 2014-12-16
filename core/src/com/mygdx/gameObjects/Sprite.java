package com.mygdx.gameObjects;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sprite {
	private Texture[] images;
	private int currentFrame = 0;
	
	/*
	 * ######################################################################
	 * ##   	SPRITE CONSTRUCTORS                                        ##
	 * ######################################################################
	 */
	//Make new Sprite using location of image file
	public Sprite(String newSpriteLocation){
		images = new Texture[1];
		images[0] = new Texture(newSpriteLocation);
	}
	
	//Make new Sprite using texture object
	public Sprite(Texture newImage){
		images = new Texture[1];
		images[0] = newImage;
	}
	
	//Make new array of textures using array of image locations
	public Sprite(String[] newSpriteLocationArray) throws Exception{
		images = new Texture[newSpriteLocationArray.length];
		for (int i = 0; i < images.length; i++){
			images[i] = new Texture(newSpriteLocationArray[i]);
		}
		checkSizes();

	}
	
	//Make new array of textures using array of textures
	public Sprite(Texture[] newImageArray) throws Exception{
		images = newImageArray;
		checkSizes();
	}
	
	/*
	 * ################################################
	 * #             Other Operations                 #
	 * ################################################
	 */
	

	//Find center pixel of the sprite for location purposes.
	public Point getCenterPx(){
		int centerX = images[0].getWidth()/2;
		int centerY = images[0].getHeight()/2;
		Point centerPx = new Point(centerX,centerY);
		return centerPx;
	}
	/*
	 * Return sprite. If no arguments are given when calling for the sprite the first 
	 * texture in the array will be returned.
	 */
	public Texture getImage(){
		return images[0];		
	}
	
	public Texture getImage(int imageIndex){
		return images[imageIndex];
	}
	
	//Get and set current frame
	public Texture getCurrentFrame(){
		return images[currentFrame];
	}
	
	public void setCurrentFrame(int newFrameIndex){
		currentFrame = newFrameIndex;
	}
	/*
	 * Check all textures in array that they are of same dimensions (size is referenced by first image)
	 * if texture dimensions differ, throw exception.
	 */
	private void checkSizes() throws Exception{
		int width = images[0].getWidth();
		int height = images[0].getHeight();
		//i = 1 because first image is referenced.
		for (int i = 1; i < images.length; i++){
			if ((images[i].getWidth() != width) || (images[i].getHeight() != height)){
				throw new Exception("Textures sizes are imcompatible");
			}				
		}
	}	
	
}
