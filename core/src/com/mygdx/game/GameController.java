package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.gameObjects.GameObject;
import com.mygdx.gameObjects.PlayerCharacter;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.CollisionController;
/*
 * Main Game Logic class, handles most activity that is required to be processed in the
 * render method.
 */
public class GameController {

	private PlayerCharacter player;
	private Point mousePosition;
	private BlockController blocks;
	private CollisionController collisions;
	private int jumpTimer;
	private int jumpTimeLimit;//Jump time limit is not based on amount of timer updates.
	
	/*
	 * Initializes game settings and values.
	 */
	public GameController() throws Exception{
		mousePosition = new Point(0,0);
		
		//Initialize player
		Texture img = new Texture("badlogic.jpg");
		Texture img2 = new Texture("playerCollision.png");
		Texture img3 = new Texture("playerJump.png");
		Sprite playerSprite = new Sprite(new Texture[]{img,img2,img3});
		player = new PlayerCharacter(0,0,20,playerSprite);
		jumpTimer = 0;
		jumpTimeLimit = 30;
		
		//Initialize Blocks
		blocks = new BlockController(10);
		collisions = new CollisionController();
		
	}
	/*
	 * Game logic handled in here. Called in main render method.
	 */
	public void updateGame(){
		mousePosition.x = Gdx.input.getX();
		mousePosition.y = ((Gdx.input.getY()-Gdx.graphics.getHeight())*-1);
		blocks.addRandomBlock(1,100);
		boolean collided = false;
		
		/*
		 * #################################################################
		 * ##				GAME OBJECT PROCESSING						  ##
		 * #################################################################
		 */
		//make sure there are blocks in the array before processing
		if (blocks.getBlocks() != null){
			blocks.MoveBlocks();
			blocks.RemoveOutsideBlocks();
			// Check if the player is touching an object
			for (GameObject block : blocks.getBlocks()){
				Rectangle rect1 = block.getRect();
				Rectangle rect2 = player.getRect();
				if(collisions.isColliding(rect1,rect2)){
					collided = true;
					}
				}	
			}
		//Player action processing
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			player.setIsJumping(true);
		}
		/*
		 * If player isn't jumping, check if player is touching any of the blocks.
		 * Change the sprite image based on state of player.
		 */
		if(player.isJumping()){
			if(jumpTimer > jumpTimeLimit){
				player.setIsJumping(false);
				jumpTimer = 0;
			}
			else{			
			player.getObjectSprite().setCurrentFrame(2);
			jumpTimer++;
			}
		}else if(collided){
			player.getObjectSprite().setCurrentFrame(1);
		}
		else{
			player.getObjectSprite().setCurrentFrame(0);
		}
		
			
			player.FollowPoint(mousePosition);
		}
	/*###################################################
	 * Setters and getters for GameController          ##
	 ###################################################*/
	public PlayerCharacter getPlayer() {
		return player;
	}

	public Point getMousePosition() {
		return mousePosition;
	}

	public BlockController getBlocks() {
		return blocks;
	}

	public CollisionController getCollisions() {
		return collisions;
	}
}

