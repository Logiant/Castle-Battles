package widgets;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import graphics.Graphics;
import graphics.Rect;

public class GameOptionsMenu {

	private MenuButton buttons[];
	
	private int textureId;
	private Vector2f position = new Vector2f(Display.getWidth() * 2 / 3, Display.getHeight() * 2 / 3);
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
		String cmd = "";
		
		for(MenuButton b : buttons)
			if(b != null)
				b.update();
		
		GL11.glPushMatrix();
		//GL11.glLoadIdentity();
		GL11.glBegin(GL11.GL_QUADS);
		draw(g);
		GL11.glEnd();
		GL11.glPopMatrix();
		
		for (MenuButton b : buttons)
			b.drawText();
		
		return cmd;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
		for (MenuButton b : buttons)	//Changed to enhanced loop
			b.draw(g);
	}
}
