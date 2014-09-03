package game.ui;

import main.Driver;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import widgets.BuildingTooltip;
import game.buildings.Building;
import game.buildings.City;
import game.buildings.DummyBuilding;
import graphics.Graphics;
import graphics.Rect;

/**
 * the ingame user interfaces - used to help the player interact with the program
 * @author beaverl
 *
 */
public class UI {

	City city;
	UITab[] tabs;
	BuildingTooltip tooltip;
	DummyBuilding dummy;
	private int uiTextureId;
	public static int height = 90;
	
	boolean showTip;

	public UI(City city) {
		this.city = city;
		tabs = new UITab[3];
		tabs[0] = new ResourceTab(this);
		tabs[1] = new MilitaryTab(this);
		tabs[2] = new DefenseTab(this);

		tabs[0].setActive(true);
		

	}

	public void initialize(Graphics g) {
		uiTextureId = g.loadImage("UIColor");
		for (UITab t: tabs)
			t.initialize(g);
		
		tooltip = new BuildingTooltip(uiTextureId);
		tooltip.setPosition(new Vector2f(650, 400));
		tooltip.setSize(new Vector2f(140, 100));
		dummy = new DummyBuilding();
		tooltip.target = dummy;
	}

	public void drawText() {
		for (UITab t: tabs)
			t.drawText();
		if (showTip)
			tooltip.drawText();

	}
	
	public void mouseOver(String cmd) {
		dummy.setBuilding(Building.fromString(cmd));
		showTip = true;
	}

	public void update(Graphics g, boolean active) {
		showTip = false;
		String cmd = "";
		g.draw(uiTextureId, new Rect(0, Driver.screenHeight - height, Driver.screenWidth, height));
		for (UITab t: tabs) {
			cmd = t.update(g);
			if (active) {
				if (!cmd.equals("")) {
					switchTabs(cmd);
					city.buildCommand(cmd);
				}
			}
		}
		if (showTip)
			tooltip.draw(g);
	}

	public static boolean containsMouse() {
		boolean moused = false;
		if (Driver.screenHeight - Mouse.getY() > Driver.screenHeight - height) {
			moused = true;
		}
		return moused;
	}

	public void switchTabs(String nextTab) {
		int tabId = 0;
		switch (nextTab) {
		default:
			return;
		case "RESOURCE":
			break;
		case "MILITARY":
			tabId = 1;
			break;
		case "DEFENSE":
			tabId = 2;
			break;
		}
		for (UITab t: tabs)
			t.setActive(false);
		tabs[tabId].setActive(true);
	}
}
