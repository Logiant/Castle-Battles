package game.world;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.geom.Vector2f;

import graphics.Graphics;
import graphics.Rect;

/**
 * A simple static background for testing purposes
 * @author beaverl
 *
 */
public class SimpleBackground {
	
	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());

	public void initialize(Graphics g) {
		textureId = g.loadImage("background");
	}
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
	}
}
