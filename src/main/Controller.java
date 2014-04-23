
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
		SPLASH_SCREEN, MAIN_MENU, CAMPAIGN, GAME, OPTIONS;
	}

	State state = State.SPLASH_SCREEN;
	SplashScreen splashScreen;
	MainMenu mainMenu;
	OptionsMenu options;
	GameController game;
	Campaign campaign;
	Graphics graphics;	

	public Controller() {
		graphics = new Graphics();
		splashScreen = new SplashScreen();
		mainMenu = new MainMenu();
		options = new OptionsMenu();
		game = new GameController();
		campaign = new Campaign();
		//we may want to move this elsewhere so there isnt a massive load time
		initialize();
	}

	public void initialize() {
		//initialize everything here
		graphics.initialize();
		splashScreen.initialize(graphics); //moved this to first, since we want it ready ASAP
		InputHandler.initialize();
		game.initialize(graphics);
		campaign.initialize(graphics);
		options.initialize(graphics);
		mainMenu.initialize(graphics);
	}


	public void update() {
		InputHandler.update();
		switch (state) {
		default:
		case MAIN_MENU:
			String nextState = mainMenu.update(graphics);
			if (nextState != null) {
				if(nextState.equals("CAMPAIGN")) {
					state = State.CAMPAIGN;
				} else if (nextState.equals("GAME")) {
					state = State.GAME;
				} else if (nextState.equals("OPTIONS")){
					state = State.OPTIONS;
				} else if (nextState.equals("QUIT")){
					Driver.close();
				}
			}
			break;
		case SPLASH_SCREEN:
			if(splashScreen.update(graphics)) {
				state = State.MAIN_MENU;
			}
			break;
		case CAMPAIGN: 
			nextState = campaign.update(graphics);
			if(nextState != null) {
				if(nextState.equals("MAIN_MENU"))
					state = State.MAIN_MENU;
			}
			break;
		case GAME:
			game.update(graphics);
			break;
		case OPTIONS:
			String command = options.update(graphics);
			if (command != null && command.equals("BACK")) {
				state = State.MAIN_MENU;
			}
			break;



		}
	}
}
