package game.ui;

import main.Driver;

import org.newdawn.slick.geom.Vector2f;

import widgets.Text;
import graphics.Graphics;

public class ResourceTab extends UITab {

	public ResourceTab(UI ui) {
		super(ui, new Vector2f(20, Driver.screenHeight- UI.height), "RESOURCE");
		idNumbers = new int[6];
		buttons = new GameButton[6];
	}
	@Override
	public void initialize(Graphics g) {
		idNumbers[0] = g.loadImage("Icons/FarmButton");
		idNumbers[1] = g.loadImage("Icons/MineButton");
		idNumbers[2] = g.loadImage("Icons/LumberButton");
		idNumbers[3] = g.loadImage("Icons/StableButton");
		idNumbers[4] = g.loadImage("Icons/QuarryButton");
		idNumbers[5] = g.loadImage("Icons/MagicButton");

		buttons[0] = new GameButton(idNumbers[0], new Vector2f(20, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "FARM", ui);
		buttons[1] = new GameButton(idNumbers[1], new Vector2f(100, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "MINE", ui);
		buttons[2] = new GameButton(idNumbers[2], new Vector2f(180, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "WOOD", ui);
		buttons[3] = new GameButton(idNumbers[3], new Vector2f(260, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "HORSE", ui);
		buttons[4] = new GameButton(idNumbers[4], new Vector2f(340, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "STONE", ui);
		buttons[5] = new GameButton(idNumbers[5], new Vector2f(420, Driver.screenHeight - UI.height + 20), new Vector2f(60, 60), "MAGIC", ui);

	}

	@Override
	public void drawTab(Graphics g, int id) {

	}

	@Override
	public void drawText() {
		Text.write("Resources", tabPosition);
	}

}
