package game.buildings.resource;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.CityManager;
import game.entities.ResourceBuilding;
import game.world.Map;

/**
 * this is the mine building
 * it produces the iron resource
 * @author Logs
 *
 */
public class Mine extends ResourceBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);


	public Mine(int textureId, Vector2f position, CityManager city) {
		super(textureId, position, size, city);
		resourceAmount = 5;
		time = cooldownTime;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			city.addResource("METAL", resourceAmount);
			time = cooldownTime;
		}
	}
}
