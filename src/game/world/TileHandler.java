package game.world;

import game.entities.Tile;
import game.world.tiles.*;

public class TileHandler {

	enum TileType {
		BUILDABLE, GROUND, WALL, WATER, ENEMY;
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
		case ENEMY:
			nextTile = new EnemyTile(x, y);
			nextTile.setBuildable(true);
			break;
		case BUILDABLE:
			nextTile = new BuildableTile(x, y);
			nextTile.setBuildable(true);
			break;
		}
		return nextTile;
	}

	private static TileType getType(char c) {
		TileType type = TileType.GROUND;
		switch (c) {
		default:
		case 'G':
			type = TileType.GROUND;
			break;
		case 'N':
		case 'X':
		case 'W':
			type = TileType.WALL;
			break;
		case 'A':
			type = TileType.WATER;
			break;
		case 'B':
			type = TileType.BUILDABLE;
			break;
		case 'E':
			type = TileType.ENEMY;
			break;
		}
		return type;
	}

}
