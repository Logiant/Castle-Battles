package game.ui;

import game.buildings.BuildingType;
import graphics.Graphics;
import graphics.Rect;
import main.Driver;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

public class GameButton {

	private int textureId;
	Vector2f position;
	Vector2f size;
	BuildingType command;
	UI ui;

	private boolean active;

	public GameButton(int textureId, Vector2f position, Vector2f size, BuildingType command, UI ui) {
		this.textureId = textureId;
		this.position = position;
		this.size = size;
		this.command = command;
		this.ui = ui;
	}


	public BuildingType update() {
		BuildingType cmd = null;
		//check for mouseover
		if (Mouse.getX() > position.x && Mouse.getX() < position.x + size.x &&
				Driver.screenHeight - Mouse.getY() > position.y && Driver.screenHeight - Mouse.getY() < position.y + size.y) {
			ui.mouseOver(command);
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

}
