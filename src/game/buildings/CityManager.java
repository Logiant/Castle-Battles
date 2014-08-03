package game.buildings;

import graphics.Graphics;

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
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void drawText() {
		Text.write("Food: " + food, new Vector2f(5, 5));
		Text.write("Lumber: " + lumber, new Vector2f(5, 25));
		Text.write("Metal: " + metal, new Vector2f(5, 45));
		Text.write("Stone: " + stone, new Vector2f(5, 65));
		Text.write("Horses: " + horse, new Vector2f(5, 85));
		Text.write("Magic: " + magic, new Vector2f(5, 105));
	}

}
