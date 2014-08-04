package game.buildings;

import game.buildings.defense.Pitfall;
import game.buildings.defense.Wall;
import game.buildings.military.Arcanum;
import game.buildings.military.Barracks;
import game.buildings.military.Cavalry;
import game.buildings.military.Range;
import game.buildings.other.Headquarters;
import game.buildings.resource.Farm;
import game.buildings.resource.MagicPump;
import game.buildings.resource.Mill;
import game.buildings.resource.Mine;
import game.buildings.resource.Quarry;
import game.buildings.resource.Stable;
import game.entities.Combat;
import game.entities.DefenseBuilding;
import game.entities.MilitaryBuilding;
import game.entities.ResourceBuilding;
import game.entities.Unit;
import game.ui.UI;
import game.units.Archer;
import game.units.Horse;
import game.units.Infantry;
import game.units.Mage;
import game.world.Map;
import graphics.Graphics;
import graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

public abstract class City {


	//do we want to put these in an int[] to save space?
	protected int lumber;
	protected int stone;
	protected int food;
	protected int metal;
	protected int horse;
	protected int magic;


	protected List<ResourceBuilding> resourceBuildings;
	protected List<MilitaryBuilding> militaryBuildings;
	protected List<Unit> soldiers;
	protected List<DefenseBuilding> defenseBuildings;
	protected Headquarters HQ;
	protected Vector2f placingPosition;
	protected boolean placingBuilding;
	protected int placingBuildingId;
	
	protected Vector2f enemyTarget;

	//do we want to put these in an int[] to save space?
	public static int farmId;
	public static int mineId;
	public static int millId;
	public static int stableId;
	public static int quarryId;
	public static int magicId;
	//military
	public static int barracksId;
	public static int cavalryId;
	public static int rangeId;
	public static int arcanumId;
	//units
	public static int infantryId;
	public static int horseId;
	public static int archerId;
	public static int mageId;
	//defensive
	public static int wallId;
	public static int pitfallId;
	//other buildings
	public static int headquartersId;

	public static Map world;

	public void setup() {
		resourceBuildings = new ArrayList<ResourceBuilding>();
		militaryBuildings = new ArrayList<MilitaryBuilding>();
		soldiers = new ArrayList<Unit>();
		defenseBuildings = new ArrayList<DefenseBuilding>();
		placingPosition = new Vector2f();;
		enemyTarget = new Vector2f();
	}
	
	public void setTarget(Vector2f target) {
		enemyTarget = target;
	}
	
	public void findTargets(City other) {
		List<Combat> targets = other.getCombatants();
		for (Unit u: soldiers)
			u.findTarget(targets);
		for (DefenseBuilding d: defenseBuildings)
			d.findTarget(targets);
	}
	
	public List<Combat> getCombatants() {
		List<Combat> targets = new ArrayList<Combat>();
		targets.add(HQ);
		targets.addAll(soldiers);
		targets.addAll(defenseBuildings);
		targets.addAll(militaryBuildings);
		targets.addAll(resourceBuildings);
		return targets;
	}
	
	public abstract void drawText();
	
	public static void initialize(Graphics g, Map map) {
		//resource buildings
		farmId = g.loadImage("Buildings/Farm");
		mineId = g.loadImage("Buildings/Mine");
		millId = g.loadImage("Buildings/Lumber");
		stableId = g.loadImage("Buildings/Stable");
		quarryId = g.loadImage("Buildings/Quarry");
		magicId = g.loadImage("Buildings/MagicPump");
		//military buildings
		barracksId = g.loadImage("Buildings/Barracks");
		cavalryId = g.loadImage("Buildings/Cavalry");
		rangeId = g.loadImage("Buildings/Range");
		arcanumId = g.loadImage("Buildings/Arcanum");
		//units
		infantryId = g.loadImage("Units/Infantry");
		horseId = g.loadImage("Units/Cavalry");
		archerId = g.loadImage("Units/Archer");
		mageId = g.loadImage("Units/Mage");
		//defensive
		wallId = g.loadImage("Buildings/Wall");
		pitfallId = g.loadImage("Buildings/Pitfall");
		//other buildings
		headquartersId = g.loadImage("Buildings/Headquarters");

		world = map;
	}
	
	
	public Headquarters getHQ() {
		return HQ;
	}

