package game.units;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.City;
import game.buildings.CityManager;
import game.entities.Unit;

public class Infantry extends Unit {

	public Infantry(int textureId, Vector2f position, Vector2f size,
			City city) {
		super(textureId, position, size, city);
	}

}
