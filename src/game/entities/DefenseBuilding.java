package game.entities;

import java.util.List;

import game.buildings.Building;
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
	protected int damage;
	
	protected Combat targetEnemy;
	
	public DefenseBuilding(int textureId, Vector2f position, Vector2f size, City city) {
		super(textureId, position, size, city);
		time = cooldownTime;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			
			time = cooldownTime;
		}
	}

	@Override
	public void findTarget(List<Combat> other) {
		//TODO find the nearest other
	}
	
	@Override
	public void attack() {
		if (targetEnemy != null) {
			Vector2f distance = new Vector2f(targetEnemy.getPosition().x - position.x, targetEnemy.getPosition().y - position.y);
			if (distance.lengthSquared() <= range*range) {
				targetEnemy.damage(damage);
			}
		}
	}
	
	@Override
	public float getRange() {
		return range;
	}
	
	@Override
	public int getDamage() {
		return damage;
	}
	
}
