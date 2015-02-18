package com.restinhosoft.player;

import java.io.IOException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


/**
 * @author Mailson
 *
 */
public class PlayerPreferencesApplicationListener implements ApplicationListener {
	private FileHandle file; 
	private final String fileName = "player_preferences.sav";
	
	
	private final boolean DefaultSoundEnable= true;
	private final boolean DefaultMusicEnable= true;
	private final boolean DefaultBGMEnable  = true;
		
	private final int DefaultVolume = 25;
	
	private final String DefaultLanguage = "engl";

	public void defaultPreferences(){
		this.preferences.sound    = this.DefaultSoundEnable;
		this.preferences.music    = this.DefaultMusicEnable;
		this.preferences.bgm      = this.DefaultBGMEnable;
		this.preferences.volume   = this.DefaultVolume;
		this.preferences.language = this.DefaultLanguage;
	}
	
	public PlayerPreferencesApplicationListener(){
		create();
				
		preferences = new Preferences();
		defaultPreferences();//method
	}
	
	public void setSoundEnable(boolean enable){
		this.preferences.sound = enable;
		writePreferences(preferences);
	}
	
	public void setMusicEnable(boolean enable){
		this.preferences.music = enable;
		writePreferences(preferences);
	}
	
	public void setBGMEnable(boolean enable){
		this.preferences.bgm = enable;
		writePreferences(preferences);
	}
	
	public void setVolume(int volume){
		if(volume>=0 || volume<=100){
			this.preferences.volume = volume;
		}
		writePreferences(preferences);
	}
	
	public void setLanguage(String language){
		if((language != null) && (language.length() == 4)){
			this.preferences.language = language;
		}
		writePreferences(preferences);
	}
	
	public boolean getSoundEnable(){
		return loadPreferences().sound;
	}
	
	public boolean getMusicEnable(){
		return loadPreferences().music;
	}
	
	public boolean getBGMEnable(){
		return loadPreferences().bgm;
	}
	
	public int getVolume(){
		return loadPreferences().volume;
	}
	
	public String getLanguage(){
		return loadPreferences().language;
	}
	
	//************************************JSON************************************************
	
	/*private SavePreferenceHelper saveHelper;
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
				//System.out.println(json.toJson(pref));
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
			System.out.println(fileName);
			System.out.println(Gdx.files);
			FileHandle file = Gdx.files.local(fileName);
			System.out.println("file:" +file);
			file.writeString(com.badlogic.gdx.utils.Base64Coder.encodeString(s), false);
			try{
				System.out.println(fileName);
				System.out.println(Gdx.files);
				FileHandle file = Gdx.files.local(fileName);
				System.out.println("file:" +file);
				file.writeString(com.badlogic.gdx.utils.Base64Coder.encodeString(s), false);	
			}catch(Exception  x){
				System.err.format("IOException: %s%n", x);
			}
			
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
		
	}*/
	
	private Preferences preferences;
	
	private static class Preferences{
		public boolean sound = true;
		public boolean music = true;
		public boolean bgm   = true;
		
		public int volume    = 25; 
		
		public String language = "engl" ;
	}
	
	private void writePreferences(Preferences pref){
		if(pref!=null){
			Json json = new Json();
			writeFile(fileName, json.toJson(pref));	
		}
	}
	
	private void writeFile(String fileName, String s){
		file.writeString(com.badlogic.gdx.utils.Base64Coder.encodeString(s), false);
	}
	 
	private Preferences loadPreferences(){
		String save = readFile(fileName);
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
	
	public String readFile(String fileName) {
		this.file = Gdx.files.local(fileName);
		if (this.file != null && this.file.exists()) {
			String s = this.file.readString();
			if (!s.isEmpty()) {
				return com.badlogic.gdx.utils.Base64Coder.decodeString(s);
			}
		}
		return "";
	}
	
	@Override
	public void create() {
		System.out.println(Gdx.files);
		//file = Gdx.files.local(this.fileName);
		file = Gdx.files.local("File_Local.txt");
		 if(!file.exists())
	       {
	             try {
	                    file.file().createNewFile();
	             } catch (IOException e) {
	                    e.printStackTrace();
	             }
	       }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


}
