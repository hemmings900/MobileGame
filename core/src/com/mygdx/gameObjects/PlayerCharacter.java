package com.mygdx.gameObjects;
/*
 * Class that represents the player's character, follows cursor based on a specific speed;
 */
public class PlayerCharacter extends GameObject{
    
	private boolean jumping;
    public PlayerCharacter(int startX, int startY, int startMoveSpeed,Sprite startSprite){
    	super(startX, startY, startMoveSpeed, startSprite);
    	jumping = false;
    } 
    
    //Set and get jumping status
    public boolean isJumping(){
    	return jumping;
    }
    
    public void setIsJumping(boolean newJumping){
    	jumping = newJumping;
    }
}
