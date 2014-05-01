package game.ui;

import org.newdawn.slick.geom.Vector2f;

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
	protected Vector2f tabPosition;
	
	public UITab(UI ui, Vector2f tabPosition) {
		this.ui = ui;
		this.tabPosition = tabPosition;
	}

	public abstract void initialize(Graphics g);

	public String update(Graphics g) {
		String cmd = "";
		if (active) {
			for (GameButton b:buttons) {
				cmd = b.update();
				if (cmd != "") {
					break;
				}
			}
			for (GameButton b:buttons)
				b.draw(g);
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
