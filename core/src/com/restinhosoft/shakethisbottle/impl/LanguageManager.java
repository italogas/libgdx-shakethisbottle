package com.restinhosoft.shakethisbottle.impl;

import com.restinhosoft.player.PlayerPreferencesIOBuffer;
import com.restinhosoft.shakethisbottle.exception.LanguageManagerNotStartedException;

public class LanguageManager {
	
	public boolean started = false;
	
	private PlayerPreferencesIOBuffer userSettings;
	
	private static LanguageManager instance = null;
	
	private LanguageManager() {}

	/**
	 * It returns the single instance of the LanguageManager.
	 * @return
	 */
	public static synchronized LanguageManager getInstance() {
		if(instance == null){
			instance = new LanguageManager();
		}
		return instance;
	}

	public void load() {
		try{
			userSettings = new PlayerPreferencesIOBuffer();
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
		started = true;
	}
	
	public String getLanguage() throws Exception{
		if(!started){
			throw new LanguageManagerNotStartedException();
		}
		
		String language = null;
		try{
			language = userSettings.getLanguage();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return language;
	}
	
	public void setLanguage(String language) throws Exception{
		if(!started){
			throw new LanguageManagerNotStartedException();
		}
		
		try{
			userSettings.setLanguage(language);;
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return;
	}

}
