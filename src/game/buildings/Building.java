package game.buildings;

import game.entities.City;
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
	
	
/*	public static BuildingTypes fromString(String bld) {
		BuildingTypes b = null;
		if (bld.equals("FARM"))
			b = BuildingTypes.Farm;
		else if (bld.equals("MAGIC"))
			b = BuildingTypes.MagicPump;
		else if (bld.equals("WOOD"))
			b = BuildingTypes.Mill;
		else if (bld.equals("MINE"))
			b = BuildingTypes.Mine;
		else if (bld.equals("STONE"))
			b = BuildingTypes.Quarry;
		else if (bld.equals("HORSE"))
			b = BuildingTypes.Stable;
		else if (bld.equals("ARCANUM"))
			b = BuildingTypes.Arcanum;
		else if (bld.equals("BARRACKS"))
			b = BuildingTypes.Barracks;
		else if (bld.equals("STABLE"))
			b = BuildingTypes.Cavalry;
		else if (bld.equals("RANGE"))
			b = BuildingTypes.Range;
		else if (bld.equals("PITFALL"))
			b = BuildingTypes.Pitfall;
		else if (bld.equals("WALL"))
			b = BuildingTypes.Wall;
		
		
		
		
		return b;
	}
*/
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
		
		public String getTooltip() {
			//Name:  Brothel
			//A description about the building
			//it's so witty!
			//--intentionally left blank--
			//Food: 250
			//Lumber: 350
			//Metal:   ,Partaaay!
			//Stone: \o/
			//Horses: \
			//Magic:  /\
			String tooltip = "Name: " + this + "\n"+ this.getDescription() + "\n\n";
			ResourceHandler cost = this.getCost();
			tooltip += "Food: " + cost.food + "  ";
			tooltip += "Lumber: " + cost.lumber + "\n";
			tooltip += "Metal: " + cost.metal + "  ";
			tooltip += "Stone: " + cost.stone + "\n";
			tooltip += "Horses: " + cost.horse + " ";
			tooltip += "Magic: " + cost.magic + "\n";

			return tooltip;
		}
		
		public String getDescription() {
			return "Default Descriptor";
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
		
		public abstract BuildingType getType();
		
		@Override
		public int getHealth() {
			return health;
		}
		
		@Override
		public void damage(int amount) {
			health -= amount;
		}
		
		@Override
		public boolean isAlive() {
			return health > 0;
		}
		
		@Override
		public String toString() {
			return "A Building";
		}
}
