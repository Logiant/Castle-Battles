package game.buildings.military;


import org.newdawn.slick.geom.Vector2f;

import game.buildings.City;
import game.buildings.CityManager;
import game.buildings.ResourceHandler;
import game.entities.MilitaryBuilding;
import game.world.Map;

/**
 * this is the mine building
 * it produces the iron resource
 * @author Logs
 *
 */
public class Range extends MilitaryBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);


	public Range(int textureId, Vector2f position, City city) {
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

}
