package graphics;

/**
 * handles texture coordinates of anything on a spritesheet
 * @author beaverl
 *
 */
public class TextureRect {
	
	public float x;
	public float y;
	public float sizeX;
	public float sizeY;
	
	public TextureRect(float x, float y, float sizeX, float sizeY) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

}
