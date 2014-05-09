package game.buildings;

import java.util.ArrayList;
import java.util.List;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import widgets.Text;
import game.buildings.military.*;
import game.buildings.resource.*;
import game.buildings.resource.Stable;
import game.entities.MilitaryBuilding;
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
	
	//do we want to put these in an int[] to save space?
	int lumber;
	int stone;
	int food;
	int metal;
	int horse;
	int magic;


	List<ResourceBuilding> resourceBuildings;
	List<MilitaryBuilding> militaryBuildings;

	//do we want to put these in an int[] to save space?
	int farmId;
	int mineId;
	int millId;
	int stableId;
	int quarryId;
	int magicId;
	//military
	int barracksId;
	int cavalryId;
	int rangeId;
	int arcanumId;

	boolean placingBuilding;
	int placingBuildingId;
	Vector2f placingPosition;
	Map world;

	public void initialize(Graphics g, Map map) {
		farmId = g.loadImage("Buildings/Farm");
		mineId = g.loadImage("Buildings/Mine");
		millId = g.loadImage("Buildings/Lumber");
		stableId = g.loadImage("Buildings/Stable");
		quarryId = g.loadImage("Buildings/Quarry");
		magicId = g.loadImage("Buildings/MagicPump");
		resourceBuildings = new ArrayList<ResourceBuilding>();
		//military
		barracksId = g.loadImage("Buildings/barracks");
		cavalryId = g.loadImage("Buildings/cavalry");
		rangeId = g.loadImage("Buildings/range");
		arcanumId = g.loadImage("Buildings/arcanum");
		militaryBuildings = new ArrayList<MilitaryBuilding>();

		world = map;
		placingPosition = new Vector2f();
	}

	public void update(Vector2f translation, boolean active) {
		if (InputHandler.rightClicked())
			placingBuilding = false;


		if (placingBuilding && world.isBuildable(placingPosition.x, placingPosition.y, getBuilding(placingBuildingId).getSize().x, getBuilding(placingBuildingId).getSize().y) 
				&& active && InputHandler.leftClicked() && !UI.containsMouse()) { //click to place a building
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
		case "MAGIC":
			placingBuildingId = magicId;
			placingBuilding = true;
			break;
		case "BARRACKS":
			placingBuildingId = barracksId;
			placingBuilding = true;
			break;
		case "RANGE":
			placingBuildingId = rangeId;
			placingBuilding = true;
			break;
		case "STABLE":
			placingBuildingId = cavalryId;
			placingBuilding = true;
			break;
		case "ARCANUM":
			placingBuildingId = arcanumId;
			placingBuilding = true;
			break;
		}
	}

	private void collectResources() {
		for (ResourceBuilding b: resourceBuildings)
			b.update();
		for (MilitaryBuilding m: militaryBuildings)
			m.update();
	}

	public void draw(Graphics g) {
		//draw all buildings
		for (ResourceBuilding b: resourceBuildings)
			b.draw(g);
		for (MilitaryBuilding m: militaryBuildings)
			m.draw(g);
		//draw all units
	}
	
	public void drawText() {
		Text.write("Food: " + food, new Vector2f(5, 5));
		Text.write("Lumber: " + lumber, new Vector2f(5, 25));
		Text.write("Metal: " + metal, new Vector2f(5, 45));
		Text.write("Stone: " + stone, new Vector2f(5, 65));
		Text.write("Horses: " + horse, new Vector2f(5, 85));
		Text.write("Magic: " + magic, new Vector2f(5, 105));
	}

	public void buildBuilding() {
		Building building = getBuilding(placingBuildingId);
		if (building instanceof ResourceBuilding)
			resourceBuildings.add((ResourceBuilding)building);
		if (building instanceof MilitaryBuilding)
			militaryBuildings.add((MilitaryBuilding)building);

		world.placeBuilding(placingPosition.x, placingPosition.y, building.getSize().x, building.getSize().y);
	}

	public void drawPlaced(Graphics g, Vector2f translate, boolean active) {
		//draw the building being placed if there is one
		if (placingBuilding) {
			//snaps the current position to the nearest tile
			if (active) {
				placingPosition = new Vector2f((float)Math.floor((Mouse.getX()+translate.x) / Map.TILE_SIZE)*Map.TILE_SIZE, 
						(float)Math.ceil(((Driver.screenHeight - Mouse.getY() - getBuilding(placingBuildingId).getSize().y + translate.y)/Map.TILE_SIZE))*Map.TILE_SIZE);
			}
			//draws the selected building (set to farm for now) at the current world location snapped to the tile the cursor is over
			g.draw(placingBuildingId, new Rect(placingPosition, new Vector2f(getBuilding(placingBuildingId).getSize().x, getBuilding(placingBuildingId).getSize().y)));
			//we want some sort of notification that a spot can't be built on - maybe tinting the building red?
		}
	}

	
	public void addResource(String type, int qty) {
		switch (type) {
		case "FOOD":
			food += qty;
			break;
		case "LUMBER":
			lumber += qty;
			break;
		case "METAL":
			metal += qty;
			break;
		case "STONE":
			stone += qty;
			break;
		case "HORSE":
			horse += qty;
			break;
		case "MAGIC":
			magic += qty;
			break;
		}
		
	}
	
	private Building getBuilding(int id) {
		Building building = null;
		if (id == farmId || id == mineId || id == millId || id == stableId || id == quarryId || id == magicId) {
			building = getResourceBuilding(id);
		} else if (id == barracksId || id == cavalryId || id == rangeId || id == arcanumId) {
			building = getMilitaryBuilding(id);
		}
		return building;
	}
	
	private MilitaryBuilding getMilitaryBuilding(int placingBuildingId) {
		MilitaryBuilding building = null;
		if (placingBuildingId == barracksId) {
			building = new Barracks(barracksId, placingPosition, this);
		}
		else if (placingBuildingId == cavalryId) {
			building = new Cavalry(cavalryId, placingPosition, this);
		}
		else if (placingBuildingId == rangeId) {
			building = new Range(rangeId, placingPosition, this);
		}
		else if (placingBuildingId == arcanumId) {
			building = new Arcanum(arcanumId, placingPosition, this);
		}
		return building;
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
		else if (placingBuildingId == quarryId) { //A hack to not crash the game when a military building is placed!
			building = new Quarry(quarryId, placingPosition, this);
		}
		else if (placingBuildingId == magicId) {
			building = new MagicPump(magicId, placingPosition, this);
		}
		return building;
	}
}
