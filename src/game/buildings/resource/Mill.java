package game.buildings.resource;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.City;
import game.buildings.ResourceHandler;
import game.entities.ResourceBuilding;
import game.world.Map;


/**
 * this is the mill building
 * it produces the lumber resource
 * @author Logs
 *
 */
public class Mill extends ResourceBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
	
	public Mill(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		resourceAmount = 5;
		time = cooldownTime;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			city.addResource("LUMBER", resourceAmount);
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
		return "Lumber Mill";
	}
	
	@Override
	public String getDescription() {
		return "+" + resourceAmount + " Wood";
	}
}
