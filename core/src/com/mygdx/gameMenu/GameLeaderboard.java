package com.mygdx.gameMenu;

import java.util.Dictionary;

import com.badlogic.gdx.graphics.Texture;

public class GameLeaderboard {
	private int bestScore;
	private Texture background;
	
	public GameLeaderboard(){
		
	}
	
	public int getBestScore(){
		return bestScore;
	}
	
	public Texture getBackground(){
		return background;
	}
}
