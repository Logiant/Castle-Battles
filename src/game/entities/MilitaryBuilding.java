package game.entities;

import java.util.List;

import game.buildings.Building;
import game.buildings.City;
import main.Time;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all military buildings
 * @author beaverl
 *
 */

public abstract class MilitaryBuilding extends Building {

	protected Vector2f unitSize = new Vector2f(32, 32);
	
	public MilitaryBuilding(int textureId, Vector2f position, Vector2f size, City city) {
		super(textureId, position, size, city);
		time = cooldownTime;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			city.produceUnit(this, position, unitSize);
			time = cooldownTime;
		}
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
