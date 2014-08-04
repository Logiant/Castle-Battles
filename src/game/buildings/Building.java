package game.buildings;

import game.entities.Combat;
import graphics.Graphics;
import graphics.Rect;

import org.newdawn.slick.geom.Vector2f;

/**
 * Highest level entity for all buildings
 * contains position (and rotation?) data as well as helper methods
 * @author beaverl
 *
 */

public abstract class Building implements Combat {

	//width and height should be tile values so we don't have awkward overlaps
		protected Vector2f position;
		protected int textureId;
		private Vector2f size;
		protected City city;
		protected int cooldownTime = 10000;//seconds
		protected int time;
		
		protected int maxHP;
		protected int health;
		
		public Building(int textureId, Vector2f position, Vector2f size, City city) {
			this.textureId = textureId;
			this.position = new Vector2f(position);
			this.size = new Vector2f(size);
			this.city = city;
			
			health = 5;
		}
		
		public int getHP() {
			return health;
		}
		
		public void setMaxHP(int maxHP) {
			this.maxHP = maxHP;
		}
		
		public Vector2f getPosition() {
			return new Vector2f(position);
		}
		
		public void draw(Graphics g) {
			g.draw(textureId, new Rect(position, new Vector2f(size.x, size.y)));
		}
		
		public Vector2f getSize() {
			return new Vector2f(size);
		}
		
		public abstract void update();
		
		public abstract ResourceHandler getCost();
		
		@Override
		public void damage(int amount) {
			health -= amount;
		}
		
		@Override
		public boolean isAlive() {
			return health > 0;
		}
}
