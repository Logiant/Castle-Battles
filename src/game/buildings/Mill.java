package game.buildings;

import org.newdawn.slick.geom.Vector2f;

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
	
	public Mill(int textureId, Vector2f position, CityManager city) {
		super(textureId, position, size, city);
	}
	
	public void update() {
		//TODO figure out how much wood is produced, handle click events, etc
		//city.addLumber(5);
	}
}
