
package main;
import menu.*;
import game.*;
import graphics.Graphics;

/**
 * controls the overall state of the game
 * splash screen, main menu, ingame, options menu
 * @author beaverl
 *
 */
public class Controller {

	enum State {
		SPLASH_SCREEN, MAIN_MENU, GAME, OPTIONS;
	}
	
	State state = State.SPLASH_SCREEN;
	SplashScreen splashScreen;
	MainMenu mainMenu;
	OptionsMenu options;
	GameController game;
	Graphics graphics;
	InputHandler input;
	
	
	public Controller() {
		graphics = new Graphics();
		splashScreen = new SplashScreen();
		mainMenu = new MainMenu();
		options = new OptionsMenu();
		game = new GameController();
		//we may want to move this elsewhere so there isnt a massive load time
		initialize();
	}
	
	public void initialize() {
		//initialize everything here
		graphics.initialize();
		game.initialize(graphics);
		splashScreen.initialize(graphics);
	}
	
	
	public void update() {
		switch (state) {
		default:
		case MAIN_MENU:
			String nextState = mainMenu.update();
			if (nextState.equals("GAME")) {
				state = State.GAME;
			} else {
				state = State.OPTIONS;
			}
			break;
		case SPLASH_SCREEN:
			if(splashScreen.update(graphics)) {
				state = State.GAME;
			}
			break;
		case GAME:
			game.update(graphics);
			break;
		case OPTIONS:
			options.update();
			break;
		
		
		
		}
	}
}
