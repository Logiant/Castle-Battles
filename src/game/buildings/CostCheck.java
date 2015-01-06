package game.buildings;

import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

import game.buildings.military.*;
import game.buildings.resource.*;
import game.buildings.defense.*;
import game.entities.*;


public class CostCheck {

	public static MilitaryBuilding[] military = {new Barracks(0, new Vector2f(), null), new Cavalry(0, new Vector2f(), null), 
										new Range(0, new Vector2f(), null), new Arcanum(0, new Vector2f(), null)};
	public static ResourceBuilding[] resource = {new Farm(0, new Vector2f(), null),new Mine(0, new Vector2f(), null), new Mill(0, new Vector2f(), null), 
												new Quarry(0, new Vector2f(), null), new Stable(0, new Vector2f(), null), new MagicPump(0, new Vector2f(), null)};
	public static DefenseBuilding[] defense = {new Pitfall(0, new Vector2f(), null), new Wall(0, new Vector2f(), null)};
	
	private static void shuffle(Building[] ar) {
		Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Building b = ar[index];
	      ar[index] = ar[i];
	      ar[i] = b;	
	    }
	}
	
	public static void shuffle() {
		shuffle(military);
		shuffle(resource);
		shuffle(defense);
	}
	
	public static BuildingType military(ResourceHandler resources) {
		BuildingType toMake = null;
		for (int i = 0; i < military.length; i++) {
			if (resources.canAfford(military[i].getCost())) {
				toMake = military[i].getType();
				return toMake;
			}
		}
		return toMake;
	}
	
	public static BuildingType resource(ResourceHandler resources) {
		BuildingType toMake = null;
		for (int i = 0; i < resource.length; i++) {
			if (resources.canAfford(resource[i].getCost())) {
				toMake = resource[i].getType();
				return toMake;
			}
		}
		return toMake;
	}
	
	public static BuildingType defense(ResourceHandler resources) {
		BuildingType toMake = null;
		for (int i = 0; i < defense.length; i++) {
			if (resources.canAfford(defense[i].getCost())) {
				toMake = defense[i].getType();
				return toMake;
			}
		}
		return toMake;
	}
}
