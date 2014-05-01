package game.buildings;

import org.newdawn.slick.geom.Vector2f;

import game.entities.ResourceBuilding;
import game.world.Map;


/**
 * this is the quarry building
 * it produces the stone resource
 * @author Logs
 *
 */
public class Quarry extends ResourceBuilding{
	
public static final Vector2f size = new Vector2f(2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
	
	public Quarry(int textureId, Vector2f position, CityManager city) {
		super(textureId, position, size, city);
	}
	
	public void update() {
		//TODO figure out how much stone is produced, handle click events, etc
		//city.addStone(5);
	}
}
