package com.restinhosoft.player;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

/**
 * @author Mailson
 *
 */
public class PlayerPreferencesGdx {
	//option sound
	private final boolean DefaultSoundEnable= true;
	private final boolean DefaultMusicEnable= true;
	private final boolean DefaultBGMEnable  = true;
	
		
	private final int DefaultVolume = 25;
	
	private final String sound = "sound";
	private final String music = "music";
	private final String bgm   = "bgm";
	private final String volume= "volume";
	
	//option language
	private final String DefaultLanguage = "engl";
	
	private final String language = "language";
	
	//general
	private Preferences preferences;
	
	public PlayerPreferencesGdx(){
		if(!Gdx.files.local("My Preferences.txt").exists()){
			FileHandle pref = Gdx.files.local("My Preferences.txt");	
		}
		
		preferences = Gdx.app.getPreferences("My Preferences.txt");
		defaultPreferences();//method
	}
	
	public void setSoundEnable(boolean enable){
		preferences.putBoolean(sound,enable);
	}
	
	public void setMusicEnable(boolean enable){
		preferences.putBoolean(music,enable);
	}
	public void setBGMEnable(boolean enable){
		preferences.putBoolean(bgm,enable);
	}
	
	public void setVolume(int volume){
		if(volume>=0 || volume<=100){
			preferences.putInteger(this.volume, volume);
		}
	}
	
	public void setLanguage(String language){
		if((language != null) && (language.length() == 4)){
			preferences.putString(this.language, language);
		}
	}
	
	public boolean getSoundEnable(){
		return preferences.getBoolean(sound);
	}
	
	public boolean getMusicEnable(){
		return preferences.getBoolean(music);
	}
	
	public boolean getBGMEnable(){
		return preferences.getBoolean(bgm);
	}
	
	public int getVolume(){
		return preferences.getInteger(volume);
	}
	
	public String getLanguage(){
		return preferences.getString(language);
	}
	
	
	//game default preferences
	public void defaultPreferences(){
		if(preferences != null){
			preferences.putBoolean(sound, DefaultSoundEnable);
			preferences.putBoolean(music, DefaultMusicEnable);
			preferences.putBoolean(bgm  , DefaultBGMEnable);
			
			preferences.putInteger(volume, DefaultVolume);
			
			preferences.putString(language,DefaultLanguage);	
		}
	}
}
