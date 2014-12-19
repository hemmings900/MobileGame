package com.mygdx.game;

public class DelayCounter {
	private int delayDuration;
	private int delayLimit;
	
	public DelayCounter(int newLimit){
		delayDuration = 0;
		delayLimit = newLimit;
	}
	
	//Update counter duration, if duration is over limit, return true and set back to 0
	public boolean UpdateCounter(){
		delayDuration++;
		if (delayDuration > delayLimit){
			delayDuration = 0;
			return true;
		}else
			return false;
	}
}
