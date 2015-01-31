package com.restinhosoft.shakethisbottle.impl;

/**
 * It contains operations that return the selected level settings according to the Game. 
 * @author Ítalo
 *
 */
public interface GameSettings {
	
	public LevelSettings getEasyLevelSettings();
	
	public LevelSettings getHardLevelSettings();
	
	public LevelSettings getInsaneLevelSettings();
	
	public LevelSettings getNormalLevelSettings();
	
}

