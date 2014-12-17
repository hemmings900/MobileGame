package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.gameMenu.GameLeaderboard;
import com.mygdx.gameMenu.GameMenu;

public class MyGdxGame extends ApplicationAdapter {

	private GameController game;
	private GameTimer timer;
	private DrawingController draw;
	private GameMenu menu;
	private GameLeaderboard score;
	
	//Initialize Game
	public void create () {
		//Set applicaiton screen size
		Gdx.graphics.setDisplayMode(240, 320, false);
		//Make new Game, Game logic and drawing handled in this class.
		try {
			game = new GameController(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Make new Game Menu
		menu = new GameMenu(game);
		try {
			score = new GameLeaderboard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		draw = new DrawingController();
		timer = new GameTimer(0.0166);
		
		//Set Beginning Game State
		GameStates.State = GameStates.MENU;
	}

	@Override
	public void render () {
//		System.out.println(GameStates.State);
		//GL stuff.
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Render menu if game is in menu state
		if(GameStates.State == GameStates.MENU)
			draw.DrawGameMenu(menu);
		//Render and update game if game is in START state
		if(GameStates.State == GameStates.START){
			try {
				timer.UpdateGameDuration(game);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			draw.DrawGame(game);
		}
		//Render scoreboard
		if(GameStates.State == GameStates.LEADERBOARD){
			draw.DrawScore(score);
			if(Gdx.input.isKeyPressed(Keys.ESCAPE))
				GameStates.State = GameStates.MENU;
				
		}
		
		//Close application if game is in QUIT state
		if(GameStates.State == GameStates.QUIT){
			System.exit(0);
			
		}
			
	}
}
