package game.ui;

import graphics.Graphics;
import main.Driver;

import org.newdawn.slick.geom.Vector2f;

import widgets.Text;

public class DefenseTab extends UITab {


	public DefenseTab(UI ui) {
		super(ui, new Vector2f(250, Driver.screenHeight- UI.height), "DEFENSE");
		idNumbers = new int[2];
		buttons = new GameButton[2];
	}
	@Override
	public void initialize(Graphics g) {
		idNumbers[0] = g.loadImage("Icons/WallButton");
		idNumbers[1] = g.loadImage("Icons/PitfallButton");

		buttons[0] = new GameButton(idNumbers[0], new Vector2f(20, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "WALL");
		buttons[1] = new GameButton(idNumbers[1], new Vector2f(100, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "PITFALL");
	}

	@Override
	public void drawTab(Graphics g, int id) {
		//draw the tab;
	}

	@Override
	public void drawText() {
		Text.write("Defense", tabPosition);
	}

}