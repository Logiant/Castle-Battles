package widgets;

import java.awt.Color;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Vector2f;

/**
 * A static class that handles writing text to the screen
 * @author Logs
 *
 */
public class Text {
	
	private static UnicodeFont font;
	private static UnicodeFont smallFont;
	
	public static int fontSize = 20;
	public static int smallSize = 12;
	
	@SuppressWarnings("unchecked")
	public static void initialize() {
		String fontPath = "resources/Cinnamon.ttf";
		try {
			font = new UnicodeFont(fontPath, fontSize, true, false);
			font.getEffects().add(new ColorEffect(Color.WHITE));
			font.addNeheGlyphs();
			font.loadGlyphs();
			smallFont = new UnicodeFont("resources/Roboto.ttf", smallSize, false, false);
			smallFont.getEffects().add(new ColorEffect(Color.WHITE));
			smallFont.addNeheGlyphs();
			smallFont.loadGlyphs();
		} catch (SlickException e) {
			System.err.println("Could not load font");
			System.exit(-1);
		}
	}
	
	public static void write(String text, Vector2f position) {
		font.drawString(position.x, position.y, text);
	}
	
	public static void writeSmall(String text, Vector2f position) {
		smallFont.drawString(position.x, position.y, text);
	}

	public static void write(String name, float x, float y) {
		write(name, new Vector2f(x, y));
	}
}
