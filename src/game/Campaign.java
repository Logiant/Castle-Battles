package game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import widgets.MenuButton;
import graphics.Graphics;
import graphics.Rect;


/**
 * This class will be responsible for the level selection in the single player campaign mode. Once the 
 * player chooses the level they want to play on, the cmd will tell the GameController to load the level. 
 *
 *In this class, I added enhanced for loops so that when we add levels, we only need to change two things:
 *	- The size of the buttons[] array
 *	- Initialize buttons[x]
 */
public class Campaign {

	MenuButton[] buttons = new MenuButton[6]; //Level 1 through x
	
	int textureId;
	Vector2f position = new Vector2f();
	Vector2f size = new Vector2f(Display.getWidth(), Display.getHeight());
	
	
	public void initialize(Graphics g) {
		textureId = g.loadImage("Campaign");
		//button constructor		Text		Position				Size					Command
		buttons[0] = new MenuButton("Level 1",	new Vector2f(100,75),	new Vector2f(150,64),	"1"); //Not implemented
		buttons[1] = new MenuButton("Level 2",	new Vector2f(100,150),	new Vector2f(150,64),	"2"); //Not implemented
		buttons[2] = new MenuButton("Level 3", 	new Vector2f(100,225),	new Vector2f(150,64),	"3"); //Not implemented
		buttons[3] = new MenuButton("Level 4", 	new Vector2f(100,300),	new Vector2f(150,64),	"4"); //Not implemented
		buttons[4] = new MenuButton("Level 5", 	new Vector2f(100,375),	new Vector2f(150,64),	"5"); //Not implemented
		buttons[5] = new MenuButton("<< Back", 	new Vector2f(100,450),	new Vector2f(150,64),	"MAIN_MENU");
		
		for (MenuButton b : buttons)
			if(b != null)
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

		for (MenuButton b : buttons)
			b.drawText();
		
		return cmd;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
		for (MenuButton b : buttons)
			b.draw(g);
	}
}
