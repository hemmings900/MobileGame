package com.mygdx.gameDebugging;

import java.util.ArrayList;

public class DebugTextHandler {
	ArrayList<DebugText> textList;
	
	//Constructor
	public DebugTextHandler(){
		textList.add(new DebugText("Mouse Position"));
		textList.add(new DebugText("Collision"));
	}
	
	//Set new Debug String
	public void UpdateDebutText(String valueName){
		
	}
	
}

	
