package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameObjects.GameObject;
import com.mygdx.gameObjects.PlayerCharacter;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.CollisionController;
/*
 * Main Game Logic class, handles most activity that is required to be processed in the
 * render method.
 */
public class GameController {
	private SpriteBatch batch;
	private PlayerCharacter player;
	private Point mousePosition;
	private BitmapFont font;
	private BlockController blocks;
	private int blockCount;
	//private ShapeRenderer shapes;
	private CollisionController collisions;
	private float jumpTimer;
	private float jumpTimeLimit;
	
	/*
	 * Initializes game settings and values.
	 */
	public GameController() throws Exception{
		mousePosition = new Point(0,0);
		jumpTimer = 0;
		jumpTimeLimit = 1;
		//Initialize rendering tools
		batch = new SpriteBatch();
		//shapes = new ShapeRenderer();
		font = new BitmapFont();	
		
		//Initialize player
		Texture img = new Texture("badlogic.jpg");
		Texture img2 = new Texture("playerCollision.png");
		Texture img3 = new Texture("playerJump.png");
		Sprite playerSprite = new Sprite(new Texture[]{img,img2,img3});
		player = new PlayerCharacter(0,0,20,playerSprite);
		
		//Initialize Blocks
		blocks = new BlockController(10);
		blockCount = 0;
		collisions = new CollisionController();
		
	}
	
	
	/*
	 * Game logic handled in here. Called in main render method.
	 */
	public void updateGame(){
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			player.setIsJumping(true);
		}
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
			/*
			 * If player isn't jumping, check if player is touching any of the blocks.
			 * Change the sprite image based on state of player.
			 */
			if(player.isJumping()){
				player.getObjectSprite().setCurrentFrame(2);
			}else if(collided){
				player.getObjectSprite().setCurrentFrame(1);
			}
			else{
				player.getObjectSprite().setCurrentFrame(0);
			}
			player.FollowPoint(mousePosition);
		}
	
	/*
	 * Does all the drawing. Called in main render method.
	 */
	public void DrawGame(){
		Sprite playerSprite = player.getObjectSprite();
		if (player.isJumping()){
			if (jumpTimer > jumpTimeLimit){
				player.setIsJumping(false);
				jumpTimer = 0;
			}else
				jumpTimer += Gdx.graphics.getDeltaTime();			
		}
			
		//BEGIN BATCH#################
		batch.begin();//##############
		//############################	
		
		font.draw(batch, "Deleted Blocks"+blockCount, 0, 60);
		font.draw(batch, "MousePosition " + mousePosition.x +":"+ mousePosition.y, 0, 40);
		//Draw Player
		batch.draw(playerSprite.getCurrentFrame(), player.getObjectX(),player.getObjectY());
		//goes through all blocks and draws them. Makes sure that there are blocks to begin with.
		if (blocks.getBlocks() != null){
		for (int i = 0; i < blocks.getBlockCount(); i++){
			Texture blockImage = blocks.getBlock(i).getObjectSprite().getImage();
			int blockX = blocks.getBlock(i).getObjectX();
			int blockY = blocks.getBlock(i).getObjectY();
			batch.draw(blockImage,blockX,blockY);
			
			/*##########################################################
			 *##	Draws collision rectangles                        ##
			 *##########################################################
			shapes.setColor(new Color(Color.CYAN));
			shapes.setAutoShapeType(true);
			shapes.begin();
			Rectangle reckt = blocks.getBlock(i).getRect();
			Rectangle reckt2 = player.getRect();
			shapes.rect(reckt.x,reckt.y,reckt.width,reckt.height);
			shapes.rect(reckt2.x,reckt2.y,reckt2.width,reckt2.height);
			shapes.end();	
			*/		
			}			
		}		
		//END BATCH###################
		batch.end();//################
		//############################
	}
}
