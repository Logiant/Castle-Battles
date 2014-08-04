/**
 * This class stores data for handling resources
 * @Author Logan Beaver, 7/30/14
 */
package game.buildings;

public class ResourceHandler {

	public int food;
	public int lumber;
	public int metal;
	public int stone;
	public int horse;
	public int magic;
	
	@Override
	public String toString() {
		return "Food: " + food + ", Lumber: " + lumber +  ", Metal: " + metal +  
				", Stone: " + stone +  ", Horse: " + horse +  ", Magic: " + magic;  
	}

}
