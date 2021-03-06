package game.buildings.resource;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.BuildingType;
import game.buildings.ResourceHandler;
import game.entities.City;
import game.entities.ResourceBuilding;
import game.world.Map;

/**
 * this is the stable building
 * it produces the horse resource
 * @author Logs
 *
 */
public class Stable extends ResourceBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
	
	public Stable(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		production = new ResourceHandler();
		production.horse = 5;
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
		rh.food = 10;
		rh.horse = 15;
		rh.stone = 5;
		return rh;
	}
	
	@Override
	public String toString() {
		return "Stable";
	}
	
	@Override
	public String getDescription() {
		return "+" + production.horse + " Horses";
	}
	
	@Override
	public BuildingType getType() {
		return BuildingType.Stable;
	}
}
