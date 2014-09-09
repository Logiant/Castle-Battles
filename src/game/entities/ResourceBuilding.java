package game.entities;

import java.util.List;

import game.buildings.Building;

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
	
	@Override
	public void findTarget(List<Combat> other) {
		//do nothing, you can't fight!
	}
	
	@Override
	public void attack() {
		//do nothing, you can't attack!
	}
	
	@Override
	public float getRange() {
		return 0;
	}
	
	@Override
	public int getDamage() {
		return 0;
	}
}
