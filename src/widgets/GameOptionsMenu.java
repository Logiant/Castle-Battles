package widgets;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import graphics.Graphics;
import graphics.Rect;

public class GameOptionsMenu {

	private MenuButton buttons[] = new MenuButton[2];
	
	private int textureId;
	private Vector2f position = new Vector2f(Display.getWidth() * 1 / 3, Display.getHeight() * 1 / 3);
	private Vector2f size = new Vector2f(Display.getWidth() / 3, Display.getHeight() / 3);
	
	boolean active = false;
	
	public void initialize(Graphics g) {
		textureId = g.loadImage("GameMenuOptions");
 		buttons[0] = new MenuButton("<< Back", new Vector2f(position.x + 50, position.y + 50), new Vector2f(50, 50), "BACK");
		buttons[1] = new MenuButton("Quit", new Vector2f(position.x + 50, position.y + 150), new Vector2f(50, 50), "QUIT");
		
		for(MenuButton b : buttons)
			if(b != null)
				b.initialzie(g);
	}
	
	public String update(Graphics g) {
		String cmd = null;
		if(active) {
		for(MenuButton b : buttons)
			if(b != null) {
				cmd = b.update();
				break;
			}
		
		for (MenuButton b : buttons)
			b.drawText();
		}
		
		return cmd;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
		for (MenuButton b : buttons)	//Changed to enhanced loop
			b.draw(g);
	}
	
	public void draw2(Graphics g) {
		for (MenuButton b : buttons)	//Changed to enhanced loop
			b.drawText();
	}
	
	public void toggle() {
		active = !active;
	}
	
	public boolean isActive() {
		return active;
	}
}
