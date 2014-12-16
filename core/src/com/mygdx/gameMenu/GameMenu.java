package com.mygdx.gameMenu;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameStates;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.CollisionController;

public class GameMenu {
	private GameButton startGameButton;
	private GameButton quitGameButton;
	private GameButton leaderboardButton;
	private CollisionController collision;
	
	public GameMenu(){
		collision = new CollisionController();
		//Make start game button
		Sprite startGameSprite = new Sprite("startGame.png");
		Point startGamePosition = new Point(20, Gdx.graphics.getHeight()-80);
		startGameButton = new GameButton(startGamePosition,startGameSprite);
		
		//Make quit game button
		Sprite quitGameSprite = new Sprite("quitGame.png");
		Point quitGamePosition = new Point(20, Gdx.graphics.getHeight()-160);
		quitGameButton = new GameButton(quitGamePosition,quitGameSprite);
		
		//Make leaderboard button
		Sprite leaderboardSprite = new Sprite("leaderboard.png");
		Point leaderboardPosition = new Point(20, Gdx.graphics.getHeight()-240);
		leaderboardButton = new GameButton(leaderboardPosition,leaderboardSprite);
	}
	
	public GameButton[] GetButtons(){
		return new GameButton[]{startGameButton,
								quitGameButton,
								leaderboardButton};
	}
	
	public void ButtonClick(Point cursorPosition){
		if(collision.isColliding(cursorPosition,startGameButton.getRect()))
			GameStates.State = GameStates.START;
		else
		if(collision.isColliding(cursorPosition,quitGameButton.getRect()))
			GameStates.State = GameStates.QUIT;
		else
		if(collision.isColliding(cursorPosition, leaderboardButton.getRect()))
			GameStates.State = GameStates.LEADERBOARD;
		else
			GameStates.State = GameStates.MENU;
		
	}
}
