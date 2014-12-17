package com.mygdx.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameStates;

public class GameLeaderboard {
	private Texture background;
	Preferences prefs;
	
	public GameLeaderboard(){
		prefs = Gdx.app.getPreferences("appPrefs");
		GameStates.GameScore = prefs.getLong("SCORE");
	}
	
	
	public void setBestScore(){
		prefs.putLong("SCORE", GameStates.GameScore);
		prefs.flush();
	}
	
	public Texture getBackground(){
		return background;
	}
}
