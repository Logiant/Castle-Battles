package game.buildings;

import org.newdawn.slick.geom.Vector2f;
import game.entities.DefenseBuilding;
import game.entities.Unit;
import graphics.Graphics;

/**
 * a class that managies the city - buildings and stuff
 * @author Logs
 *
 */
public class EnemyCityManager extends City{


	@Override
	public void setup() {
		super.setup();
		placeHQ(world.enemyHeadquartersPos());
		buildWalls(world.getEnemyWalls());
	}

	@Override
	public void update(Vector2f translation, boolean active) {
		collectResources();

		for (Unit u:soldiers)
			u.update();
		
		for (DefenseBuilding d: defenseBuildings) {
			d.update();
		}

	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void drawText() {
		//do nothing
	}

}
