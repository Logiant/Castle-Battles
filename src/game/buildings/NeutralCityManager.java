package game.buildings;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import game.entities.DefenseBuilding;
import graphics.Graphics;

/**
 * a class that managies the city - buildings and stuff
 * @author Logs
 *
 */
public class NeutralCityManager extends City{


	@Override
	public void setup() {
		defenseBuildings = new ArrayList<DefenseBuilding>();
		buildWalls(world.getNeutralWalls());
	}

	@Override
	public boolean update(Vector2f translation, boolean active) {	
		for (DefenseBuilding d: defenseBuildings)
			d.update();
		return false;
	}

	@Override
	public void draw(Graphics g) {
		for (DefenseBuilding d: defenseBuildings)
			d.draw(g);
	}

	@Override
	public void drawText() {
		//do nothing
	}

}
