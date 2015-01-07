package game.ui;

import game.buildings.BuildingType;
import graphics.Graphics;
import main.Driver;

import org.newdawn.slick.geom.Vector2f;

import widgets.Text;

public class DefenseTab extends UITab {


	public DefenseTab(UI ui) {
		super(ui, new Vector2f(270, Driver.screenHeight- UI.height), "DEFENSE");
		idNumbers = new int[3];
		buttons = new GameButton[3];
	}
	@Override
	public void initialize(Graphics g) {
		idNumbers[0] = g.loadImage("Icons/WallButton");
		idNumbers[1] = g.loadImage("Icons/PitfallButton");
		idNumbers[2] = g.loadImage("Icons/Delete");
		
		buttons[0] = new GameButton(idNumbers[0], new Vector2f(20, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Wall, ui);
		buttons[1] = new GameButton(idNumbers[1], new Vector2f(100, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Pitfall, ui);
		buttons[2] = new GameButton(idNumbers[2], new Vector2f(Driver.screenWidth - 120, Driver.screenHeight - UI.height + 35), new Vector2f(60, 60), BuildingType.Delete, ui);
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
