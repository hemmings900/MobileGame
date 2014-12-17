package com.mygdx.gameMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Dictionary;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameStates;

public class GameLeaderboard {
	private Texture background;
	private PrintWriter writer;
	
	public GameLeaderboard() throws IOException{
		try {
	    		BufferedReader br = new BufferedReader(new FileReader("score.txt"));
	    		GameStates.GameScore = Long.parseLong(br.readLine(),10);
				br.close();
			} catch (IOException e) {
				throw e;
			} 
	}
	
	
	public void setBestScore() throws FileNotFoundException, UnsupportedEncodingException{
		writer = new PrintWriter("score.txt", "UTF-8");
		writer.println(Long.toString(GameStates.GameScore));
		writer.close();
	}
	
	public Texture getBackground(){
		return background;
	}
}
