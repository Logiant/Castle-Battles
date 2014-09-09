package game.entities;


import java.util.List;

import graphics.Graphics;
import graphics.Rect;
import main.Time;

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
	protected float attackSpeed; //attacks per second
	protected float coolDown;

	protected boolean moving;
	protected float aggroRange;


	public Unit(int textureId, Vector2f position, Vector2f size, City city) {
		this.textureId = textureId;
		this.position = new Vector2f(position);
		this.size = new Vector2f(size);
		this.city = city;
		speed = 32; // px/s
		damage = 5;
		range = 5;
		health = 5;
		aggroRange = 128; // about 2 tiles
		attackSpeed = .5f;
		targetPos = new Vector2f();
	}

	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, new Vector2f(size.x, size.y)));
	}

	public Vector2f getSize() {
		return new Vector2f(size);
	}

	public void update() {
		float dt = (float) (Time.dt / 1000.0);
		coolDown -= dt;
		Vector2f distance = new Vector2f(targetPos.x - position.x, targetPos.y - position.y);
		if (targetEnemy != null) { //move forward for the emeperor if theres a target enemy
			distance = new Vector2f(targetEnemy.getPosition().x - position.x, targetEnemy.getPosition().y - position.y);
		}
		if (distance.lengthSquared() <= speed*speed * dt * dt) {
			position.add(distance);
			moving = false;
		} else {
			distance.normalise();
			distance.scale(speed * dt);
			position.add(distance);
			moving = true;
		}
		attack();
	}

	public boolean isMoving() {
		return moving;
	}

	public void setTarget(Vector2f target) {
		this.targetPos= new Vector2f(target);
	}

	@Override
	public void findTarget(List<Combat> other) {
		int combatIndex = -1;
		float nearest = aggroRange + 1;
		for (int i = 0; i < other.size(); i++) {
			Vector2f distance = new Vector2f(other.get(i).getPosition().x - position.x, other.get(i).getPosition().y - position.y);
			float dist = distance.length();
			if (dist < nearest) {
				combatIndex = i;
				nearest = dist;
			}
		}
		if (combatIndex >=0)
			targetEnemy = other.get(combatIndex);
		else
			targetEnemy = null;
	}

	@Override
	public int getHealth() {
		return health;
	}
	
	@Override
	public void attack() {
		if (coolDown <= 0 && targetEnemy != null) {
			coolDown = attackSpeed;
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
		health -= dmg;
	}

	@Override
	public boolean isAlive() {
		return health > 0;
	}

}
