package game.units;

import org.newdawn.slick.geom.Vector2f;

import game.entities.City;
import game.entities.Unit;
import game.projectiles.Arrow;

public class Archer extends Unit {

	public Archer(int textureId, Vector2f position, Vector2f size,
			City city) {
		super(textureId, position, size, city);
		range = aggroRange;
	}

	@Override
	public void attack() {
		if (coolDown <= 0 && targetEnemy != null) {
			coolDown = attackSpeed;
			//create an arrow at this position, pointed at ze enemy!
			Vector2f distance = new Vector2f(targetEnemy.getPosition().x - position.x, targetEnemy.getPosition().y - position.y);
			if (distance.lengthSquared() <= range*range) {
				city.addProjectile(new Arrow(new Vector2f(position), targetEnemy));
			}
		}
	}

	
}
