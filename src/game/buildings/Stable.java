package game.buildings;

import org.newdawn.slick.geom.Vector2f;

import game.entities.Building;
import game.world.Map;
import graphics.Graphics;
import graphics.Rect;

/**
 * this is the farm building
 * it produces the food resource
 * @author Logs
 *
 */
public class Stable extends Building{
	
	public Stable(int textureId, Vector2f position) {
		super(textureId, position);
	}

	static final float width = 2*Map.TILE_SIZE;
	static final float height = 2*Map.TILE_SIZE;
	
	public void update() {
		//TODO figure out how much food is produced, handle click events, etc
	}
	
	@Override
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, new Vector2f(width, height)));
	}
	
	public static Vector2f getSize() {
		return new Vector2f(width, height);
	}
}
