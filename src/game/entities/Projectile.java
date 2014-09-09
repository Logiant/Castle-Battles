package game.entities;

import graphics.Graphics;
import main.Time;

import org.newdawn.slick.geom.Vector2f;

public abstract class Projectile {

	protected int damage;
	protected Vector2f position;
	protected Vector2f targetPosition;
	protected Combat target;
	protected boolean homing;
	protected float speed;

	public Projectile(int damage, Vector2f position, Combat target, boolean homing, float speed) {
		this.damage = damage;
		this.position = position;
		this.target = target;
		this.homing = homing;
		this.speed = speed;
		targetPosition = target.getPosition();
	}

	public boolean update() {
		boolean hit = false;
		if (target == null) {
			return true;
		}
		if (homing && target.isAlive()) { //track the target, 
			targetPosition = target.getPosition();
		}
		Vector2f velocity = new Vector2f(targetPosition.x - position.x, targetPosition.y - position.y);
		velocity.normalise();
		Vector2f distance = new Vector2f(targetPosition.x - position.x, targetPosition.y - position.y);
		float zoom = (float)Math.min(speed * Time.dt/1000f, distance.length());
		velocity.scale(zoom);
		position.add(velocity);
		if (zoom < speed * Time.dt/1000f) {
			hit = true;
		}
		return hit;
	}

	public abstract void draw(Graphics g);

	public int getDamage() {
		return damage;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void doDamage() {
		System.out.println("Damaging " + target + " for " + damage);
		if (target != null) {
				target.damage(damage);
		}
	}
}
