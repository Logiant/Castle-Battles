package game.entities;


import java.util.List;

import game.buildings.City;
import graphics.Graphics;
import graphics.Rect;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all units
 * Contains position and rotation values, as well as helper methods
 * @author beaverl
 *
 */
public abstract class Unit implements Combat {

	protected int textureId;
	protected Vector2f position;
	protected float speed;
	protected Vector2f size;
	protected Vector2f targetPos;
	protected City city;
	protected Combat targetEnemy;

	protected float range;
	protected int damage;
	protected int health;

	protected boolean moving;


	public Unit(int textureId, Vector2f position, Vector2f size, City city) {
		this.textureId = textureId;
		this.position = position;
		this.size = size;
		this.city = city;
		speed = 0.25f;
		damage = 1;
		range = 5;
		targetPos= position;
	}

	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, new Vector2f(size.x, size.y)));
	}

	public Vector2f getSize() {
		return new Vector2f(size);
	}

	public void update() {
		Vector2f distance = new Vector2f(targetPos.x - position.x, targetPos.y - position.y);
		if (targetEnemy != null) { //move forward for the emeperor if theres a target enemy
			distance = new Vector2f(targetEnemy.getPosition().x - position.x, targetEnemy.getPosition().y - position.y);
		}
		if (distance.lengthSquared() <= speed*speed) {
			position = new Vector2f(targetPos);
			moving = false;
		} else {
			distance.normalise();
			distance.scale(speed);
			position = new Vector2f(position.x + distance.x, position.y + distance.y);
			moving = true;
		}
		attack();

	}

	public boolean isMoving() {
		return moving;
	}

	public void setTarget(Vector2f target) {
		this.targetPos= target;
	}

	@Override
	public void findTarget(List<Combat> other) {
		if (other.size() > 0) {
			targetEnemy = other.get(0);
		}
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

	@Override
	public Vector2f getPosition() {
		return new Vector2f(position);
	}

	@Override
	public void damage(int dmg) {
		health -= damage;
	}

	@Override
	public boolean isAlive() {
		return health > 0;
	}

}