	protected void buildWalls(List<Vector2f> walls) {
		for (Vector2f w:walls) {
			DefenseBuilding building = new Wall(wallId, new Vector2f(w.x*Map.TILE_SIZE, w.y*Map.TILE_SIZE), this);
			world.placeBuilding(w.x, w.y, building.getSize().x, building.getSize().y);
			defenseBuildings.add(building);
		}
	}

	protected void placeHQ(Vector2f pos) {
		HQ = new Headquarters(headquartersId, new Vector2f(pos.x*Map.TILE_SIZE, pos.y*Map.TILE_SIZE), this);
		world.placeBuilding(pos.x, pos.y, 1, 1);

	}

	public boolean update(Vector2f translation, boolean active) {
		if (InputHandler.rightClicked())
			placingBuilding = false;


		if (placingBuilding && world.isBuildable(placingPosition.x, placingPosition.y, getBuilding(placingBuildingId).getSize().x, getBuilding(placingBuildingId).getSize().y) 
				&& active && InputHandler.leftClicked() && !UI.containsMouse()) { //click to place a building
			buildBuilding();
		}

		collectResources();

		for (Unit u:soldiers)
			u.update();
		
		for (DefenseBuilding d: defenseBuildings) {
			d.update();
		}
		
		return !HQ.isAlive();

	}

	public void buildCommand (String command) {
		switch (command) {
		case "FARM":
			placingBuildingId = farmId;
			placingBuilding = true;
			break;
		case "MINE":
			placingBuildingId = mineId;
			placingBuilding = true;
			break;
		case "WOOD":
			placingBuildingId = millId;
			placingBuilding = true;
			break;
		case "HORSE":
			placingBuildingId = stableId;
			placingBuilding = true;
			break;
		case "STONE":
			placingBuildingId = quarryId;
			placingBuilding = true;
			break;
		case "MAGIC":
			placingBuildingId = magicId;
			placingBuilding = true;
			break;
		case "BARRACKS":
			placingBuildingId = barracksId;
			placingBuilding = true;
			break;
		case "RANGE":
			placingBuildingId = rangeId;
			placingBuilding = true;
			break;
		case "STABLE":
			placingBuildingId = cavalryId;
			placingBuilding = true;
			break;
		case "ARCANUM":
			placingBuildingId = arcanumId;
			placingBuilding = true;
			break;
		case "WALL":
			placingBuildingId = wallId;
			placingBuilding = true;
			break;
		case "PITFALL":
			placingBuildingId = pitfallId;
			placingBuilding = true;
			break;
		}
	}

	protected void collectResources() {
		for (ResourceBuilding b: resourceBuildings)
			b.update();
		for (MilitaryBuilding m: militaryBuildings)
			m.update();

		HQ.update();
	}

	public void draw(Graphics g) {
		//draw all buildings
		for (ResourceBuilding b: resourceBuildings)
			b.draw(g);
		for (MilitaryBuilding m: militaryBuildings)
			m.draw(g);
		//draw all units
		for (Unit u: soldiers)
			u.draw(g);
		//draw all defensive buildings
		for (DefenseBuilding d: defenseBuildings)
			d.draw(g);
		//draw the HQ
		HQ.draw(g);
	}

	
	protected void buildBuilding() {
		Building building = getBuilding(placingBuildingId);
		if (canAfford(building.getCost())) {
			if (building instanceof ResourceBuilding)
				resourceBuildings.add((ResourceBuilding)building);
			if (building instanceof MilitaryBuilding)
				militaryBuildings.add((MilitaryBuilding)building);
			if (building instanceof DefenseBuilding)
				defenseBuildings.add((DefenseBuilding)building);

			world.placeBuilding(placingPosition.x, placingPosition.y, building.getSize().x, building.getSize().y);
		} else {
			System.out.println("Too poor, " + building.getCost());
		}
	}

	protected boolean canAfford(ResourceHandler cost) {
		boolean afford = false;
		if (cost.food <= food && cost.lumber <= lumber && cost.metal <= metal && cost.stone <= stone && cost.horse <= horse && cost.magic <= magic) {
			afford = true;
			food -= cost.food;
			lumber -= cost.lumber;
			metal -= cost.metal;
			stone -= cost.stone;
			horse -= cost.horse;
			magic -= cost.magic;
		}
		return afford;
	}

