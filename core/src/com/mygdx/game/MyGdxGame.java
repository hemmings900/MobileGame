package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {

	private GameController game;
	private GameTimer timer;
	public void create () {
		//Make new Game object. Game logic and drawing handled in this class.
		try {
			game = new GameController();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer = new GameTimer(0.0166);
	}

	@Override
	public void render () {
		//GL stuff.
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Update game################### 		
		game.DrawGame();
				
		timer.UpdateGameDuration(game);
	}
}
