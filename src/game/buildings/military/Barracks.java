package game.buildings.military;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.BuildingType;
import game.buildings.ResourceHandler;
import game.entities.City;
import game.entities.MilitaryBuilding;
import game.world.Map;


/**
 * this is the farm building
 * it produces the food resource
 * @author Logs
 *
 */
public class Barracks extends MilitaryBuilding {
	
	public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
	
	public Barracks(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		time = cooldownTime;
	}
	
	@Override
	public ResourceHandler getCost() {
		ResourceHandler rh = new ResourceHandler();
		rh.food = 10;
		rh.metal = 15;
		rh.stone = 5;
		return rh;
	}
	
	@Override
	public String toString() {
		return "Barracks";
	}
	
	@Override
	public String getDescription() {
		return "Spawns Infantry";
	}
	
	@Override
	public BuildingType getType() {
		return BuildingType.Barracks;
	}
}
