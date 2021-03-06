package game.buildings.military;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.BuildingType;
import game.buildings.ResourceHandler;
import game.entities.City;
import game.entities.MilitaryBuilding;
import game.world.Map;


/**
 * this is the quarry building
 * it produces the stone resource
 * @author Logs
 *
 */
public class Arcanum extends MilitaryBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
	
	public Arcanum(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		time = cooldownTime;
	}

	@Override
	public ResourceHandler getCost() {
		ResourceHandler rh = new ResourceHandler();
		rh.magic = 10;
		rh.lumber = 15;
		rh.stone = 5;
		return rh;
	}

	@Override
	public String toString() {
		return "Arcanum";
	}
	
	@Override
	public String getDescription() {
		return "Spawns Wizards";
	}
	
	@Override
	public BuildingType getType() {
		return BuildingType.Arcanum;
	}
}
