package game.buildings;

import java.util.ArrayList;
import java.util.List;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import game.entities.ResourceBuilding;
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


	List<ResourceBuilding> resourceBuildings;

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
		resourceBuildings = new ArrayList<ResourceBuilding>();

		world = map;
		placingPosition = new Vector2f();
	}

	public void update(Vector2f translation, boolean active) {
		if (InputHandler.rightClicked())
			placingBuilding = false;


		if (placingBuilding && world.isBuildable(placingPosition.x, placingPosition.y, Farm.size.x, Farm.size.y) 
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
		for (ResourceBuilding b: resourceBuildings)
			//TODO figure out how to return resources
			b.update();
	}

	public void draw(Graphics g) {
		//draw all buildings
		for (ResourceBuilding b: resourceBuildings)
			b.draw(g);

	}

	public void buildBuilding() {
		ResourceBuilding building = getResourceBuilding(placingBuildingId);
		resourceBuildings.add(building);
		world.placeBuilding(placingPosition.x, placingPosition.y, building.getSize().x, building.getSize().y);
	}

	public void drawPlaced(Graphics g, Vector2f translate, boolean active) {
		//draw the building being placed if there is one
		if (placingBuilding) {
			//snaps the current position to the nearest tile
			if (active) {
				placingPosition = new Vector2f((float)Math.floor((Mouse.getX()+translate.x) / Map.TILE_SIZE)*Map.TILE_SIZE, 
						(float)Math.ceil(((Driver.screenHeight - Mouse.getY() - 2*Map.TILE_SIZE + translate.y)/Map.TILE_SIZE))*Map.TILE_SIZE);
			}
			//draws the selected building (set to farm for now) at the current world location snapped to the tile the cursor is over
			g.draw(placingBuildingId, new Rect(placingPosition, new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE)));
			//we want some sort of notification that a spot can't be built on - maybe tinting the building red?
		}
	}


	private ResourceBuilding getResourceBuilding(int placingBuildingId) {
		ResourceBuilding building = null;
		if (placingBuildingId == farmId) {
			building = new Farm(farmId, placingPosition, this);
		}
		else if (placingBuildingId == mineId) {
			building = new Mine(mineId, placingPosition, this);
		}
		else if (placingBuildingId == millId) {
			building = new Mill(millId, placingPosition, this);
		}
		else if (placingBuildingId == stableId) {
			building = new Stable(stableId, placingPosition, this);
		}
		else if (placingBuildingId == quarryId) {
			building = new Quarry(quarryId, placingPosition, this);
		}
		return building;
	}
}
