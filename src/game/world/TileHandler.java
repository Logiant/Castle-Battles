package game.world;

import game.entities.Tile;
import game.world.tiles.*;

public class TileHandler {

	enum TileType {
		GROUND, WALL, WATER;
	}


	public static Tile getTile(char c, int x, int y) {
		Tile nextTile = null;
		switch(getType(c)) {
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

	private static TileType getType(char c) {
		TileType type = TileType.GROUND;
		switch (c) {
		default:
		case 'G':
			break;
		case 'W':
			type = TileType.WALL;
			break;
		case 'A':
			type = TileType.WATER;
			break;
		}
		return type;
	}

}
