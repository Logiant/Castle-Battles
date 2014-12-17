package game.city;

import game.entities.City;

import org.newdawn.slick.geom.Vector2f;

import widgets.Text;

/**
 * a class that managies the city - buildings and stuff
 * @author Logs
 *
 */
public class CityManager extends City {


	@Override
	public void setup() {
		super.setup();
		placeHQ(world.headquartersPos());
		buildWalls(world.getWalls());
	}

	@Override
	public boolean update(Vector2f translation, boolean active) {
		return super.update(translation, active);

	}

	@Override
	public void drawText() {
		super.drawText();
		Text.write("Food: " + resources.food, new Vector2f(15, 5));
		Text.write("Lumber: " + resources.lumber, new Vector2f(150, 5));
		Text.write("Metal: " + resources.metal, new Vector2f(15, 25));
		Text.write("Stone: " + resources.stone, new Vector2f(150, 25));
		Text.write("Horses: " + resources.horse, new Vector2f(15, 45));
		Text.write("Magic: " + resources.magic, new Vector2f(150, 45));
	}

}
