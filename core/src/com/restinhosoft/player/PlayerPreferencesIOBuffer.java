package com.restinhosoft.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * @author Mailson
 *
 */
public class PlayerPreferencesIOBuffer {
	private File file;
	
	private final String DefaultSoundEnable= "true";
	private final String DefaultMusicEnable= "true";
	private final String DefaultBGMEnable  = "true";
	private final String DefaultVolume     = ""+25;
	private final String DefaultLanguage   = "engl";

	private boolean sound;
	private boolean music;
	private boolean bgm;
	
	private int volume;
	
	private String language;
	
	
	//game default preferences
	public void defaultPreferences(){
		save[0] = DefaultSoundEnable;
		save[1] = DefaultMusicEnable;
		save[2] = DefaultBGMEnable;
		save[3] = DefaultVolume;
		save[4] = DefaultLanguage;

		saveIO();
	}
	private void defaultPreferencesInitial(){
		save[0] = DefaultSoundEnable;
		save[1] = DefaultMusicEnable;
		save[2] = DefaultBGMEnable;
		save[3] = DefaultVolume;
		save[4] = DefaultLanguage;
	}
	
	public PlayerPreferencesIOBuffer(){
		this.file = new File("player_preferences.txt");
		this.file.setWritable(true);
		this.file.setReadable(true);
		
		defaultPreferencesInitial();//method
	}
	
	public void setSoundEnable(boolean enable){
		this.sound = enable;
		if(sound) save[0]= "true";
		else save[0] = "false";
		saveIO();
	}
	
	public void setMusicEnable(boolean enable){
		this.music = enable;
		if(music) save[1]= "true";
		else save[1] = "false";
		saveIO();
	}
	
	public void setBGMEnable(boolean enable){
		this.bgm = enable;
		if(bgm) save[2]= "true";
		else save[2] = "false";
		saveIO();

	}
	
	public void setVolume(int volume){
		if(volume>=0 && volume<=100){
			this.volume = volume;
			save[3] = ""+volume;
			saveIO();
		}
	}
	
	public void setLanguage(String language){
		if((language != null) && (language.length() == 4)){
			this.language = language;
			save[4]= language;
			saveIO();
		}
	}
	
	public boolean getSoundEnable(){
		loadIO();
		if(save[0].equals("true")) this.sound = true;
		else this.sound = false;
		return this.sound;
	}
	
	public boolean getMusicEnable(){
		loadIO();
		if(save[1].equals("true")) this.music = true;
		else this.music = false;
		return this.music;
	}
	
	public boolean getBGMEnable(){
		loadIO();
		if(save[2].equals("true")) this.bgm = true;
		else this.bgm = false;
		return this.bgm;
	}
	
	public int getVolume(){
		loadIO();
		volume = Integer.parseInt(save[3]);
		return this.volume;
	}
	
	public String getLanguage(){
		loadIO();
		language = save[4];
		return this.language;
	}
	
	//************************************IO BUFFER************************************************
	
	private final int numberOfPreferences = 5;
	private String[] save = new String[numberOfPreferences]; 
	
	private String convertToOneString(String[] array){
		String one = "";
		for(int i=0; i < array.length;i++){
			one+= array[i]+" ";
		}
		return one;
	}
	
	private String[] convertToArrayString(String one){
		String[] array = one.split(" ");
		return array;
	}
	
	
	private void saveIO(){
		Charset charset = Charset.forName("US-ASCII");
		String s = convertToOneString(save);
		try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), charset)) {
		    writer.write(s, 0, s.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	private String[] loadIO(){
		Charset charset = Charset.forName("US-ASCII");
		String read = "";
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		       read = line+" "; 
		    }
		    save = convertToArrayString(read);
		    return convertToArrayString(read);
		} catch (IOException x) {
		}
		save = convertToArrayString(read);
		return convertToArrayString(read);
	}
}
