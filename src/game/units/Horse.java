package game.units;

import org.newdawn.slick.geom.Vector2f;

import game.entities.City;
import game.entities.Unit;

public class Horse extends Unit {

	public Horse(int textureId, Vector2f position, Vector2f size,
			City city) {
		super(textureId, position, size, city);
		speed *= 1.5f;
	}


}
