package game.city;

import java.util.Random;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.Building;
import game.buildings.BuildingType;
import game.entities.City;
import game.entities.MilitaryBuilding;
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

		for (int i = defenseBuildings.size()-1; i >=0;  i--) {
			if (defenseBuildings.get(i).isAlive())
				defenseBuildings.get(i).update();
			else
				defenseBuildings.remove(i);
		}
		
		updateProjectiles();

		return !HQ.isAlive();

	}

	public void doSomething() {
		/*
		 * find the most important structure to build
		 * check if there is room
		 * if not, delete the least important structure
		 * build the most important structure
		 * 
		 */
		
		
		//chose a building to make
		
		//make it
		if (!full) {
			build();
		}
	}

	protected void build() {
		int size = MILITARY_SIZE;
		int offset = MILITARY_OFFSET;
		BuildingType m = BUILDINGS[rGen.nextInt(size) + offset];
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
