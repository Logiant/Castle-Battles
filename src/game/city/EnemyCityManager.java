package game.city;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.Building;
import game.buildings.BuildingType;
import game.buildings.CostCheck;
import game.entities.City;
import game.entities.MilitaryBuilding;
import game.entities.ResourceBuilding;
import game.world.Map;

/**
 * a class that manages the city - buildings and stuff
 * @author Logs
 *
 */
public class EnemyCityManager extends City{

	private float maxCD = 2500;
	private float cooldown;
	private boolean full;
	private boolean militarized;

	@Override
	public void setup() {
		super.setup();
		placeHQ(world.enemyHeadquartersPos());
		buildWalls(world.getEnemyWalls());
		CostCheck.shuffle();
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
		if (full && resourceBuildings.size() > 0) { //if we can, destroy a resource building
			System.out.println("removed " + resourceBuildings.get(0) + " , militarized.");
			this.deleteBuilding(resourceBuildings.get(0).getPosition());
			militarized = true;
			full = false;
		}
		if (!full) {
			BuildingType toBuild = null;
			if (militaryBuildings.size() > resourceBuildings.size() * 2 && !militarized) {
				toBuild = CostCheck.resource(resources);
				if (toBuild == null)
					toBuild = CostCheck.military(resources);
			} else {
				toBuild = CostCheck.military(resources);
				if (toBuild == null)
					toBuild = CostCheck.resource(resources);
			}
			//make it
			if (toBuild != null) {
				build(toBuild);
				full = full || militarized; //we are full if we filled up or if we militarized
			}
		}
	}

	protected void build(BuildingType m) {
		Vector2f position = world.getEnemySpace();
		if (position != null) {
			Building added = getBuildingFromEnum(m, position);
			if (added instanceof MilitaryBuilding)
				militaryBuildings.add((MilitaryBuilding)added);
			else if (added instanceof ResourceBuilding)
				resourceBuildings.add((ResourceBuilding)added);
			else
				System.out.println("Defense? We shouldn't be here");

			world.placeBuilding(position.x, position.y, 2*Map.TILE_SIZE, 2*Map.TILE_SIZE);
			resources.remove(added.getCost());
		} else {
			full = true;
		}
	}
	
	@Override
	public void drawText() {
		//do nothing
	}

}
