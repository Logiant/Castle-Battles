package game.world;

import game.entities.Tile;
import game.world.tiles.WallTile;
import graphics.Graphics;
import graphics.Rect;
import graphics.TextureRect;

/**
 * a map class that holds/renders the games tiles
 * @author beaverl
 *
 */
public class Map {

	public static final float TILE_SIZE = 64;

	char[][] map = {{'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'},
					{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
					{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
					{'G', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'G', 'G'},
					{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
					{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
					{'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W'}};

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
}
