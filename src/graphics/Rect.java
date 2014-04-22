package graphics;

import org.newdawn.slick.geom.Vector2f;

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
	
	public Rect(Vector2f position, Vector2f size) {
		this(position.x, position.y, size.x, size.y);
	}


}
