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
		GL11.glLoadIdentity();
		GL11.glTranslatef(-position.x, -position.y, 0);
	}
	
	public void move(float x, float y) {
		position.x += x * moveSpeed * Time.dt;
		position.y += y * moveSpeed * Time.dt;
	}
}
