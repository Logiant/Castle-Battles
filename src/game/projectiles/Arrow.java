package game.projectiles;

import org.newdawn.slick.geom.Vector2f;

import game.entities.Combat;
import game.entities.Projectile;
import graphics.Graphics;
import graphics.Rect;

public class Arrow extends Projectile {

	public Arrow(int damage, Vector2f position, Combat target, float speed) {
		super(damage, position, target, true, speed);
	}
	
	public Arrow(Vector2f position, Combat target) {
		this(1, position, target, 200);
	}

	@Override
	public void draw(Graphics g) {
		g.draw(0, new Rect(position.x,position.y,10,10));
	}
	
}
