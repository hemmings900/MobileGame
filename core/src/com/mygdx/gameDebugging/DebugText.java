package com.mygdx.gameDebugging;

public class DebugText {
	private String text;
	private String valueName;
	
	public DebugText(String newValueName){
		valueName = newValueName;
		text = "";
	}
	
	/* 
	 * #############################
	 * ## SETTERS AND GETTERS     ##
	 * #############################
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
}
