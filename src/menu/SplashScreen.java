package menu;


import main.Time;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


import org.newdawn.slick.geom.Vector2f;

import graphics.Graphics;
import graphics.Rect;

/**
 * The game's splash screen
 * An image with a possible loading bar/audio and/or a simple animation
 * @author beaverl
 *
 */
public class SplashScreen {

	float timeRemaining = 3000;		//3 seconds (or 3000 ms)
	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());
	

	public void initialize(Graphics g) {
		textureId = g.loadImage("Splashscreen");
	}
	
	public boolean update(Graphics g) {
		GL11.glBegin(GL11.GL_QUADS);
		draw(g);
		GL11.glEnd();
		timeRemaining -= Time.dt;	//decrement the time remaining by the duration of the last update
		return timeRemaining <= 0;	//if the time is less than or equal to 0, return true.
	}								//causing the program to shift to the next state
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
	}
}
