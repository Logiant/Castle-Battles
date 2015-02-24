package game.units.ai;

import java.util.LinkedList;
import java.util.List;

public class SequenceNode extends Node{

	List<Node> children;
	
	public SequenceNode(List<Node> children) {
		this.children = new LinkedList<Node>();
		this.children.addAll(children);
	}
	
	@Override
	public STATE update() {
		STATE s = STATE.Failure;
		for (Node n : children) {
			s = n .update();
			if (s == STATE.Failure){
				break;
			}
		}
		state = s;
		return state;
	}

	@Override
	public void start() {
		state = STATE.Running;
	}

	@Override
	public void stop() {
		state = STATE.Stopped;
	}
	
	

}
