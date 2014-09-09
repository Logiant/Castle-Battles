package game.entities;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

public interface Combat {
	
	
	void findTarget(List<Combat> c);

	void attack();
	
	Vector2f getPosition();
	
	void damage(int dmg);
	
	float getRange();
	
	int getDamage();
	
	boolean isAlive();
	
	int getHealth();
}
