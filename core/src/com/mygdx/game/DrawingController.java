package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameObjects.PlayerCharacter;
import com.mygdx.gameObjects.Sprite;

public class DrawingController {
	private SpriteBatch batch;
	private BitmapFont font;
	//private ShapeRenderer shapes;
	//Constructor
	public DrawingController(){
		font = new BitmapFont();
		batch = new SpriteBatch();
	}
	
	public void DrawGame(GameController game){
		PlayerCharacter player = game.getPlayer();
		Sprite playerSprite = player.getObjectSprite();
		Point mousePosition = game.getMousePosition();
		BlockController blocks = game.getBlocks();
//		if (player.isJumping()){
//			if (jumpTimer > jumpTimeLimit){
//				player.setIsJumping(false);
//				jumpTimer = 0;
//			}else
//				jumpTimer += Gdx.graphics.getDeltaTime();			
//		}
			
		//BEGIN BATCH#################
		batch.begin();//##############
		//############################	
		
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
