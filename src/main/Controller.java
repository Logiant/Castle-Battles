package main;
import menu.*;
import game.*;
import graphics.Graphics;

public class Controller {

	enum GameState {
		SPLASH_SCREEN, MAIN_MENU, GAME, OPTIONS;
	}
	
	GameState state = GameState.SPLASH_SCREEN;
	SplashScreen splashScreen;
	MainMenu mainMenu;
	OptionsMenu options;
	Game game;
	Graphics graphics;
	InputHandler input;
	
	
	public Controller() {
		graphics = new Graphics();
		splashScreen = new SplashScreen();
		mainMenu = new MainMenu();
		options = new OptionsMenu();
		game = new Game();
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
				state = GameState.GAME;
			} else {
				state = GameState.OPTIONS;
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
