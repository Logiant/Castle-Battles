
package game;

import main.InputHandler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import widgets.GameOptionsMenu;
import game.world.SimpleBackground;
import graphics.Graphics;

/**
 * an actual skirmish game. Handles input, logic, and rendering
 * @author beaverl
 *
 */
public class Game {

	Camera cam;
	SimpleBackground background;
	GameOptionsMenu gameOptionsMenu;

	//we can load this into a keybind class or something
	int upKey = Keyboard.getKeyIndex("W");
	int downKey = Keyboard.getKeyIndex("S");
	int leftKey = Keyboard.getKeyIndex("A");
	int rightKey = Keyboard.getKeyIndex("D");
	int escape = Keyboard.KEY_ESCAPE;


	public Game() {
		cam = new Camera();
		background = new SimpleBackground();
		gameOptionsMenu = new GameOptionsMenu();
	}

	public void initialize(Graphics g) {
		background.initialize(g);
		cam.initialize(0, 0);
		gameOptionsMenu.initialize(g);
	}
	public String update(Graphics g) {

		cameraMovement();		
		String nextState = gameOptionsMenu.update(g);

		if(InputHandler.wasKeyPressed(escape) || nextState.equals("BACK")) {
			gameOptionsMenu.toggle();
		}

		//rendering - we could make this a nested class called Render if needed
		GL11.glBegin(GL11.GL_QUADS);
		background.draw(g);
		GL11.glEnd();

		drawUI(g);	
		
		//TODO finish this
		return nextState;
	}
	
	public void drawUI(Graphics g) {
		if (gameOptionsMenu.isActive()) {
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glBegin(GL11.GL_QUADS);
			gameOptionsMenu.draw(g);
			GL11.glEnd();
			gameOptionsMenu.draw2(g);
			GL11.glPopMatrix();

		}
	}

	public void cameraMovement() {
		float dx = 0;
		float dy = 0;
		if (InputHandler.isKeyDown(upKey)) {
			dy--;
		}if (InputHandler.isKeyDown(downKey)) {
			dy++;
		}if (InputHandler.isKeyDown(leftKey)) {
			dx--;
		}if (InputHandler.isKeyDown(rightKey)) {
			dx++;
		}
		cam.move(dx, dy);
		cam.update();
	}
}
