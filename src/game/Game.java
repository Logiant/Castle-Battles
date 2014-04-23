
package game;

import main.InputHandler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

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
	
	//we can load this into a keybind class or something
	int upKey = Keyboard.getKeyIndex("W");
	int downKey = Keyboard.getKeyIndex("S");
	int leftKey = Keyboard.getKeyIndex("A");
	int rightKey = Keyboard.getKeyIndex("D");

	
	public Game() {
		cam = new Camera();
		background = new SimpleBackground();
	}
	
	public void initialize(Graphics g) {
		background.initialize(g);
		cam.initialize(0, 0);
	}
	public void update(Graphics g) {
		
		cameraMovement();		
		
		//rendering - we could make this a nested class called Render if needed
		GL11.glBegin(GL11.GL_QUADS);
		background.draw(g);
		GL11.glEnd();
		//TODO finish this
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
