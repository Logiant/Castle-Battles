package game.world;

import game.entities.Tile;
import game.world.tiles.*;

public class TileHandler {
	
	public static Tile getTile(TileType type, int x, int y) {
		Tile nextTile = null;
		switch(type) {
		default:
		case GROUND:
			nextTile = new GroundTile(x, y);
			break;
		case WALL:
			nextTile = new WallTile(x, y);
			break;
			
		case WATER:
			nextTile = new WaterTile(x, y);
			break;
		}
		return nextTile;
	}

}
