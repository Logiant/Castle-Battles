package game.buildings;

import game.buildings.defense.*;
import game.buildings.military.*;
import game.buildings.resource.*;
import game.city.NeutralCityManager;
import game.entities.City;
import game.entities.Combat;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

public class DummyBuilding extends Building {
	
	
	static City dummyCity = new NeutralCityManager();
	static Vector2f v = new Vector2f(0,0);
	Building dummy;
	
	public DummyBuilding() {
		this(0, v, v, dummyCity);
	}

	public DummyBuilding(int textureId, Vector2f position, Vector2f size, City city) {
		super(textureId, position, size, dummyCity);
	}
	
	public void setBuilding(BuildingType building) {
		switch(building) {
		case  Farm:
			dummy = new Farm(999999999, v, dummyCity);
			break;
		case MagicPump:
			dummy = new MagicPump(999999999, v, dummyCity);
			break;
		case Mill:
			dummy = new Mill(999999999, v, dummyCity);
			break;			
		case Mine:
			dummy = new Mine(999999999, v, dummyCity);
			break;
		case Quarry:
			dummy = new Quarry(999999999, v, dummyCity);
			break;
		case Stable:
			dummy = new Stable(999999999, v, dummyCity);
			break;
		case Arcanum:
			dummy = new Arcanum(999999999, v, dummyCity);
			break;
		case Barracks:
			dummy = new Barracks(999999999, v, dummyCity);
			break;
		case Cavalry:
			dummy = new Cavalry(999999999, v, dummyCity);
			break;
		case Range:
			dummy = new Range(999999999, v, dummyCity);
			break;
		case Pitfall:
			dummy = new Pitfall(999999999, v, dummyCity);
			break;
		case Wall:
			dummy = new Wall(999999999, v, dummyCity);
			break;
		}
	}

	@Override
	public void findTarget(List<Combat> c) {}
	@Override
	public void attack() {}
	@Override
	public float getRange() {return 0;}
	@Override
	public int getDamage() {return 0;}
	@Override
	public void update() {}

	@Override
	public ResourceHandler getCost() {
		if (dummy != null)
			return dummy.getCost();
		return null;
	}
	
	@Override
	public String toString() {
		return dummy + "";
	}
	
	@Override
	public String getTooltip() {
		if (dummy != null)
			return dummy.getTooltip();
		return "Null";
	}


}
