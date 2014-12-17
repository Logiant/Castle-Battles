package game.entities;

import game.buildings.Building;
import game.buildings.BuildingType;
import game.buildings.ResourceHandler;
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
import main.Time;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.geom.Vector2f;

import widgets.Text;

public abstract class City {

	enum UNIT {
		INFANTRY,  HORSE, ARCHER, MAGE;
	}


	//Too poor message constants
	public static final float messageTotal = 2000; //ms
	private float messageTime = 0;
	private String message = "";
	private Vector2f warnPos = new Vector2f(20, Display.getHeight()/2 - 200);

	//Enum Values
	public static final BuildingType[] BUILDINGS = BuildingType.values();
	public static final UNIT[] UNITS = UNIT.values();
	public static final int RESOURCE_OFFSET = 0;
	public static final int RESOURCE_SIZE = 6;
	public static final int MILITARY_OFFSET = 6;
	public static final int MILITARY_SIZE = 4;
	public static final int DEFENSE_OFFSET = 10;
	public static final int DEFENSE_SIZE = 2;


	//do we want to put these in an int[] to save space?
	protected ResourceHandler resources;

	protected List<Projectile> projectileHandler;
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
		projectileHandler = new ArrayList<Projectile>();
		placingPosition = new Vector2f();;
		enemyTarget = new Vector2f();
		resources = new ResourceHandler();
		resources.lumber = 50;
		resources.food = 50;
		resources.magic = 50;
		resources.horse = 50;
		resources.stone = 50;
		resources.metal = 50;
	}
	
	public void addProjectile(Projectile p) {
		projectileHandler.add(p);
	}

	public void deleteBuilding(Vector2f position) {
		for (int i = 0; i < resourceBuildings.size(); i++) {
			Building b = resourceBuildings.get(i);
			if (containsPoint(b, position)) {
				world.removeBuilding(b.getPosition().x, b.getPosition().y, b.getSize().x, b.getSize().y);
				resourceBuildings.remove(b);
				return;
			}
		}
		for (int i = 0; i < militaryBuildings.size(); i++) {
			Building b = militaryBuildings.get(i);
			if (containsPoint(b, position)) {
				world.removeBuilding(b.getPosition().x, b.getPosition().y, b.getSize().x, b.getSize().y);
				militaryBuildings.remove(b);
				return;
			}
		}
	}

	public boolean containsPoint(Building b, Vector2f position) {
		boolean contains = false;
		Vector2f buildingPos = b.getPosition();
		Vector2f buildingSize = b.getSize();


		if (position.x >= buildingPos.x && position.y >= buildingPos.y
				&& position.x <= buildingPos.x + buildingSize.x
				&& position.y <= buildingPos.y + buildingSize.y) {
			contains = true;
		}

		return contains;
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
		targets.addAll(militaryBuildings);
		targets.addAll(resourceBuildings);
		targets.addAll(defenseBuildings);
		targets.addAll(soldiers);
		return targets;
	}

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
	
	public void drawText() {
		if (messageTime > 0) {
			Text.warn(message, new Vector2f(warnPos.x, warnPos.y - (messageTotal - messageTime)/100f));
		}
	}


	public boolean update(Vector2f translation, boolean active) {
		messageTime -= Time.dt;
		messageTime = (float)Math.max(messageTime, 0);
		if (InputHandler.rightClicked())
			placingBuilding = false;


		if (placingBuilding && world.isBuildable(placingPosition.x, placingPosition.y, getBuilding(placingBuildingId).getSize().x, getBuilding(placingBuildingId).getSize().y) 
				&& active && InputHandler.leftClicked() && !UI.containsMouse()) { //click to place a building
			buildBuilding();
		}

		collectResources();

		for (int i = soldiers.size()-1; i >=0;  i--) {
			if (soldiers.get(i).isAlive())
				soldiers.get(i).update();
			else
				soldiers.remove(i);
		}

		for (int i = defenseBuildings.size()-1; i >=0;  i--) {
			if (defenseBuildings.get(i).isAlive())
				defenseBuildings.get(i).update();
			else
				defenseBuildings.remove(i);
		}
		
		updateProjectiles();

		return !HQ.isAlive();

	}
	
	public void updateProjectiles() {
		for (int i = projectileHandler.size() - 1; i >=0; i--) {
			Projectile p = projectileHandler.get(i);
			if (p.update()) { //if P has reached its target
				p.doDamage();
				projectileHandler.remove(i);
			}
		}
	}

	public void buildCommand (BuildingType command) {
		switch (command) {
		case Farm:
			placingBuildingId = farmId;
			placingBuilding = true;
			break;
		case Mine:
			placingBuildingId = mineId;
			placingBuilding = true;
			break;
		case Mill:
			placingBuildingId = millId;
			placingBuilding = true;
			break;
		case Stable:
			placingBuildingId = stableId;
			placingBuilding = true;
			break;
		case Quarry:
			placingBuildingId = quarryId;
			placingBuilding = true;
			break;
		case MagicPump:
			placingBuildingId = magicId;
			placingBuilding = true;
			break;
		case Barracks:
			placingBuildingId = barracksId;
			placingBuilding = true;
			break;
		case Range:
			placingBuildingId = rangeId;
			placingBuilding = true;
			break;
		case Cavalry:
			placingBuildingId = cavalryId;
			placingBuilding = true;
			break;
		case Arcanum:
			placingBuildingId = arcanumId;
			placingBuilding = true;
			break;
		case Wall:
			placingBuildingId = wallId;
			placingBuilding = true;
			break;
		case Pitfall:
			placingBuildingId = pitfallId;
			placingBuilding = true;
			break;
		}
	}

	protected void collectResources() {
		for (int i = resourceBuildings.size()-1; i >=0;  i--) {
			if (resourceBuildings.get(i).isAlive())
				resourceBuildings.get(i).update();
			else {
				world.removeBuilding(resourceBuildings.get(i));
				resourceBuildings.remove(i);
			}
		}
		for (int i = militaryBuildings.size()-1; i >=0;  i--) {
			if (militaryBuildings.get(i).isAlive())
				militaryBuildings.get(i).update();
			else {
				world.removeBuilding(militaryBuildings.get(i));
				militaryBuildings.remove(i);
			}
		}
		HQ.update();
	}
	
	public void drawProjectiles(Graphics g) {
		for (Projectile p:projectileHandler)
			p.draw(g);
	}

	public void drawBuildings(Graphics g) {
		//draw all buildings
		for (ResourceBuilding b: resourceBuildings) {
			if (b.isAlive())
				b.draw(g);
		}
		for (MilitaryBuilding m: militaryBuildings) {
			if (m.isAlive())
				m.draw(g);
		}
		//draw all defensive buildings
		for (DefenseBuilding d: defenseBuildings) {
			if (d.isAlive())
				d.draw(g);
		}
		//draw the HQ
		if (HQ.isAlive()) {
			HQ.draw(g);
		}
	}

	public void drawUnits(Graphics g) {
		//draw all units
		for (Unit u: soldiers) {
			if (u.isAlive())
				u.draw(g);
		}
	}


	protected void buildBuilding() {
		Building building = getBuilding(placingBuildingId);
		ResourceHandler cost = building.getCost();
		if (canAfford(cost)) {
			if (building instanceof ResourceBuilding)
				resourceBuildings.add((ResourceBuilding)building);
			if (building instanceof MilitaryBuilding)
				militaryBuildings.add((MilitaryBuilding)building);
			if (building instanceof DefenseBuilding)
				defenseBuildings.add((DefenseBuilding)building);

			world.placeBuilding(placingPosition.x, placingPosition.y, building.getSize().x, building.getSize().y);
		} else {
			messageTime = messageTotal;
			message = "Requires Additional ";
			message += getMissingResources(cost);
			System.out.println("Too poor, " + building.getCost());
		}
	}

	protected boolean canAfford(ResourceHandler cost) {
		boolean afford = false;
		if (cost.food <= resources.food && cost.lumber <= resources.lumber && cost.metal <= resources.metal
				&& cost.stone <= resources.stone && cost.horse <= resources.horse && cost.magic <= resources.magic) {
			afford = true;
			resources.food -= cost.food;
			resources.lumber -= cost.lumber;
			resources.metal -= cost.metal;
			resources.stone -= cost.stone;
			resources.horse -= cost.horse;
			resources.magic -= cost.magic;
		}
		return afford;
	}

	protected String getMissingResources(ResourceHandler cost) {
		String need = "";
		if (cost.food >= resources.food){
			need += " food, ";
		}
		if(cost.lumber >= resources.lumber) {
			need += " lumber, ";

		}
		if(cost.metal >= resources.metal) {
			need += " metal, ";

		}
		if(cost.stone >= resources.stone){
			need += " stone, ";

		}
		if(cost.horse >= resources.horse) {
			need += " horses, ";

		}
		if(cost.magic >= resources.magic) {
			need += " magic, ";
		}
		if (!need.isEmpty()) {
			need = need.substring(0, need.length() - 2);
		}
		return need;
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


	public void addResource(ResourceHandler delivered) {
		resources.add(delivered);
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

	protected Building getBuildingFromEnum(BuildingType make, Vector2f position) {
		Building b = null;
		switch (make) {
		case Barracks:
			b = new Barracks(barracksId, position, this);
			break;
		case Cavalry:
			b = new Cavalry(cavalryId, position, this);
			break;
		case Range:
			b = new Range(rangeId, position, this);
			break;
		case Arcanum:
			b = new Arcanum(arcanumId, position, this);
			break;
		case Farm:
			b = new Farm(farmId, position, this);
			break;
		case MagicPump:
			b = new MagicPump(magicId, position, this);
			break;
		case Mill:
			b = new Mill(millId, position, this);
			break;
		case Mine:
			b = new Mine(mineId, position, this);
			break;
		case Pitfall:
			b = new Pitfall(pitfallId, position, this);
			break;
		case Quarry:
			b = new Quarry(quarryId, position, this);
			break;
		case Stable:
			b = new Stable(stableId, position, this);
			break;
		case Wall:
			b = new Wall(wallId, position, this);
			break;
		}		
		return b;
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
