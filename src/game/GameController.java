
package game;

import graphics.Graphics;

/**
 * State machine which controls a single game
 * @author beaverl
 *
 */
public class GameController {

	enum GameState {
		SETUP, CHARACTER_CREATE, GAME
	}
	
	Game game;
	GameState state= GameState.GAME;
	
	public GameController() {
		game = new Game();
	}
	
	public void initialize(Graphics g) {
		//initialize everything here
		game.initialize(g);
	}
	
	public void update(Graphics g) {
		switch (state) {
		default:
		case SETUP:
			//game setup. Choose map (or map seed) set dificulty (AI resource bonuses)
			break;
		case CHARACTER_CREATE:
			//update the character creation until finish or back is hit, returns int[]
			break;
		case GAME:
			game.update(g);
			break;
		}
	}
}
