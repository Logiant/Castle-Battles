package game.buildings.defense;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.City;
import game.buildings.ResourceHandler;
import game.entities.DefenseBuilding;
import game.world.Map;


/**
 * this is the quarry building
 * it produces the stone resource
 * @author Logs
 *
 */
public class Pitfall extends DefenseBuilding{
	
public static final Vector2f size = new Vector2f(1*Map.TILE_SIZE, 1*Map.TILE_SIZE);
	
	public Pitfall(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		time = cooldownTime;
		damage = 1;
		range = 1;
	}

	@Override
	public ResourceHandler getCost() {
		ResourceHandler rh = new ResourceHandler();
		rh.lumber = 5;
		rh.stone = 2;
		return rh;
	}

	@Override
	public String toString() {
		return "Pitfall Trap";
	}
	
	@Override
	public String getDescription() {
		return "As seen on TV";
	}
}
