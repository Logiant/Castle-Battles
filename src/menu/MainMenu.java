package menu;

import graphics.Graphics;
import graphics.Rect;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

/**
 * the main menu seen when entering the game
 * has buttons - campaign, skirmish, options
 * @author beaverl
 *
 */
public class MainMenu {
	
	//Button[]
	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());
	
	public void initialize(Graphics g) {
		textureId = g.loadImage("MainMenu");
	}
	public String update(Graphics g) {
		String cmd = "";	//Changed from GAME to an empty string to test the Main Menu state
		GL11.glBegin(GL11.GL_QUADS);
		draw(g);
		GL11.glEnd();
		return cmd;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
	}
}
