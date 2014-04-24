package game.world.tiles;

import game.entities.Tile;

public class WallTile extends Tile {

	int xIndex;
	int yIndex;
	
	public WallTile(int x, int y) {
		xIndex = x;
		yIndex = y;
	}
}
