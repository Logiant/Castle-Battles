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
	}
	
	public void initialize() {
		//initialize everything here
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
			splashScreen.update();
			break;
		case GAME:
			game.update();
			break;
		case OPTIONS:
			options.update();
			break;
		
		
		
		}
	}
}
