package widgets;

import graphics.Graphics;
import graphics.Rect;
import main.Driver;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

public class MenuButton {

	private String text;
	private Vector2f position;
	private Vector2f size;
	private String command;
	
	private int textureId;

	boolean active = false; //is the mouse pressed down over the button

	public MenuButton(String text, Vector2f position, Vector2f size, String command) {
		this.text = text;
		this.position = position;
		this.size = size;
		this.command = command;
	}
	
	public void initialzie(Graphics g) {
		textureId = g.loadImage("Button");
	}

	public String update() {	
		String cmd = "";
		//check for mouseover
		if (Mouse.getX() > position.x && Mouse.getX() < position.x + size.x &&
			Driver.screenHeight - Mouse.getY() > position.y && Driver.screenHeight - Mouse.getY() < position.y + size.y) {
			if (Mouse.isButtonDown(0)) { //0 is left click, 1 is right click, 2 is middle click
				active = true; //this could change the button to a pressed button
			} else if (active) { //buttons dont usually click until the mouse is released
				cmd = command;
				active = false;
			}
		} else { //if you're not over the button
			active = false;
		}
		return cmd;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, size));
	}
	
	public void drawText() {
		//the 5 is magic. I have no idea how font size and width are related - I think it depends on the font
		Text.write(text, new Vector2f(position.x + size.x/2 - 5*text.length(), position.y + size.y/2 - Text.fontSize/2f));
	}
}
