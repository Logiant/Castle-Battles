package widgets;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.Building;
import graphics.Graphics;
import graphics.Rect;

public class BuildingTooltip {

	//the current building tooltip to display;
	public Building target;
	private Vector2f position;
	private Vector2f size;
	private int id;
	
	public BuildingTooltip(int id) {
		this(new Vector2f(0,0), new Vector2f(50, 50), id);
	}
	
	public BuildingTooltip(Vector2f position, Vector2f size, int id) {
		this.position = position;
		this.size = size;
		this.id = id;
	}
	
	public void draw(Graphics g) {
		if (target != null) { //if we have a target
			g.draw(id, new Rect(position, size));
			
		}
	}
	
	public void drawText() {
		if (target != null) {
			Vector2f drawPos = new Vector2f(position.x + 5, position.y);
			Text.writeSmall(target.getTooltip(), drawPos);
		}
	}
	
	public void setPosition(Vector2f pos) {
		position = pos;
	}
	
	public void setSize(Vector2f size) {
		this.size = size;
	}
}
