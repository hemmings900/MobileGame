package com.mygdx.gameMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameStates;

public class GameLeaderboard {
	private Texture background;
	private PrintWriter writer;
	Preferences prefs;
	
	public GameLeaderboard(){
		prefs = Gdx.app.getPreferences("appPrefs");
		GameStates.GameScore = prefs.getLong("SCORE");
	}
	
	
	public void setBestScore(){
		prefs.putLong("SCORE", GameStates.GameScore);
	}
	
	public Texture getBackground(){
		return background;
	}
}
