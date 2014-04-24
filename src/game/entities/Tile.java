package game.entities;

import graphics.TextureRect;

/**
 * highest level entity for all tiles
 * contains position data as well as helper methods
 * @author beaverl
 *
 */
public abstract class Tile {
	
	private boolean buildable; //can a tile have a building placed on it?

	public abstract TextureRect getTextureRect();
	
	public boolean isBuildable() {
		return buildable;
	}
	
	public void setBuildable(boolean buildable) {
		this.buildable = buildable;
	}

}
