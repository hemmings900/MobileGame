package com.mygdx.game;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.gameMenu.GameLeaderboard;
import com.mygdx.gameObjects.GameObject;
import com.mygdx.gameObjects.PlayerCharacter;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.CollisionController;
/*
 * Main Game Logic class, handles most activity that is required to be processed in the
 * render method.
 */
public class GameController {

	//Game Objects
	private PlayerCharacter player;
	private Point mousePosition;
	private BlockController blocks;
	private CollisionController collisions;
	//Timing variables
	private int jumpTimer;
	private int jumpTimeLimit;//Jump time limit is not based on amount of timer updates.
	private int blockSpawnTimer;
	private int blockSpawnTimeLimit;
	private int gameOverTimer;
	private int gameOverTimeLimit;
	//Misc
	private long gameScore;
	private boolean gameOver;
	private Texture background;
	private int backgroundScroll;
	private int gameSpeed;
	private int startGameSpeed;
	private GameLeaderboard scoreboard;
	
	/*
	 * Initializes game settings and values.
	 */
	public GameController(int newGameSpeed) throws Exception{
		mousePosition = new Point(0,0);
		gameScore = 0;
		gameSpeed = newGameSpeed;
		startGameSpeed = newGameSpeed;
		scoreboard = new GameLeaderboard();
		
		//Initialize Background
		background = new Texture("backgrounds/gameBackground.bmp");
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		backgroundScroll = 0;
		
		//Initialize player
		Texture img = new Texture("gameObjects/player.png");
		Texture img2 = new Texture("gameObjects/playerCollision.png");
		Texture img3 = new Texture("gameObjects/playerJump.png");
		Sprite playerSprite = new Sprite(new Texture[]{img,img2,img3});
		player = new PlayerCharacter(0,Gdx.graphics.getHeight()-100,3,playerSprite);
		
		//Initialize delay variables
		jumpTimer = 0;
		jumpTimeLimit = 30;
		blockSpawnTimer = 0;
		blockSpawnTimeLimit = 50;		
		gameOverTimer = 0;
		gameOverTimeLimit = 50;
		
		//Initialize Blocks
		blocks = new BlockController(gameSpeed,3);
		collisions = new CollisionController();
		
	}
	/*
	 * Game logic handled in here. Called in main render method.
	 */
	public void updateGame() throws FileNotFoundException, UnsupportedEncodingException{
		mousePosition.x = Gdx.input.getX();
		mousePosition.y = ((Gdx.input.getY()-Gdx.graphics.getHeight())*-1);
		backgroundScroll += gameSpeed;
		if(backgroundScroll > background.getHeight())
			backgroundScroll = 0;
		
		boolean collided = false;
		
		/*
		 * #################################################################
		 * ##				GAME OBJECT PROCESSING						  ##
		 * #################################################################
		 */
		//Block spawn timer
		blockSpawnTimer++;
		//If time interval is correct, make a row of blocks.
		if (blockSpawnTimer > blockSpawnTimeLimit){
			blocks.addRandomBlocks();
			blockSpawnTimer = 0;
			if(!gameOver)
				gameScore++;
		}
		//Game over timer
		if(gameOver){
			if (gameScore > GameStates.GameScore)
				GameStates.GameScore = gameScore;	
			
			gameSpeed=0;
			gameOverTimer++;			
			if(gameOverTimer>gameOverTimeLimit){
				GameStates.State = GameStates.MENU;			
			}
				
		}
			
		//make sure there are blocks in the array before processing
		if (blocks.getBlocks() != null){
			if(!gameOver)
				blocks.MoveBlocks();
			
			blocks.RemoveOutsideBlocks();
			// Check if the player is touching an object
			for (GameObject block : blocks.getBlocks()){
				Rectangle rect1 = block.getRect();
				Rectangle rect2 = player.getRect();
				if(collisions.isColliding(rect1,rect2)){
					collided = true;
					gameOver = true;
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
			gameOver = true;
		}
		else{
			player.getObjectSprite().setCurrentFrame(0);
		}			
		Point playerPoint = new Point(mousePosition.x,Gdx.graphics.getHeight()-100);
		if(!gameOver)
			player.FollowPoint(playerPoint);
	}
	
	public void ResetGame(){
		gameOver = false;
		gameSpeed = startGameSpeed;
		mousePosition = new Point(0,0);
		gameScore = 0;		
		//Initialize Background
		background = new Texture("backgrounds/gameBackground.bmp");
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		backgroundScroll = 0;
		
		//Initialize player
		player = new PlayerCharacter(0,Gdx.graphics.getHeight()-100,3,player.getObjectSprite());
		
		//Initialize delay variables
		jumpTimer = 0;
		jumpTimeLimit = 30;
		blockSpawnTimer = 0;
		blockSpawnTimeLimit = 50;		
		gameOverTimer = 0;
		gameOverTimeLimit = 50;
		
		//Initialize Blocks
		blocks = new BlockController(gameSpeed,3);
		collisions = new CollisionController();
		
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
	
	public boolean getGameOver(){
		return gameOver;
	}
	public Texture getBackground() {
		return background;
	}
	public int getBackgroundScroll() {
		return backgroundScroll;
	}
	public long getGameScore() {
		return gameScore;
	}
	public int getGameSpeed(){
		return gameSpeed;
	}
}

