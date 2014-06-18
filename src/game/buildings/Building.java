package game.buildings;

import game.buildings.CityManager;
import graphics.Graphics;
import graphics.Rect;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all buildings
 * contains position (and rotation?) data as well as helper methods
 * @author beaverl
 *
 */

public abstract class Building {

	//width and height should be tile values so we don't have awkward overlaps
		protected Vector2f position;
		protected int textureId;
		private Vector2f size;
		protected CityManager city;
		protected int cooldownTime = 10000;//seconds
		protected int time;
		
		public Building(int textureId, Vector2f position, Vector2f size, CityManager city) {
			this.textureId = textureId;
			this.position = position;
			this.size = size;
			this.city = city;
		}
		
		public void draw(Graphics g) {
			g.draw(textureId, new Rect(position, new Vector2f(size.x, size.y)));
		}
		
		public Vector2f getSize() {
			return new Vector2f(size);
		}
		
		public abstract void update();
}
