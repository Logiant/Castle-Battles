package menu;

import graphics.Graphics;
import graphics.Rect;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import widgets.MenuButton;

/**
 * A class to handle the options menu
 * YOU HAVE NO OPTIONS
 * @author Logs
 *
 */
public class OptionsMenu {

MenuButton[] buttons = new MenuButton[1]; //Back
	
	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());
	
	public void initialize(Graphics g) {
		textureId = g.loadImage("Options");
		//button constructor		Text		Position				Size					Command
		buttons[0] = new MenuButton("<< Back",	new Vector2f(520,100),	new Vector2f(256,64),	"BACK"); 
		
		for (MenuButton b : buttons)	//Changed to enhanced loop so you dont need to change loop parameters
			if (b != null)				//every time you add a new button. 
				b.initialzie(g);
	}
	
	public String update(Graphics g) {
		String cmd = "";
		for (MenuButton b : buttons) {
			cmd = b.update();
			if (cmd != null)
				break;	//a button was pressed!
		}
		
		GL11.glBegin(GL11.GL_QUADS);
		draw(g);
		GL11.glEnd();
		
		
		//drawing fonts needs to be done outside of the glBegin and glEnd calls because they use their own textures
		//this is a bit of a hack, we may want to do something about it later - maybe unbinding the textures then calling it? idk
		for (MenuButton b : buttons)	//Changed to enhanced loop
			b.drawText();
		
		return cmd;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
		for (MenuButton b : buttons)	//Changed to enhanced loop
			b.draw(g);
	}
}
