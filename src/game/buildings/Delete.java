package game.buildings;

import java.util.List;

import game.entities.City;
import game.entities.Combat;

import org.newdawn.slick.geom.Vector2f;

public class Delete extends Building {

	public Delete(int textureId, Vector2f position, Vector2f size, City city) {
		super(textureId, position, size, city);
	}

	@Override //do nothing
	public void findTarget(List<Combat> c) {}
		
	@Override
	public void attack() {}

	@Override
	public float getRange() {return 0;}

	@Override
	public int getDamage() {return 0;}

	@Override
	public void update() {}

	@Override
	public ResourceHandler getCost() {return null;}

	@Override
	public BuildingType getType() {return BuildingType.Delete;}

}
