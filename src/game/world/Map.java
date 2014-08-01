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

	char[][] map = {{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
					{'W', 'W', 'W', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'W', 'W', 'W', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
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
	List<Vector2f> walls;

	public Map() { //eventually replace with a levelLoader or something
		worldHeight = map.length;
		worldWidth = map[0].length;
		world = new Tile[worldHeight][worldWidth];
		headquartersPos = new Vector2f();
		walls = new LinkedList<Vector2f>();
		System.out.println(worldWidth + ", " + worldHeight);
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
}
