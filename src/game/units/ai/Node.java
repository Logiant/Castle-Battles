package game.units.ai;

enum STATE {
	Success, Failure, Running, Stopped;
}

public abstract class Node {

	protected STATE state = STATE.Stopped;
	
	public abstract STATE update();
	
	public abstract void start();
	
	public abstract void stop();
}
