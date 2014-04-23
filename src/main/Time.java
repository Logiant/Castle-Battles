package main;

/**
 * this class holds the time between frames
 * multiply all motion by dt to make it framerate independent
 * @author beaverl
 *
 */
public class Time {

	public static float dt; //time since last frame in MS
	
	private static long lastTime = System.nanoTime();
	
	public static void update() {
		long thisTime = System.nanoTime();
		dt = (thisTime-lastTime)/1000000f;
		lastTime = thisTime;
	}
}
