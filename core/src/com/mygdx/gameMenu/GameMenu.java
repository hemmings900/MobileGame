package com.mygdx.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameController;
import com.mygdx.game.GameStates;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.CollisionController;
import com.mygdx.gamePhysics.Point;

public class GameMenu {
	private GameButton startGameButton;
	private GameButton quitGameButton;
	private GameButton leaderboardButton;
	private CollisionController collision;
	private GameController game;
	
	public GameMenu(GameController newGame){
		collision = new CollisionController();
		game = newGame;
		//Make start game button
		Sprite startGameSprite = new Sprite("menu/startGame.png");
		Point startGamePosition = new Point(20, Gdx.graphics.getHeight()-80);
		startGameButton = new GameButton(startGamePosition,startGameSprite);
		
		//Make quit game button
		Sprite quitGameSprite = new Sprite("menu/quitGame.png");
		Point quitGamePosition = new Point(20, Gdx.graphics.getHeight()-160);
		quitGameButton = new GameButton(quitGamePosition,quitGameSprite);
		
		//Make leaderboard button
		Sprite leaderboardSprite = new Sprite("menu/leaderboard.png");
		Point leaderboardPosition = new Point(20, Gdx.graphics.getHeight()-240);
		leaderboardButton = new GameButton(leaderboardPosition,leaderboardSprite);
	}
	
	public GameButton[] GetButtons(){
		return new GameButton[]{startGameButton,
								quitGameButton,
								leaderboardButton};
	}
	
	public void ButtonClick(){
		if(collision.isColliding(new Point(Gdx.input.getX(),
										   Gdx.graphics.getHeight()-Gdx.input.getY()),
								           startGameButton.getRect())){
			GameStates.State = GameStates.START;
			game.ResetGame();
		}
		else
		if(collision.isColliding(new Point(Gdx.input.getX(),
										   Gdx.graphics.getHeight()-Gdx.input.getY()),
								           quitGameButton.getRect()))
			GameStates.State = GameStates.QUIT;
		else
		if(collision.isColliding(new Point(Gdx.input.getX(),
										   Gdx.graphics.getHeight()-Gdx.input.getY()), 
										   leaderboardButton.getRect()))
			GameStates.State = GameStates.LEADERBOARD;
		else
			GameStates.State = GameStates.MENU;
		System.out.println("-------------------------");
	}
}
