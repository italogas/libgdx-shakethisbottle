package com.restinhosoft.shakethisbottle.ui;

/**
 * It defines operations that modify the game settings according to the level.
 * @author Ã?talo
 *
 */
public interface LevelSettings {
	
	public int getEnemyHealth();
	
	public int getGameSpeed();
	
	public boolean setEnemyHealth(int health);
	
	public boolean setGameSpeed(int speed);
	
	
}

