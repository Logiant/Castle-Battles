package game.world.tiles;

import game.entities.Tile;

public class GroundTile extends Tile {

	int xIndex;
	int yIndex;
	
	public GroundTile(int x, int y) {
		xIndex = x;
		yIndex = y;
	}
}
