package game.world;

import game.entities.Tile;
import graphics.Graphics;
import graphics.Rect;
import graphics.TextureRect;

/**
 * a map class that holds/renders the games tiles
 * @author beaverl
 *
 */
public class Map {

	public static final int TILE_SIZE = 32;
	
	Tile[][] world;
	int tileMapID;
	
	int worldWidth;
	int worldHeight;

	public Map(int width, int height) { //eventually replace with a levelLoader or something
		worldWidth = width;
		worldHeight = height;
		world = new Tile[width][height];
	}
	
	public void initialize(Graphics g) {
		tileMapID = g.loadImage("TileMap");
	}

	public void loadTiles() {

	}

	public void draw(Graphics g) {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				g.drawMapped(tileMapID, new Rect(i, j, TILE_SIZE, TILE_SIZE), new TextureRect(0, 0.25f, 0.25f, 0.25f));
			}
		}
	}
}
