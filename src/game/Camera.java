package game;

import main.Time;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

/**
 * Translates the world to see around
 * @author beaverl
 *
 */
public class Camera {
	
	private Vector2f position;
	private float moveSpeed = 0.8f; //pixels/ms
	
	public void initialize(float x, float y) {
		position = new Vector2f(x, y);
	}

	public void update() {
		//sets (0, 0) to be the upper left of the screen
		GL11.glLoadIdentity();
		//translates the origin [and thus everything drawn after this call] (-x, -y) to give the illusion of camera movement
		//so the position (2, 1) translates the world left 2 and up 1 (-2, -1) so the camera appears to move right 2 and down 1
		GL11.glTranslatef(-(int)position.x, -(int)position.y, 0);
	}
	

	//we may eventually want to bounds on the camera's position when we have a real map
	public void move(float dx, float dy) { //takes in an x and y displacement
		position.x += dx * moveSpeed * Time.dt;
		position.y += dy * moveSpeed * Time.dt;
	}

	public Vector2f getTranslation() {
		return position;
	}
}
