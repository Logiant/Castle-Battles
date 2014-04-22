package graphics;

/**
 * A storage class which transfers the position of a quad on the screen to Graphics
 * @author beaverl
 *
 */
public class Rect {
	
	public float x, y, width, height;

	public Rect(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
