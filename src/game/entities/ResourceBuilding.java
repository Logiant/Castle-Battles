package game.entities;

import game.buildings.Building;
import game.buildings.CityManager;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all resource buildings
 * @author beaverl
 *
 */

public abstract class ResourceBuilding extends Building {

	protected int resourceAmount;

	public ResourceBuilding(int textureId, Vector2f position, Vector2f size, CityManager city) {
		super(textureId, position, size, city);
	}

}
