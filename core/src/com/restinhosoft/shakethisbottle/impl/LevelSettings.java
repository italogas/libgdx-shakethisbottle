package com.restinhosoft.shakethisbottle.impl;

/**
 * It defines operations that modify the game settings according to the level.
 * @author Ítalo
 *
 */
public interface LevelSettings {
	
	public int getEnemyHealth();
	
	public int getGameSpeed();
	
	public boolean setEnemyHealth(int health);
	
	public boolean setGameSpeed(int speed);
	
	
}

