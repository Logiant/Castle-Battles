package menu;


import graphics.Graphics;
import graphics.Rect;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import widgets.MenuButton;

/**
 * the main menu seen when entering the game
 * has buttons - campaign, skirmish, options
 * @author beaverl
 *
 */
public class MainMenu {
	
	MenuButton[] buttons = new MenuButton[4]; //Campaign, Skirmish, Options, Quit
	
	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());
	
	public void initialize(Graphics g) {
		textureId = g.loadImage("MainMenu");
		//button constructor		Text		Position				Size					Command
		buttons[0] = new MenuButton("Campaign",	new Vector2f(520,100),	new Vector2f(256,64),	"CAMPAIGN"); //not implemented
		buttons[1] = new MenuButton("Skirmish",	new Vector2f(520,200),	new Vector2f(256,64),	"GAME");
		buttons[2] = new MenuButton("Options", 	new Vector2f(520,300),	new Vector2f(256,64),	"OPTIONs"); //not implemented
		buttons[3] = new MenuButton("Quit", 	new Vector2f(520,400),	new Vector2f(256,64),	"QUIT");
		
		for (MenuButton b : buttons)	//Changed to enhanced loop so you dont need to change loop parameters
			if (b != null)				//every time you add a new button. 
				b.initialzie(g);
	}
	public String update(Graphics g) {
		String cmd = "";	//Changed from GAME to an empty string to test the Main Menu state
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
