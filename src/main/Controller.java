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
		
		initialize();
	}
	
	public void initialize() {
		graphics.initialize();
		splashScreen.initialize();
		mainMenu.initialize();
		options.initialize();
		game.initialize();
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
			state = State.MAIN_MENU;
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
