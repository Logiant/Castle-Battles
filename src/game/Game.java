
package game;

import main.Driver;
import main.InputHandler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import widgets.GameOptionsMenu;
import game.city.CityManager;
import game.city.EnemyCityManager;
import game.city.NeutralCityManager;
import game.entities.City;
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

	GameController controller;
	Camera cam;
	Map map;
	GameOptionsMenu gameOptionsMenu;
	City city;
	City enemyCity;
	City neutralCity;
	UI ui;
	SimpleBackground background;
	//region for scrolling the camera via mouse
	float scrollRegion = 15;
	
	//endgame flags
	private boolean win;
	private boolean lose;

	//we can load this into a keybind class or something
	int upKey = Keyboard.getKeyIndex("W");
	int downKey = Keyboard.getKeyIndex("S");
	int leftKey = Keyboard.getKeyIndex("A");
	int rightKey = Keyboard.getKeyIndex("D");
	int escape = Keyboard.KEY_ESCAPE;
	int delete = Keyboard.KEY_DELETE;


	public Game(GameController controller) {
		background = new SimpleBackground();
		cam = new Camera();
		map = new Map();
		gameOptionsMenu = new GameOptionsMenu();
		this.controller = controller;
	}

	public void initialize(Graphics g) {
		//create new objects
		city = new CityManager();
		enemyCity = new EnemyCityManager();
		neutralCity = new NeutralCityManager();
		ui = new UI(city);
		//initialize all objects
		City.initialize(g, map);
		background.initialize(g);
		map.initialize(g);
		cam.initialize(0, map.getHeight() - Driver.screenHeight);
		gameOptionsMenu.initialize(g);
		city.setup();
		enemyCity.setup();
		neutralCity.setup();
		city.setTarget(enemyCity.getHQ().getPosition());
		enemyCity.setTarget(city.getHQ().getPosition());
		ui.initialize(g);
	}
	public String update(Graphics g) {
		drawBackground(g);
		cameraMovement();		
		String nextState = gameOptionsMenu.update(g);

		if(InputHandler.wasKeyPressed(escape) || nextState.equals("BACK")) {
			gameOptionsMenu.toggle();
		}
				
		if (!gameOptionsMenu.isActive()) {
			
			if(InputHandler.wasKeyPressed(delete)) {
				Vector2f deletePos = new Vector2f(Mouse.getX(), Driver.screenHeight - Mouse.getY());
				deletePos.add(cam.getTranslation());
				city.deleteBuilding(deletePos);
			}
			
			
			city.findTargets(enemyCity);
			enemyCity.findTargets(city);
			lose = city.update(cam.getTranslation(), !gameOptionsMenu.isActive());
			win = enemyCity.update(cam.getTranslation(), !gameOptionsMenu.isActive());
			neutralCity.update(cam.getTranslation(), !gameOptionsMenu.isActive());
		}
		
		//rendering - we could make this a nested class called Render if needed
		GL11.glBegin(GL11.GL_QUADS);
		map.draw(g);
		drawCities(g);
		city.drawPlaced(g, cam.getTranslation(), !gameOptionsMenu.isActive());
		GL11.glEnd();

		drawUI(g);
		
		if (win || lose) {
			controller.gameOver(win);
		}
		
		return nextState;
	}
	
	private void drawBackground(Graphics g) {
		//draws a static background so we don't just havee the blank screen clear
		GL11.glBegin(GL11.GL_QUADS);
		background.draw(g);
		GL11.glEnd();
	}
	
	private void drawCities(Graphics g) {
		enemyCity.drawBuildings(g);
		neutralCity.drawBuildings(g);
		city.drawBuildings(g);
		enemyCity.drawUnits(g);
		city.drawUnits(g);
		city.drawProjectiles(g);
		enemyCity.drawProjectiles(g);
	}
	
	private void drawUI(Graphics g) {
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glBegin(GL11.GL_QUADS);
		ui.update(g, !gameOptionsMenu.isActive());
		GL11.glEnd();
		if (gameOptionsMenu.isActive()) {
			GL11.glBegin(GL11.GL_QUADS);
			gameOptionsMenu.draw(g);
			GL11.glEnd();
			gameOptionsMenu.draw2(g);
		}
		ui.drawText();
		city.drawText();
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
	
	public void endGame(boolean win) {
		this.win = win;
		lose = !win;
	}
}
