package game.world;

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

	char[][] map = {{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'},
					{'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A'},
					{'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A'},
					{'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A'},
					{'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A'},
					{'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A'},
					{'W', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'W'}};

	Tile[][] world;
	int tileMapID;

	int worldWidth;
	int worldHeight;

	public Map() { //eventually replace with a levelLoader or something
		worldWidth = map.length;
		worldHeight = map[0].length;
		world = new Tile[worldWidth][worldHeight];
	}

	public void initialize(Graphics g) {
		tileMapID = g.loadImage("TileMap");
		loadTiles();
	}

	public void loadTiles() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				world[i][j] = TileHandler.getTile(map[i][j], i, j);
			}
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				Tile t = world[i][j];
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

		if (x < 0 || y< 0 || x+width > map[0].length || y+height > map.length) {
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
}
