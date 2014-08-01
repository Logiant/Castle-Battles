package game.world;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import game.entities.Tile;
import graphics.Graphics;
import graphics.Rect;

/**
 * a map class that holds/renders the games tiles
 * @author beaverl
 *
 */
public class Map {

	public static final float TILE_SIZE = 64;

	char[][] map = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'Z', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'X'},
					{'X', 'X', 'X', 'X', 'G', 'G', 'G', 'G', 'G', 'G', 'X', 'X', 'X', 'X'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'N'},
					{'W', 'W', 'W', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'W', 'W', 'W', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'Q', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}};

	Tile[][] world;
	int tileMapID;

	int worldWidth;
	int worldHeight;
	
	Vector2f headquartersPos;
	Vector2f enemyHeadquartersPos;
	List<Vector2f> walls;
	List<Vector2f> enemyWalls;
	List<Vector2f> neutralWalls;

	public Map() { //eventually replace with a levelLoader or something
		worldHeight = map.length;
		worldWidth = map[0].length;
		world = new Tile[worldHeight][worldWidth];
		headquartersPos = new Vector2f();
		enemyHeadquartersPos = new Vector2f();
		walls = new LinkedList<Vector2f>();
		enemyWalls = new LinkedList<Vector2f>();
		neutralWalls = new LinkedList<Vector2f>();
	}

	public void initialize(Graphics g) {
		tileMapID = g.loadImage("TileMap");
		loadTiles();
	}

	public void loadTiles() {
		for (int i = 0; i < worldHeight; i++) {
			for (int j = 0; j < worldWidth; j++) {
				world[i][j] = TileHandler.getTile(map[i][j], i, j);
				if (map[i][j] == 'Q') {
					headquartersPos = new Vector2f(j, i);
				}
				if (map[i][j] == 'W') {
					walls.add(new Vector2f(j, i));
				}
				if (map[i][j] == 'Z') {
					enemyHeadquartersPos = new Vector2f(j, i);
				}
				if (map[i][j] == 'X') {
					enemyWalls.add(new Vector2f(j, i));
				}
				if (map[i][j] == 'N') {
					neutralWalls.add(new Vector2f(j, i));
				}
			}
		}
	}
	
	public float getHeight() {
		return worldHeight * TILE_SIZE;
	}

	public void draw(Graphics g) {
		for (int i = 0; i < worldHeight; i++) {
			for (int j = 0; j < worldWidth; j++) {
				Tile t = world[i][j];
				//we will eventually want to check if the tile is on the screen at all before drawing to optimize
				g.drawMapped(tileMapID, new Rect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE), t.getTextureRect());
			}
		}
	}
	
	public boolean isBuildable(float xPos, float yPos, float widthF, float heightF) {
		boolean buildable = true;
		
		int x = (int)(xPos/TILE_SIZE);
		int y = (int)(yPos/TILE_SIZE);
		int width = (int)(widthF/TILE_SIZE);
		int height = (int)(heightF/TILE_SIZE);

		if (x < 0 || y< 0 || x+width > worldWidth || y+height > worldHeight) {
			return false;
		}
		for (int i = y; i < y+height; i++) {
			for (int j = x; j < x+width; j++) {
				if (!world[i][j].isBuildable()) {
					return false;
				}
			}
		}
		
		return buildable;
	}
	
	public void removeBuilding(float xPos, float yPos, float widthF, float heightF) {
		int x = (int)(xPos/TILE_SIZE);
		int y = (int)(yPos/TILE_SIZE);
		int width = (int)(widthF/TILE_SIZE);
		int height = (int)(heightF/TILE_SIZE);

		for (int i = y; i < y+height; i++) {
			for (int j = x; j < x+width; j++) {
				world[i][j].setBuildable(map[i][j] == 'B');				
			}
		}
	}
	
	public void placeBuilding(float xPos, float yPos, float widthF, float heightF) {
		int x = (int)(xPos/TILE_SIZE);
		int y = (int)(yPos/TILE_SIZE);
		int width = (int)(widthF/TILE_SIZE);
		int height = (int)(heightF/TILE_SIZE);

		for (int i = y; i < y+height; i++) {
			for (int j = x; j < x+width; j++) {
				world[i][j].setBuildable(false);				
			}
		}
	}

	public Vector2f headquartersPos() {
		return headquartersPos;
	}
	
	public List<Vector2f> getWalls() {
		return walls;
	}

	public Vector2f enemyHeadquartersPos() {
		return enemyHeadquartersPos;
	}

	public List<Vector2f> getEnemyWalls() {
		return enemyWalls;
	}

	public List<Vector2f> getNeutralWalls() {
		return neutralWalls;
	}
}
