package game.entities;

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
		
		public Building(int textureId, Vector2f position) {
			this.textureId = textureId;
			this.position = position;
		}
		
		public abstract void draw(Graphics g);
		
		public abstract void update();
}
