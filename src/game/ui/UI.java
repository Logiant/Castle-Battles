package game.ui;

import main.Driver;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import game.buildings.CityManager;
import graphics.Graphics;
import graphics.Rect;

/**
 * the ingame user interfaces - used to help the player interact with the program
 * @author beaverl
 *
 */
public class UI {

	//eventually there should be a TAB helper class that handles each tab (buildings, spells, upgrades, etc)
	GameButton[] buttons = new GameButton[1];;
	
	CityManager city;
	
	int buttonTextureId;
	int uiTextureId;
	
	public static int height = 90;
	
	public UI(CityManager city) {
		this.city = city;
	}
	
	public void initialize(Graphics g) {
		buttonTextureId = g.loadImage("FarmButton");
		uiTextureId = g.loadImage("UIColor");
		
		buttons[0] = new GameButton(buttonTextureId, new Vector2f(20, Driver.screenHeight - height + 5), new Vector2f(60, 60), "FARM");
	}
	
	public String update(Graphics g) {
		String cmd = "";
		
		for (GameButton b:buttons) {
			cmd = b.update();
			if (cmd != "") {
				//we may want to filter commands based on the active tab (building vs spells vs upgrades)
				city.buildCommand(cmd);
				break;
			}
		}
		
		g.draw(uiTextureId, new Rect(0, Driver.screenHeight - height, Driver.screenWidth, height));
		for (GameButton b:buttons)
			b.draw(g);
		
		return cmd;
	}
	
	public static boolean containsMouse() {
		boolean moused = false;
		if (Driver.screenHeight - Mouse.getY() > Driver.screenHeight - height) {
			moused = true;
		}
		return moused;
	}
}
