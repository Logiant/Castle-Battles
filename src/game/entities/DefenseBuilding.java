package game.entities;

import game.buildings.Building;
import game.buildings.CityManager;
import main.Time;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all defense buildings
 * @author beaverl
 *
 */

public abstract class DefenseBuilding extends Building {

	protected Vector2f unitSize = new Vector2f(32, 32);
	protected float range;
	protected float damage;
	
	public DefenseBuilding(int textureId, Vector2f position, Vector2f size, CityManager city) {
		super(textureId, position, size, city);
		time = cooldownTime;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			
			time = cooldownTime;
		}
	}

}
