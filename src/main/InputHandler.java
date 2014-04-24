package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * handles the user's inputs
 * scans the keyboard for input, 
 * @author beaverl
 *
 */
public class InputHandler {

	public static final int numKeys = 220; //there aren't really this many keys, but some #s are skipped on a QWERTY keyboard
	
	static boolean[] keyDown;
	static boolean[] keyUp;
	static boolean[] keyHeld;
	
	static boolean leftClick;
	static boolean rightClick;
	static boolean middleClick;
	
	public static void initialize() {
		keyHeld = new boolean[numKeys];
		keyDown = new boolean[numKeys];
		keyUp = new boolean[numKeys];
	}
	
	public static void update() {
		//clear the keyboard
		for (int i = 0; i < numKeys; i++) { //iterating is twice as fast as re-allocating memory
			keyDown[i] = false;
			keyUp[i] = false;
		}
		//check the keyboard
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) { //if it was a press
				keyDown[Keyboard.getEventKey()] = true;
				keyHeld[Keyboard.getEventKey()] = true;
			} else { //it was a release
				keyUp[Keyboard.getEventKey()] = true;
				keyHeld[Keyboard.getEventKey()] = false;
			}
		}
		//clear the mouse
		leftClick = false;
		rightClick = false;
		middleClick = false;
		while (Mouse.next()) {
			if (Mouse.getEventButton() == 0 && Mouse.isButtonDown(0)) { //left click
				leftClick = true;
			}
			if (Mouse.getEventButton() == 1 && Mouse.isButtonDown(1)) { //right click
				rightClick = true;
			}
			if (Mouse.getEventButton() == 2 && Mouse.isButtonDown(2)) { //middle click
				middleClick = true;
			}
		}
	}
	
	public static boolean wasKeyPressed(int key) {
		return keyDown[key];
	}
	public static boolean wasKeyReleased(int key) {
		return keyUp[key];
	}
	public static boolean isKeyDown(int key) {
		return keyHeld[key];
	}
	public static boolean leftClicked() {
		return leftClick;
	}
	public static boolean rightClicked() {
		return rightClick;
	}
	public static boolean middleClicked() {
		return middleClick;
	}
}
