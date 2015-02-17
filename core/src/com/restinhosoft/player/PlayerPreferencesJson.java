package com.restinhosoft.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * @author Mailson
 *
 */
public class PlayerPreferencesJson {
	
	private final boolean DefaultSoundEnable= true;
	private final boolean DefaultMusicEnable= true;
	private final boolean DefaultBGMEnable  = true;
		
	private final int DefaultVolume = 25;
	
	private final String DefaultLanguage = "engl";

	/*private boolean sound;
	private boolean music;
	private boolean bgm  ;
	private int    volume;
	
	//option language
	
	private String language;
	*/
	
	//game default preferences
	public void defaultPreferences(){
		this.preferences.sound    = this.DefaultSoundEnable;
		this.preferences.music    = this.DefaultMusicEnable;
		this.preferences.bgm      = this.DefaultBGMEnable;
		this.preferences.volume   = this.DefaultVolume;
		this.preferences.language = this.DefaultLanguage;
	}
	
	public PlayerPreferencesJson(){
		preferences = new Preferences();
		saveHelper = new SavePreferenceHelper();
		defaultPreferences();//method
	}
	
	public void setSoundEnable(boolean enable){
		this.preferences.sound = enable;
		saveHelper.save(preferences);
	}
	
	public void setMusicEnable(boolean enable){
		this.preferences.music = enable;
		saveHelper.save(preferences);
	}
	
	public void setBGMEnable(boolean enable){
		this.preferences.bgm = enable;
		saveHelper.save(preferences);
	}
	
	public void setVolume(int volume){
		if(volume>=0 || volume<=100){
			this.preferences.volume = volume;
		}
		saveHelper.save(preferences);
	}
	
	public void setLanguage(String language){
		if((language != null) && (language.length() == 4)){
			this.preferences.language = language;
		}
		saveHelper.save(preferences);
	}
	
	public boolean getSoundEnable(){
		return saveHelper.load().sound;
	}
	
	public boolean getMusicEnable(){
		return saveHelper.load().music;
	}
	
	public boolean getBGMEnable(){
		return saveHelper.load().bgm;
	}
	
	public int getVolume(){
		return saveHelper.load().volume;
	}
	
	public String getLanguage(){
		return saveHelper.load().language;
	}
	
	//************************************JSON************************************************
	
	private SavePreferenceHelper saveHelper;
	private Preferences preferences;
	
	private static class Preferences{
		public boolean sound = true;
		public boolean music = true;
		public boolean bgm   = true;
		
		public int volume    = 25; 
		
		public String language = "engl" ;
	}
	
	
	private static class SavePreferenceHelper{
		
		public static void save(Preferences pref){
			if(pref!=null){
				Json json = new Json();
				writeFile("player_preferences.sav", json.toJson(pref));	
			}
		}
		
		public static Preferences load(){
			String save = readFile("player_preferences.sav");
			if (!save.isEmpty()) {
				Preferences pref = new Preferences();
	 
				Json json = new Json();
				Preferences Jpref = json.fromJson(Preferences.class, save);
				
				pref.sound   = Jpref.sound;
				pref.music   = Jpref.music;
				pref.bgm     = Jpref.bgm;
				pref.volume  = Jpref.volume;
				pref.language= Jpref.language;
				return pref;
			}
			return null;
		}
		
		public static void writeFile(String fileName, String s) {
			FileHandle file = Gdx.files.local(fileName);
			file.writeString(com.badlogic.gdx.utils.Base64Coder.encodeString(s), false);
		}	
	 
		public static String readFile(String fileName) {
			FileHandle file = Gdx.files.local(fileName);
			if (file != null && file.exists()) {
				String s = file.readString();
				if (!s.isEmpty()) {
					return com.badlogic.gdx.utils.Base64Coder.decodeString(s);
				}
			}
			return "";
		}
		
	}
}
