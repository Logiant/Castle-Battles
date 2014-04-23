package graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * A class which handles all draw calls to openGL and texture loading
 * @author beaverl
 *
 */
public class Graphics {

	private static int idLoaded = -1;
	private static List<Texture> textures;
	private static List<String> paths;

	public void initialize() {
		textures = new ArrayList<Texture>();
		paths = new ArrayList<String>();
	}

	public int loadImage(String path) {
		int id = textures.size();
		if (paths.contains(path)) { //if we already loaded this texture
			id = paths.indexOf(path); //just get the id and return it
		} else {
			paths.add(path); //add to the list of loaded textures
			try {
				textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/" + path + ".png")));
				System.out.println("Loaded: " + path + ".png");
			} catch (IOException e) {
				System.err.println("Could not load file " + path);
				System.exit(-1);
			}
		}
		return id;
	}

	//draws static images
	public void draw(int id, Rect rectangle) {
		//if drawing a new texture
		if (idLoaded != id) {
			GL11.glEnd();
			idLoaded = id;
			textures.get(id).bind();
			GL11.glBegin(GL11.GL_QUADS);
		}
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(rectangle.x, rectangle.y);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(rectangle.x, rectangle.y + rectangle.height);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(rectangle.x + rectangle.width, rectangle.y);
	}
}
