package com.restinhosoft.shakethisbottle.ui;

/**
 * It contains operations that return the selected level settings according to the Game. 
 * @author �?talo
 *
 */
public interface GameSettings {
	
	public LevelSettings getEasyLevelSettings();
	
	public LevelSettings getHardLevelSettings();
	
	public LevelSettings getInsaneLevelSettings();
	
	public LevelSettings getNormalLevelSettings();
	
}

