package game.buildings;

import java.util.Random;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.entities.DefenseBuilding;
import game.entities.MilitaryBuilding;
import game.entities.Unit;
import game.units.Infantry;
import game.world.Map;

/**
 * a class that manages the city - buildings and stuff
 * @author Logs
 *
 */
public class EnemyCityManager extends City{

	private float maxCD = 10000;
	private float cooldown = 0;
	private Random rGen = new Random();
	private boolean full = false;

	@Override
	public void setup() {
		super.setup();
		placeHQ(world.enemyHeadquartersPos());
		buildWalls(world.getEnemyWalls());
	}

	@Override
	public boolean update(Vector2f translation, boolean active) {
		collectResources();

		cooldown -= Time.dt;

		if (cooldown < 0) {
			cooldown = maxCD;
			doSomething();
		}

		for (int i = soldiers.size()-1; i >=0;  i--) {
			if (soldiers.get(i).isAlive())
				soldiers.get(i).update();
			else
				soldiers.remove(i);
		}

		for (DefenseBuilding d: defenseBuildings) {
			if (d.isAlive())
				d.update();
		}

		return !HQ.isAlive();

	}

	public void doSomething() {
		//chose a building to make
		if (!full) {
			build();
		}
		//make it
		Unit newUnit = new Infantry(infantryId, HQ.position, new Vector2f(32, 32), this);
		newUnit.setTarget(enemyTarget);
		soldiers.add(newUnit);
	}

	protected void build() {
		int size = MILITARY_SIZE;
		int offset = MILITARY_OFFSET;
		BUILDING m = BUILDINGS[rGen.nextInt(size) + offset];
		Vector2f position = world.getEnemySpace();
		if (position != null) {
			Building added = getBuildingFromEnum(m, position);
			militaryBuildings.add((MilitaryBuilding)added);
			world.placeBuilding(position.x, position.y, 2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
		} else {
			full = true;
		}
		//resource: farm, mine, mill, stable, quarry, magic
		//military: barracks, cavalry, range, arcanum
	}

	@Override
	public void drawText() {
		//do nothing
	}

}
