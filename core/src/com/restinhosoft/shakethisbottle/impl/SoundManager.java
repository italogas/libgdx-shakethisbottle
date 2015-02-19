package com.restinhosoft.shakethisbottle.impl;

/**
 * This class is responsible for managing the game sound effects and music. 
 * @author �talo
 *
 */
public class SoundManager {

	public boolean started = false;
	public boolean sound_enabled = false;
	public boolean music_enabled = false;
	public boolean sound_effects_enabled = false;
	public int volume;
	
	private static SoundManager instance = null;
	
	private SoundManager() {}

	/**
	 * It returns the single instance of the SoundManager.
	 * @return
	 */
	public static synchronized SoundManager getInstance() {
		if(instance == null){
			instance = new SoundManager();
		}
		return instance;
	}

	public void load() {
		// TODO call Assets.loadSoundAndMusicAssets();
		started = true;
	}

	public void turnOn() {
		// TODO call method that will start playing the music and sound effects 
		music_enabled = true;
		sound_effects_enabled = true;
	}

	public void turnOff() {
		// TODO call method that will stop playing the music and sound effects 
		music_enabled = false;
		sound_effects_enabled = false;
	}

	public void disableSoundEffects() {
		// TODO call method that will stop playing the sound effects 
		sound_effects_enabled = false;
	}

	public void disableMusic() {
		// TODO call method that will stop playing the music  
		music_enabled = false;
	}

	public void setVolume(int i) {
		if(i >= 0 && i <= 100){
			// TODO call method that will set the volume
			volume = i;
		}
	}
	
}
