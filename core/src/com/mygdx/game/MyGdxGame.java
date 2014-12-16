package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.gameMenu.GameMenu;

public class MyGdxGame extends ApplicationAdapter {

	private GameController game;
	private GameTimer timer;
	private DrawingController draw;
	private GameMenu menu;
	
	//Initialize Game
	public void create () {
		//Set applicaiton screen size
		Gdx.graphics.setDisplayMode(240, 320, false);
		//Make new Game Menu
		menu = new GameMenu();
		//Make new Game, Game logic and drawing handled in this class.
		try {
			game = new GameController();
		} catch (Exception e) {
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
			timer.UpdateGameDuration(game);
			draw.DrawGame(game);
		}
		//TO DO IMPLEMENT SCOREBOARD HERE
		
		//Close application if game is in QUIT state
		if(GameStates.State == GameStates.QUIT){
			System.exit(0);
			
		}
			
	}
}
