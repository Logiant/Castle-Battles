package game.buildings.resource;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.BuildingType;
import game.buildings.ResourceHandler;
import game.entities.City;
import game.entities.ResourceBuilding;
import game.world.Map;


/**
 * this is the farm building
 * it produces the food resource
 * @author Logs
 *
 */
public class Farm extends ResourceBuilding{
	
	public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
	
	public Farm(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		production = new ResourceHandler();
		production.food = 5;
		time = cooldownTime;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			city.addResource(production);
			time = cooldownTime;
		}
	}
	
	@Override
	public ResourceHandler getCost() {
		ResourceHandler rh = new ResourceHandler();
		rh.lumber = 15;
		rh.stone = 5;
		return rh;
	}
	
	@Override
	public String toString() {
		return "Farm";
	}
	
	@Override
	public String getDescription() {
		return "+" + production.food + " Food";
	}
	
	@Override
	public BuildingType getType() {
		return BuildingType.Farm;
	}
}
