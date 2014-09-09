package game.ui;

import game.buildings.BuildingType;
import graphics.Graphics;
import main.Driver;

import org.newdawn.slick.geom.Vector2f;

import widgets.Text;

public class MilitaryTab extends UITab {


	public MilitaryTab(UI ui) {
		super(ui, new Vector2f(150, Driver.screenHeight- UI.height), "MILITARY");
		idNumbers = new int[4];
		buttons = new GameButton[4];
	}
	@Override
	public void initialize(Graphics g) {
		idNumbers[0] = g.loadImage("Icons/BarracksButton");
		idNumbers[1] = g.loadImage("Icons/RangeButton");
		idNumbers[2] = g.loadImage("Icons/CavalryButton");
		idNumbers[3] = g.loadImage("Icons/ArcanumButton");

		buttons[0] = new GameButton(idNumbers[0], new Vector2f(20, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Barracks, ui);
		buttons[1] = new GameButton(idNumbers[1], new Vector2f(100, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Range, ui);
		buttons[2] = new GameButton(idNumbers[2], new Vector2f(180, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Cavalry, ui);
		buttons[3] = new GameButton(idNumbers[3], new Vector2f(260, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Arcanum, ui);
	}

	@Override
	public void drawTab(Graphics g, int id) {
		//draw the tab;
	}

	@Override
	public void drawText() {
		Text.write("Military", tabPosition);
	}

}
