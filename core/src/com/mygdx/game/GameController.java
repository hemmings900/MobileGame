package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameObjects.GameObject;
import com.mygdx.gameObjects.PlayerCharacter;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.CollisionController;
import com.mygdx.gamePhysics.Point;
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
	//delays	
	private DelayCounter jumpDelay;
	private DelayCounter blockSpawnDelay;
	private DelayCounter gameOverDelay;
	private DelayCounter tutorialDelay;
	
	//tutorial
	private String tutorial;
	private boolean isTutorial;
	//Misc
	private long gameScore;
	private boolean gameOver;
	private Texture background;
	private Texture arrow;
	private Point arrowPos;
	public Texture getArrow() {
		return arrow;
	}
	public Point getArrowPos() {
		return arrowPos;
	}
	private int backgroundScroll;
	private int gameSpeed;
	private int startGameSpeed;
	
	/*
	 * Initializes game settings and values.
	 */
	public GameController(int newGameSpeed) throws Exception{
		
		mousePosition = new Point(0,0);
		gameScore = 0;
		gameSpeed = newGameSpeed;
		startGameSpeed = newGameSpeed;
		//arrow
		arrow = new Texture("gameObjects/arrow.png");
		arrowPos = new Point(0,0);
		//Initialize Background
		background = new Texture("backgrounds/gameBackground.bmp");
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		backgroundScroll = 0;
		
		//Initialize player
		Texture img = new Texture("gameObjects/player.png");
		Texture img2 = new Texture("gameObjects/playerCollision.png");
		Texture img3 = new Texture("gameObjects/playerJump.png");
		Sprite playerSprite = new Sprite(new Texture[]{img,img2,img3});
		player = new PlayerCharacter(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-150,gameSpeed+5,playerSprite);
		
		//Initialize delay variables		
		jumpDelay = new DelayCounter(30);
		blockSpawnDelay = new DelayCounter(100);
		gameOverDelay = new DelayCounter(50);
		tutorialDelay = new DelayCounter(150);
		
		//Tutorial
		tutorial = "Drag the arrow to move.";
		isTutorial = true;
		
		//Initialize Blocks
		blocks = new BlockController(gameSpeed,2);
		collisions = new CollisionController();	
	}
	
	
	/*
	 * Game logic handled in here. Called in main render method.
	 */
	public void updateGame(){
		//Set mouse/touch position
		mousePosition.x = Gdx.input.getX();
		mousePosition.y = ((Gdx.input.getY()-Gdx.graphics.getHeight())*-1);
		//Set arrow location
		arrowPos = new Point(Gdx.input.getX()-arrow.getWidth()/2,100);
		
		//Background
		backgroundScroll += gameSpeed;
		if(backgroundScroll > background.getHeight())
			backgroundScroll = 0;
		
		boolean collided = false;
		
		/*
		 * #################################################################
		 * ##				GAME OBJECT PROCESSING						  ##
		 * #################################################################
		 */
		//Tutorial
		if (tutorialDelay.UpdateCounter())
			isTutorial = false;			
		
		//If time interval is correct, make a row of blocks.
		if (blockSpawnDelay.UpdateCounter()){
			blocks.addRandomBlocks();
			if(!gameOver)
				gameScore++;
		}
		
		//Game over handling
		if(gameOver){
			if (gameScore > GameStates.GameScore)
				GameStates.GameScore = gameScore;				
			gameSpeed=0;			
			if(gameOverDelay.UpdateCounter()){
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
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
			player.setIsJumping(true);
		}
		/*
		 * If player isn't jumping, check if player is touching any of the blocks.
		 * Change the sprite image based on state of player.
		 */
		if(player.isJumping()){
			if(jumpDelay.UpdateCounter())
				player.setIsJumping(false);
			else		
				player.getObjectSprite().setCurrentFrame(2);
		} else if(collided){
			player.getObjectSprite().setCurrentFrame(1);
			gameOver = true;
		}
		else{
			player.getObjectSprite().setCurrentFrame(0);
		}		
		
		//Set position of player
		Point playerPoint = new Point(mousePosition.x-player.getObjectSprite().getCenterPx().x,
									  Gdx.graphics.getHeight()-100);
		if(!gameOver)
			player.FollowPoint(playerPoint);
	}
	
	/*#############################################
	 *##    RESET GAME							 ##
	 *#############################################
	 */
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
		jumpDelay = new DelayCounter(30);
		blockSpawnDelay = new DelayCounter(100);
		gameOverDelay = new DelayCounter(50);
		tutorialDelay = new DelayCounter(150);
		
		//Initialize Blocks
		blocks = new BlockController(gameSpeed,blocks.getBlockChance());
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
	
	public String getTutorial(){
		return tutorial;
	}
	
	public Boolean getIsTutorial(){
		return isTutorial;
	}
}

