package graphics;

import org.newdawn.slick.geom.Vector2f;

/**
 * A storage class which transfers the position of a quad on the screen to Graphics
 * @author beaverl
 *
 */
public class Rect {
	
	public int x, y, width, height; //changed to integers to reduce jagged edges

	public Rect(float x, float y, float width, float height) {
		this.x = (int)(x + 0.5);
		this.y = (int)(y + 0.5);
		this.width = (int)(width + 0.5);
		this.height = (int)(height + 0.5);
	}
	
	public Rect(Vector2f position, Vector2f size) {
		this(position.x, position.y, size.x, size.y);
	}


}
