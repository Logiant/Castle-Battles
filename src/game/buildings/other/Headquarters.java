package game.buildings.other;

import java.util.List;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.Building;
import game.buildings.City;
import game.buildings.ResourceHandler;
import game.entities.Combat;
import game.world.Map;


/**
 * this is the Headquarters building
 * it produces all resources
 * @author Logs
 *
 */
public class Headquarters extends Building{
	
	public static final Vector2f size = new Vector2f(1*Map.TILE_SIZE, 1*Map.TILE_SIZE);
	private int resourceAmount = 5;
	
	public Headquarters(int textureId, Vector2f position, City city) {
		super(textureId, position, size, city);
		time = cooldownTime;
		health = 10;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public void update() {
		time -= Time.dt;
		if (time <= 0) {
			city.addResource("ALL", resourceAmount);
			time = cooldownTime;
		}
	}
	
	@Override
	public ResourceHandler getCost() {
		ResourceHandler rh = new ResourceHandler();
		return rh;
	}
	
	@Override
	public float getRange() {
		return 0;
	}
	
	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public void findTarget(List<Combat> c) {
		//can't fight
	}

	@Override
	public void attack() {
		//can't fight
	}
}
