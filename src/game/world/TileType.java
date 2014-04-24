package game.world;

public enum TileType {
	GROUND, WALL, WATER;

	public TileType getType(char c) {
		TileType type = GROUND;
		switch (c) {
		default:
		case 'G':
			break;
		case 'W':
			type = WALL;
			break;
		case 'A':
			type = WATER;
			break;
		}
		return type;
	}

}
