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
		
	public ResourceHandler(int food, int lumber, int metal, int stone, int horse, int magic) {
		this.food = food; this.lumber = lumber; this.metal = metal; this.stone = stone; this.horse = horse; this.magic = magic;
	}
	
	public ResourceHandler() {
		this(0, 0, 0, 0, 0, 0);
	}
	
	@Override
	public String toString() {
		return "Food: " + food + ", Lumber: " + lumber +  ", Metal: " + metal +  
				", Stone: " + stone +  ", Horse: " + horse +  ", Magic: " + magic;  
	}
	
	public boolean canAfford(ResourceHandler cost) {
		return (cost.food < food && cost.lumber < lumber && cost.metal < metal && cost.stone < stone
				&& cost.horse < horse && cost.magic < magic);
	}
	
	public void add(ResourceHandler delivered) {
		food += delivered.food;
		lumber += delivered.lumber;
		metal += delivered.metal;
		stone += delivered.stone;
		horse += delivered.horse;
		magic += delivered.magic;
	}
	
	public void remove(ResourceHandler taken) {
		food -= taken.food;
		lumber -= taken.lumber;
		metal -= taken.metal;
		stone -= taken.stone;
		horse -= taken.horse;
		magic -= taken.magic;
	}

}
