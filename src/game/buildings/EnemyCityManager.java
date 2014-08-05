package game.buildings;

import main.Time;

import org.newdawn.slick.geom.Vector2f;

import game.entities.DefenseBuilding;
import game.entities.Unit;
import game.units.Infantry;

/**
 * a class that managies the city - buildings and stuff
 * @author Logs
 *
 */
public class EnemyCityManager extends City{

	private float maxCD = 5000;
	private float cooldown = 5000;

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
			Unit u = new Infantry(infantryId, HQ.getPosition(), new Vector2f(32, 32), this);
			u.setTarget(enemyTarget);
			soldiers.add(u);
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

	@Override
	public void drawText() {
		//do nothing
	}

}
