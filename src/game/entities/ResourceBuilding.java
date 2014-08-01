package game.entities;

import game.buildings.Building;
import game.buildings.City;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all resource buildings
 * @author beaverl
 *
 */

public abstract class ResourceBuilding extends Building {

	protected int resourceAmount;

	public ResourceBuilding(int textureId, Vector2f position, Vector2f size, City city) {
		super(textureId, position, size, city);
	}

}
