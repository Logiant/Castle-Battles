package game.ui;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import widgets.Text;
import game.buildings.BuildingType;
import graphics.Graphics;

/**
 * this class is each tab on the UI
 * @author beaverl
 *
 */
public abstract class UITab {
	protected int[] idNumbers;
	protected GameButton[] buttons;
	protected boolean active;
	protected UI ui;
	protected String name;
	protected Vector2f tabPosition;
	
	public UITab(UI ui, Vector2f tabPosition, String name) {
		this.ui = ui;
		this.tabPosition = tabPosition;
		this.name = name;
	}

	public abstract void initialize(Graphics g);

	public BuildingType update(Graphics g) {
		BuildingType cmd = null;
		if (active) {
			for (GameButton b:buttons) {
				cmd = b.update();
				if (cmd != null) {
					break;
				}
			}
			for (GameButton b:buttons)
				b.draw(g);
		}
		if (Mouse.getX() > tabPosition.x && Mouse.getX() < tabPosition.x + name.length() * Text.fontSize/2f 
				&& Driver.screenHeight - Mouse.getY() > tabPosition.y && Driver.screenHeight - Mouse.getY() < tabPosition.y + 15
				&& InputHandler.leftClicked()) {
			ui.switchTabs(name);
		}
		return cmd;
	}

	public abstract void drawText();
	
	public void setActive(boolean b) {
		active = b;
	}

	public boolean isActive() {
		return active;
	}

	public abstract void drawTab(Graphics g, int id);
}
