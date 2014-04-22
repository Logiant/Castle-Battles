package menu;

import main.Driver;
import graphics.Graphics;
import graphics.Rect;

/**
 * The game's splash screen
 * An image with a possible loading bar/audio and/or a simple animation
 * @author beaverl
 *
 */
public class SplashScreen {

	private int SplashId;
	Graphics graphics;
	
	public void initialize() {
		graphics = new Graphics();
		graphics.initialize();
		SplashId = graphics.loadImage("Splashscreen");
	}
	
	public boolean update() {
		boolean finished = false;
		graphics.draw(SplashId, new Rect(0f, 0f, Driver.screenWidth, Driver.screenHeight));
		finished = true;
		return finished;
	}
	
	public void render() {
		
	}	
}
