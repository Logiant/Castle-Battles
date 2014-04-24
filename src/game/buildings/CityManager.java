package game.buildings;

import java.util.ArrayList;
import java.util.List;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

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

	int farmId;
	int buildKey = Keyboard.getKeyIndex("B");

	boolean placingBuilding;
	int placingBuildingId;
	Vector2f placingPosition;
	Map world;
	
	public void initialize(Graphics g, Map map) {
		farmId = g.loadImage("farm");
		farms = new ArrayList<Farm>();

		world = map;
		placingPosition = new Vector2f();
	}

	public void update(Vector2f translation, boolean active) {
		//check to see if the player is placing a building --- this will eventually be handled by UI
		if (InputHandler.wasKeyPressed(buildKey)) {
			placingBuilding = !placingBuilding;
			placingBuildingId = farmId; //this will eventually be returned from a BuildingButton
		}
		
		if (placingBuilding && world.isBuildable(placingPosition.x, placingPosition.y, Farm.width, Farm.height) 
				&& active && InputHandler.leftClicked()) { //click to place a building
			farms.add(new Farm(farmId, placingPosition));
			world.placeBuilding(placingPosition.x, placingPosition.y, Farm.width, Farm.height);
		}
		
		collectResources();

	}

	private void collectResources() { //this will eventually get resources from buildings
		for (Farm f: farms)
			f.update();
	}

	public void draw(Graphics g) {
		//draw all buildings
		for (Farm f: farms)
			f.draw(g);

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
