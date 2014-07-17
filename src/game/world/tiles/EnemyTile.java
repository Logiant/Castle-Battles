package game.world.tiles;

import game.entities.Tile;
import graphics.TextureRect;

public class EnemyTile extends Tile {

	int xIndex;
	int yIndex;
	
	public EnemyTile(int x, int y) {
		xIndex = x;
		yIndex = y;
	}

	@Override
	public TextureRect getTextureRect() {
		return new TextureRect(0.25f, 0.25f, 0.25f, 0.25f);
	}
}
