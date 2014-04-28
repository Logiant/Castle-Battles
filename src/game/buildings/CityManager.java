package game.buildings;

import java.util.ArrayList;
import java.util.List;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import game.ui.UI;
import game.world.Map;
import graphics.Graphics;
import graphics.Rect;

/**
 * a class that managies the city - buildings and stuff
 * @author Logs
 *
 */
public class CityManager {

	List<Farm> farms;
	List<Mine> mines;
	List<Mill> mills;
	List<Stable> stables;
	List<Quarry> quarries;

	int farmId;
	int mineId;
	int millId;
	int stableId;
	int quarryId;

	boolean placingBuilding;
	int placingBuildingId;
	Vector2f placingPosition;
	Map world;

	public void initialize(Graphics g, Map map) {
		farmId = g.loadImage("Farm");
		mineId = g.loadImage("Mine");
		millId = g.loadImage("Lumber");
		stableId = g.loadImage("Stable");
		quarryId = g.loadImage("Quarry");
		farms = new ArrayList<Farm>();
		mines = new ArrayList<Mine>();
		mills = new ArrayList<Mill>();
		stables = new ArrayList<Stable>();
		quarries = new ArrayList<Quarry>();

		world = map;
		placingPosition = new Vector2f();
	}

	public void update(Vector2f translation, boolean active) {
		if (InputHandler.rightClicked())
			placingBuilding = false;


		if (placingBuilding && world.isBuildable(placingPosition.x, placingPosition.y, Farm.width, Farm.height) 
				&& active && InputHandler.leftClicked() && !UI.containsMouse()) { //click to place a building
			//place the currently selected building
			buildBuilding();
		}

		collectResources();

	}

	public void buildCommand (String command) {
		switch (command) {
		case "FARM":
			placingBuildingId = farmId;
			placingBuilding = true;
			break;
		case "MINE":
			placingBuildingId = mineId;
			placingBuilding = true;
			break;
		case "WOOD":
			placingBuildingId = millId;
			placingBuilding = true;
			break;
		case "HORSE":
			placingBuildingId = stableId;
			placingBuilding = true;
			break;
		case "STONE":
			placingBuildingId = quarryId;
			placingBuilding = true;
			break;
		}
	}

	private void collectResources() { //this will eventually get resources from buildings
		for (Farm f: farms)
			f.update();
	}

	public void draw(Graphics g) {
		//draw all buildings
		for (Farm f: farms)
			f.draw(g);
		for (Mine f: mines)
			f.draw(g);
		for (Mill f: mills)
			f.draw(g);
		for (Stable f: stables)
			f.draw(g);
		for (Quarry f: quarries)
			f.draw(g);

	}

	public void buildBuilding() {
		if (placingBuildingId == farmId) {
			farms.add(new Farm(farmId, placingPosition));
			world.placeBuilding(placingPosition.x, placingPosition.y, Farm.width, Farm.height);
		}
		if (placingBuildingId == mineId) {
			mines.add(new Mine(mineId, placingPosition));
			world.placeBuilding(placingPosition.x, placingPosition.y, Mine.width, Mine.height);
		}
		if (placingBuildingId == millId) {
			mills.add(new Mill(millId, placingPosition));
			world.placeBuilding(placingPosition.x, placingPosition.y, Mill.width, Mill.height);
		}
		if (placingBuildingId == stableId) {
			stables.add(new Stable(stableId, placingPosition));
			world.placeBuilding(placingPosition.x, placingPosition.y, Stable.width, Stable.height);
		}
		if (placingBuildingId == quarryId) {
			quarries.add(new Quarry(quarryId, placingPosition));
			world.placeBuilding(placingPosition.x, placingPosition.y, Quarry.width, Quarry.height);
		}
	}

	public void drawPlaced(Graphics g, Vector2f translate, boolean active) {
		//draw the building being placed if there is one
		if (placingBuilding) {
			//snaps the current position to the nearest tile
			if (active) {
				placingPosition = new Vector2f((float)Math.floor((Mouse.getX()+translate.x) / Map.TILE_SIZE)*Map.TILE_SIZE, 
						(float)Math.ceil(((Driver.screenHeight - Mouse.getY() - Farm.getSize().y + translate.y)/Map.TILE_SIZE))*Map.TILE_SIZE);
			}
			//draws the selected building (set to farm for now) at the current world location snapped to the tile the cursor is over
			g.draw(placingBuildingId, new Rect(placingPosition, new Vector2f(Farm.getSize().x, Farm.getSize().y)));
			//we want some sort of notification that a spot can't be built on - maybe tinting the building red?
		}
	}
}
