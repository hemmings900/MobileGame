package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameMenu.GameButton;
import com.mygdx.gameMenu.GameLeaderboard;
import com.mygdx.gameMenu.GameMenu;
import com.mygdx.gameObjects.PlayerCharacter;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.Point;

public class DrawingController {
	private SpriteBatch batch;
	private SpriteBatch bgBatch;
	private BitmapFont font;
	

	//Constructor
	public DrawingController(){
		font = new BitmapFont();
		batch = new SpriteBatch();
		bgBatch = new SpriteBatch();

	}
	public void DrawGameMenu(GameMenu menu){
		GameButton[] buttonsToDraw = menu.GetButtons();		
		//BEGIN BATCH#################
		batch.begin();//##############
	    //############################
		
		//Draw each button in GameButtons array
		for(int i = 0; i < buttonsToDraw.length; i++){
			GameButton button = buttonsToDraw[i];
			batch.draw(button.getButtonSprite().getCurrentFrame(),
					   button.getButtonLocation().x,
					   button.getButtonLocation().y);
		}
		
		//END BATCH###################
		batch.end();//################
		//############################
		//System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
		//#AD HOC CODE WARNING TODO:Sort out input class#		
		if(Gdx.input.isButtonPressed(Buttons.LEFT))
			menu.ButtonClick();
	}
	public void DrawGame(GameController game){
		PlayerCharacter player = game.getPlayer();
		Sprite playerSprite = player.getObjectSprite();
		BlockController blocks = game.getBlocks();
		Texture background = game.getBackground();
		//####################################
		//############BATCHES#################
		//####################################
		//Draw background
		bgBatch.begin();
		Point bg = new Point(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	    bgBatch.draw(background,0,0,0,game.getBackgroundScroll(),bg.x,bg.y);
		bgBatch.end();
		//Draw Game
		batch.begin();	
		font.draw(batch, "Score:"+game.getGameScore(), 0, Gdx.graphics.getHeight()-20);
		
		//Draw Player
		batch.draw(playerSprite.getCurrentFrame(), player.getObjectX(),player.getObjectY());
		//goes through all blocks and draws them. Makes sure that there are blocks to begin with.
		if (blocks.getBlocks() != null){
		for (int i = 0; i < blocks.getBlockCount(); i++){
			Texture blockImage = blocks.getBlock(i).getObjectSprite().getImage();
			int blockX = blocks.getBlock(i).getObjectX();
			int blockY = blocks.getBlock(i).getObjectY();
			batch.draw(blockImage,blockX,blockY);	
			}			
		}		
		//END BATCH###################
		batch.end();//################
		//############################
	}
	
	public void DrawScore(GameLeaderboard score){
		Point center = new Point(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		batch.begin();		
			font.draw(batch,"High Score: " + GameStates.GameScore,50,center.y);
		batch.end();
	}
}
