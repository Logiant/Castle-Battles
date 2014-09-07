
package game;

import menu.GameOver;
import graphics.Graphics;

/**
 * State machine which controls a single game
 * @author beaverl
 *
 */
public class GameController {

	enum GameState {
		SETUP, CHARACTER_CREATE, GAME, GAME_OVER
	}
	
	Game game;
	GameState state = GameState.GAME;
	GameOver gameOver;
	boolean win;
	
	public GameController() {
		game = new Game(this);
		gameOver = new GameOver();
	}
	
	public void initialize(Graphics g) {
		//initialize everything here
		game.initialize(g);
		gameOver.initialize(g);
		win = false;
		state = GameState.SETUP;
	}
	
	public String update(Graphics g) {
		String cmd = "";
		switch (state) {
		default:
		case SETUP:
			state = GameState.CHARACTER_CREATE;
			//game setup. Choose map (or map seed) set dificulty (AI resource bonuses)
			break;
		case CHARACTER_CREATE:
			state = GameState.GAME;
			//update the character creation until finish or back is hit, returns int[]
			break;
		case GAME:
			String nextState = game.update(g);
			if (nextState.equals("QUIT")) {
				cmd = nextState;
			}
			break;
		case GAME_OVER:
			cmd = gameOver.update(g, win);
			break;
		}
		return cmd;
	}
	
	public void gameOver(boolean win) {
		this.win = win;
		state = GameState.GAME_OVER;
	}
}
