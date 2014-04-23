package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.*;

/**
 * this class contains main()
 * It initializes openGL and contains the game running loop
 * @author beaverl
 *
 */
public class Driver {


	public static int screenHeight = 600;	//changed modifier to public so our other packages can access it
	public static int screenWidth = 800;	//ditto
	
	private Controller game;

	/**
	 * constructor
	 * initializes the program, then runs the game running loop
	 */
	public Driver() {
		// Initialization
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.setTitle("Castle Battles");
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		graphicInitialization();
		logicInitialization();
		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			updateLogic();
			
			Display.update();
			Display.sync(60);
		}//end update loop
		AL.destroy();
		Display.destroy();
		Keyboard.destroy();
		System.exit(0);
	}

	/**
	 * initializes the game state controller
	 */
	public void logicInitialization() {
		game = new Controller();
	}

	/**
	 * initializes the OpenGL
	 */
	public void graphicInitialization() {
		GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glEnable(GL11.GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	/**
	 * resets the FOV and updates the game state controller
	 */
	public void updateLogic() {
		System.out.println("dt: " + Time.dt);
		Time.update();
		game.update();
	}
	
	public static void main(String[] args) {
		new Driver();
	}

}