	public void drawPlaced(Graphics g, Vector2f translate, boolean active) {
		//draw the building being placed if there is one
		if (placingBuilding) {
			//snaps the current position to the nearest tile
			if (active) {
				placingPosition = new Vector2f((float)Math.floor((Mouse.getX()+translate.x) / Map.TILE_SIZE)*Map.TILE_SIZE, 
						(float)Math.ceil(((Driver.screenHeight - Mouse.getY() - getBuilding(placingBuildingId).getSize().y + translate.y)/Map.TILE_SIZE))*Map.TILE_SIZE);
			}
			//draws the selected building (set to farm for now) at the current world location snapped to the tile the cursor is over
			g.draw(placingBuildingId, new Rect(placingPosition, new Vector2f(getBuilding(placingBuildingId).getSize().x, getBuilding(placingBuildingId).getSize().y)));
			//we want some sort of notification that a spot can't be built on - maybe tinting the building red?
		}
	}


	public void addResource(String type, int qty) {
		switch (type) {
		case "FOOD":
			food += qty;
			break;
		case "LUMBER":
			lumber += qty;
			break;
		case "METAL":
			metal += qty;
			break;
		case "STONE":
			stone += qty;
			break;
		case "HORSE":
			horse += qty;
			break;
		case "MAGIC":
			magic += qty;
			break;
		case "ALL":
			food += qty;
			lumber += qty;
			metal += qty;
			stone += qty;
			horse += qty;
			magic += qty;
			break;
		}

	}

	protected Building getBuilding(int id) {
		Building building = null;
		if (id == farmId || id == mineId || id == millId || id == stableId || id == quarryId || id == magicId) {
			building = getResourceBuilding(id);
		} else if (id == barracksId || id == cavalryId || id == rangeId || id == arcanumId) {
			building = getMilitaryBuilding(id);
		} else if (id == wallId || id == pitfallId) {
			building = getDefenseBuilding(id);
		}
		return building;
	}

	protected DefenseBuilding getDefenseBuilding(int placingBuildingId) {
		DefenseBuilding building = null;
		if (placingBuildingId == wallId) {
			building = new Wall(wallId, placingPosition, this);
		}
		else if (placingBuildingId == pitfallId) {
			building = new Pitfall(pitfallId, placingPosition, this);
		}
		return building;
	}
	
	protected MilitaryBuilding getMilitaryBuilding(int placingBuildingId) {
		MilitaryBuilding building = null;
		if (placingBuildingId == barracksId) {
			building = new Barracks(barracksId, placingPosition, this);
		}
		else if (placingBuildingId == cavalryId) {
			building = new Cavalry(cavalryId, placingPosition, this);
		}
		else if (placingBuildingId == rangeId) {
			building = new Range(rangeId, placingPosition, this);
		}
		else if (placingBuildingId == arcanumId) {
			building = new Arcanum(arcanumId, placingPosition, this);
		}
		return building;
	}

	protected ResourceBuilding getResourceBuilding(int placingBuildingId) {
		ResourceBuilding building = null;
		if (placingBuildingId == farmId) {
			building = new Farm(farmId, placingPosition, this);
		}
		else if (placingBuildingId == mineId) {
			building = new Mine(mineId, placingPosition, this);
		}
		else if (placingBuildingId == millId) {
			building = new Mill(millId, placingPosition, this);
		}
		else if (placingBuildingId == stableId) {
			building = new Stable(stableId, placingPosition, this);
		}
		else if (placingBuildingId == quarryId) { //A hack to not crash the game when a military building is placed!
			building = new Quarry(quarryId, placingPosition, this);
		}
		else if (placingBuildingId == magicId) {
			building = new MagicPump(magicId, placingPosition, this);
		}
		return building;
	}

	public void produceUnit(MilitaryBuilding building, Vector2f position, Vector2f size) {
		Unit unit = null;
		if (building instanceof Barracks) {
			unit = new Infantry(infantryId, position, size,this);
		}
		else if (building instanceof Cavalry) {
			unit = new Horse(horseId, position, size,this);
		}
		else if (building instanceof Range) {
			unit = new Archer(archerId, position, size,this);
		}
		else if (building instanceof Arcanum) {
			unit = new Mage(mageId, position, size,this);
		}
		if (unit != null) {
			soldiers.add(unit);
			unit.setTarget(enemyTarget);
		}
	}
	
}
