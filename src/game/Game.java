package game;

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
	
	public Game() {
		cam = new Camera();
		background = new SimpleBackground();
	}
	
	public void initialize(Graphics g) {
		background.initialize(g);
	}
	public void update(Graphics g) {
		cam.update();
		
		GL11.glBegin(GL11.GL_QUADS);
		background.draw(g);
		GL11.glEnd();
		//TODO finish this
	}
}
