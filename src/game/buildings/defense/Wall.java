package game.buildings.defense;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.BuildingType;
import game.buildings.ResourceHandler;
import game.entities.City;
import game.entities.DefenseBuilding;
import game.world.Map;


/**
 * this is the quarry building
 * it produces the stone resource
 * @author Logs
 *
 */
public class Wall extends DefenseBuilding{
	
public static final Vector2f size = new Vector2f(1*Map.TILE_SIZE, 1*Map.TILE_SIZE);
	
	public Wall(int textureId, Vector2f position, City c) {
		super(textureId, position, size, c);
		time = cooldownTime;
		damage = 0;
		range = 0;
	}

	@Override
	public ResourceHandler getCost() {
		ResourceHandler rh = new ResourceHandler();
		rh.lumber = 2;
		rh.stone = 5;
		return rh;
	}

	@Override
	public String toString() {
		return "Wall";
	}
	
	@Override
	public String getDescription() {
		return "A Defensive Block";
	}
	
	@Override
	public BuildingType getType() {
		return BuildingType.Wall;
	}
}
