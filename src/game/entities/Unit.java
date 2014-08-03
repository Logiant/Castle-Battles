package game.entities;

import java.util.Random;

import game.buildings.City;
import game.buildings.CityManager;
import graphics.Graphics;
import graphics.Rect;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all units
 * Contains position and rotation values, as well as helper methods
 * @author beaverl
 *
 */
public abstract class Unit {
	
	protected int textureId;
	protected Vector2f position;
	protected float speed;
	protected Vector2f size;
	protected Vector2f target;
	protected City city;
	
	protected boolean moving;
	

	public Unit(int textureId, Vector2f position, Vector2f size, City city) {
		this.textureId = textureId;
		this.position = position;
		this.size = size;
		this.city = city;
		speed = 0.25f;
		target = position;
	}
	
	public void draw(Graphics g) {
		g.draw(textureId, new Rect(position, new Vector2f(size.x, size.y)));
	}
	
	public Vector2f getSize() {
		return new Vector2f(size);
	}
	
	public void update() {
		Vector2f distance = new Vector2f(target.x - position.x, target.y - position.y);
		if (distance.lengthSquared() <= speed*speed) {
			position = new Vector2f(target);
			moving = false;
		} else {
			distance.normalise();
			distance.scale(speed);
			position = new Vector2f(position.x + distance.x, position.y + distance.y);
			moving = true;
		}
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setTarget(Vector2f target) {
		this.target = target;
	}
}
