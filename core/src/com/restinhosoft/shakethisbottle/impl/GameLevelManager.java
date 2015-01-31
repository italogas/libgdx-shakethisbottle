package com.restinhosoft.shakethisbottle.impl;

/**
 * This class is responsible for managing the selected game level settings  
 * @author Ítalo
 *
 */
public class GameLevelManager {

	private GameLevelManager instance;

	private GameLevelManager(){}
	
	/**
	 * Singleton holder, it returns GameLevelManager single instance.
	 * @return GameLevelManager
	 */
	public GameLevelManager getInstance(){
		if(instance == null){
			instance = new GameLevelManager();
		}
		return instance;
	}

	public GameSettings setSpecificLevelSettings(String level) {
		// TODO implement me
		return null;	
	}
	
}

