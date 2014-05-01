
package game;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import widgets.GameOptionsMenu;
import game.buildings.CityManager;
import game.ui.UI;
import game.world.Map;
import game.world.SimpleBackground;
import graphics.Graphics;

/**
 * an actual skirmish game. Handles input, logic, and rendering
 * @author beaverl
 *
 */
public class Game {

	Camera cam;
	Map map;
	GameOptionsMenu gameOptionsMenu;
	CityManager city;
	UI ui;
	SimpleBackground background;
	//region for scrolling the camera via mouse
	float scrollRegion = 20;

	//we can load this into a keybind class or something
	int upKey = Keyboard.getKeyIndex("W");
	int downKey = Keyboard.getKeyIndex("S");
	int leftKey = Keyboard.getKeyIndex("A");
	int rightKey = Keyboard.getKeyIndex("D");
	int escape = Keyboard.KEY_ESCAPE;


	public Game() {
		background = new SimpleBackground();
		cam = new Camera();
		map = new Map();
		gameOptionsMenu = new GameOptionsMenu();
		city = new CityManager();
		ui = new UI(city);
	}

	public void initialize(Graphics g) {
		background.initialize(g);
		map.initialize(g);
		cam.initialize(0, 0);
		gameOptionsMenu.initialize(g);
		city.initialize(g, map);
		ui.initialize(g);
	}
	public String update(Graphics g) {
		drawBackground(g);
		cameraMovement();		
		String nextState = gameOptionsMenu.update(g);

		if(InputHandler.wasKeyPressed(escape) || nextState.equals("BACK")) {
			gameOptionsMenu.toggle();
		}

		city.update(cam.getTranslation(), !gameOptionsMenu.isActive());
		
		
		//rendering - we could make this a nested class called Render if needed
		GL11.glBegin(GL11.GL_QUADS);
		map.draw(g);
		city.draw(g);
		city.drawPlaced(g, cam.getTranslation(), !gameOptionsMenu.isActive());
		GL11.glEnd();

		drawUI(g);
		
		//TODO finish this
		return nextState;
	}
	
	private void drawBackground(Graphics g) {
		//draws a static background so we don't just havee the blank screen clear
		GL11.glBegin(GL11.GL_QUADS);
		background.draw(g);
		GL11.glEnd();
	}
	
	private void drawUI(Graphics g) {
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glBegin(GL11.GL_QUADS);
		ui.update(g);
		GL11.glEnd();
		if (gameOptionsMenu.isActive()) {
			GL11.glBegin(GL11.GL_QUADS);
			gameOptionsMenu.draw(g);
			GL11.glEnd();
			gameOptionsMenu.draw2(g);
		}
		ui.drawText();
		GL11.glPopMatrix();
	}

	public void cameraMovement() {
		float dx = 0;
		float dy = 0;
		if(!gameOptionsMenu.isActive()) {	//fixed scrolling bug (scrolling while menu is up).
			if (InputHandler.isKeyDown(upKey) || Mouse.getY() > Driver.screenHeight - scrollRegion) {
				dy--;
			}if (InputHandler.isKeyDown(downKey) || Mouse.getY() < scrollRegion) {
				dy++;
			}if (InputHandler.isKeyDown(leftKey) || Mouse.getX() < scrollRegion) {
				dx--;
			}if (InputHandler.isKeyDown(rightKey) || Mouse.getX() > Driver.screenWidth - scrollRegion) {
				dx++;
			}
		}
		cam.move(dx, dy);
		cam.update();
	}
}
