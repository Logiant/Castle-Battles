package game.buildings.military;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.CityManager;
import game.entities.MilitaryBuilding;
import game.world.Map;


/**
 * this is the mill building
 * it produces the lumber resource
 * @author Logs
 *
 */
public class Cavalry extends MilitaryBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);

	
	public Cavalry(int textureId, Vector2f position, CityManager city) {
		super(textureId, position, size, city);
		time = cooldownTime;
		unitSize = new Vector2f(64, 32);
	}
	
}
