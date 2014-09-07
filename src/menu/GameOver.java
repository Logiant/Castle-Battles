package menu;


import graphics.Graphics;
import graphics.Rect;
import main.Driver;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import widgets.MenuButton;
import widgets.Text;

/**
 * the game over seen
 * has buttons - close
 * @author beaverl
 *
 */
public class GameOver {

	MenuButton returnButton;

	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());

	public void initialize(Graphics g) {
		textureId = g.loadImage("GameOver");
		returnButton = new MenuButton("Return", new Vector2f(Driver.screenWidth/2f - 250, Driver.screenHeight/2f), new Vector2f(500, 100), "QUIT");
		returnButton.initialzie(g);

	}

	public String update(Graphics g, boolean win) {
		String cmd = "";	//Changed from GAME to an empty string to test the Main Menu state
		cmd = returnButton.update();
		
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glBegin(GL11.GL_QUADS);
		draw(g);
		GL11.glEnd();
		draw2(g, win);
		GL11.glPopMatrix();

		//drawing fonts needs to be done outside of the glBegin and glEnd calls because they use their own textures
		//this is a bit of a hack, we may want to do something about it later - maybe unbinding the textures then calling it? idk

		return cmd;
	}

	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
		returnButton.draw(g);
	}

	public void draw2(Graphics g, boolean win) {
		if (win)
			Text.write("You win!", new Vector2f(Driver.screenWidth/2f, Driver.screenHeight/2f - 200));
		else
			Text.write("Loser!", new Vector2f(Driver.screenWidth/2f, Driver.screenHeight/2f - 200));
		returnButton.drawText();
	}
}
