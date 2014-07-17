package game.world.tiles;

import game.entities.Tile;
import graphics.TextureRect;

public class BuildableTile extends Tile {

	int xIndex;
	int yIndex;
	
	public BuildableTile(int x, int y) {
		xIndex = x;
		yIndex = y;
	}

	@Override
	public TextureRect getTextureRect() {
		return new TextureRect(0, 0.25f, 0.25f, 0.25f);
	}
}
