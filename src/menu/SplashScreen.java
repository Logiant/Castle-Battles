package menu;

import java.util.Timer;
import java.util.TimerTask;

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

	boolean finished;
	int textureId;
	Timer timer;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());
	

	public void initialize(Graphics g) {
		textureId = g.loadImage("Splashscreen");
		finished = false;
		timer = new Timer();
	}
	
	public boolean update(Graphics g) {
		GL11.glBegin(GL11.GL_QUADS);
		draw(g);
		GL11.glEnd();
		timer.schedule(new SplashTimer(), 2000);
		return finished;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
	}
	
	private class SplashTimer extends TimerTask {

		@Override
		public void run() {
			finished = true;			
		}
		
	}
}
