package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/*
 * Class handles timing intervals
 */
public class GameTimer {
	private double intervalLimit;//How long to wait until timer ticks over.
	private double intervalDuration; //How long it's been since last tick.
	private double customDuration;
	
	public GameTimer(double newIntervalLimit){
		intervalLimit = newIntervalLimit;
		intervalDuration = 0;
		customDuration = 0;
	}
	
	/*
	 * Add time between frames to interval duration.
	 * If duration is over the limit, reset duration to Zero.
	 */
	
	//Update game
	public void UpdateGameDuration(GameController game){		
		double deltaTime = Gdx.graphics.getDeltaTime();//Time between last frame and current frame.
		
		//if current length of interval is longer than the limit set, reset interval.
		if (intervalDuration > intervalLimit){
			intervalDuration = 0;
			game.updateGame();
		}		
		intervalDuration += deltaTime;
		customDuration += deltaTime;
		//System.out.println(Double.toString(intervalDuration));
	}
			
	public double getIntervalDuration(){
		return intervalDuration;
	}
	
	public void resetCustomDuration(){
		customDuration = 0;
	}
	
	public double getCustomDuration(){
		return customDuration;
	}
	
	
	
}
