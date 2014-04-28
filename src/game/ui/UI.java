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
	GameButton[] buttons = new GameButton[5];;
	
	CityManager city;
	
	int farmId;
	int mineId;
	int lumberId;
	int stableId;
	int quarryId;
	int uiTextureId;
	
	public static int height = 90;
	
	public UI(CityManager city) {
		this.city = city;
	}
	
	public void initialize(Graphics g) {
		farmId = g.loadImage("FarmButton");
		mineId = g.loadImage("MineButton");
		lumberId = g.loadImage("LumberButton");
		stableId = g.loadImage("StableButton");
		quarryId = g.loadImage("QuarryButton");
		uiTextureId = g.loadImage("UIColor");
		
		buttons[0] = new GameButton(farmId, new Vector2f(20, Driver.screenHeight - height + 5), new Vector2f(60, 60), "FARM");
		buttons[1] = new GameButton(mineId, new Vector2f(100, Driver.screenHeight - height + 5), new Vector2f(60, 60), "MINE");
		buttons[2] = new GameButton(lumberId, new Vector2f(180, Driver.screenHeight - height + 5), new Vector2f(60, 60), "WOOD");
		buttons[3] = new GameButton(stableId, new Vector2f(260, Driver.screenHeight - height + 5), new Vector2f(60, 60), "HORSE");
		buttons[4] = new GameButton(quarryId, new Vector2f(340, Driver.screenHeight - height + 5), new Vector2f(60, 60), "STONE");

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
