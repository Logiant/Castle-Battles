package game.ui;

import main.Driver;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import widgets.BuildingTooltip;
import game.buildings.BuildingType;
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
	
	private int activeTab = 0;
	
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
		tooltip.setSize(new Vector2f(140, 105));
		dummy = new DummyBuilding();
		tooltip.target = dummy;
	}

	public void drawText() {
		for (UITab t: tabs)
			t.drawText();
		if (showTip)
			tooltip.drawText();

	}
	
	public void mouseOver(BuildingType cmd) {
		dummy.setBuilding(cmd);
		showTip = true;
 	}

	public void update(Graphics g, boolean active) {
		showTip = false;
		BuildingType cmd = null;
		g.draw(uiTextureId, new Rect(0, Driver.screenHeight - height, Driver.screenWidth, height));
		for (int  i = 0; i < tabs.length; i++)  {
			int textId = 1;
			if (activeTab == i)
				textId = 2;
			g.draw(textId, new Rect(10 + 120*i, Driver.screenHeight - height, 110, 20));
		}

		for (UITab t: tabs) {
			cmd = t.update(g);
			if (active) {
				if (cmd != null) {
					city.buildCommand(cmd);
				}
			}
		}
		if (showTip)
			tooltip.draw(g);
		
		g.draw(uiTextureId, new Rect(0, 0, 280, 80));
	}

	public static boolean containsMouse() {
		boolean moused = false;
		if (Driver.screenHeight - Mouse.getY() > Driver.screenHeight - height) {
			moused = true;
		}
		return moused;
	}

	public void switchTabs(String nextTab) {
		switch (nextTab) {
		default:
			return;
		case "RESOURCE":
			activeTab = 0;
			break;
		case "MILITARY":
			activeTab = 1;
			break;
		case "DEFENSE":
			activeTab = 2;
			break;
		}
		for (UITab t: tabs)
			t.setActive(false);
		tabs[activeTab].setActive(true);
	}
}
