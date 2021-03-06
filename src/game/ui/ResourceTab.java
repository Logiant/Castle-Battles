package game.ui;

import main.Driver;

import org.newdawn.slick.geom.Vector2f;

import widgets.Text;
import game.buildings.BuildingType;
import graphics.Graphics;

public class ResourceTab extends UITab {

	public ResourceTab(UI ui) {
		super(ui, new Vector2f(20, Driver.screenHeight- UI.height), "RESOURCE");
		idNumbers = new int[7];
		buttons = new GameButton[7];
	}
	@Override
	public void initialize(Graphics g) {
		idNumbers[0] = g.loadImage("Icons/FarmButton");
		idNumbers[1] = g.loadImage("Icons/MineButton");
		idNumbers[2] = g.loadImage("Icons/LumberButton");
		idNumbers[3] = g.loadImage("Icons/StableButton");
		idNumbers[4] = g.loadImage("Icons/QuarryButton");
		idNumbers[5] = g.loadImage("Icons/MagicButton");
		idNumbers[6] = g.loadImage("Icons/Delete");

		buttons[0] = new GameButton(idNumbers[0], new Vector2f(20, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Farm, ui);
		buttons[1] = new GameButton(idNumbers[1], new Vector2f(100, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Mine, ui);
		buttons[2] = new GameButton(idNumbers[2], new Vector2f(180, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Mill, ui);
		buttons[3] = new GameButton(idNumbers[3], new Vector2f(260, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Stable, ui);
		buttons[4] = new GameButton(idNumbers[4], new Vector2f(340, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Quarry, ui);
		buttons[5] = new GameButton(idNumbers[5], new Vector2f(420, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.MagicPump, ui);
		buttons[6] = new GameButton(idNumbers[6], new Vector2f(Driver.screenWidth - 120, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Delete, ui);

	}

	@Override
	public void drawTab(Graphics g, int id) {

	}

	@Override
	public void drawText() {
		Text.write("Resources", tabPosition);
	}

}
