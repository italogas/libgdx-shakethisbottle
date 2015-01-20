package com.restinhosoft.shakethisbottle;

/**
 * @author Mailson
 *
 */
public class Options {
	private boolean enableSound;
	private boolean enableSoundTrack;
	private boolean enableMusic;
	private int volume;
	
	private final int maxVolume;
	private final int minVolume;
	

	public Options(boolean enableSound, boolean enableSoundTrack, boolean enableMusic, int volume){
		this.enableSound = enableSound;
		this.enableSoundTrack = enableSoundTrack;
		this.enableMusic = enableMusic;
		this.volume = volume;
		
		this.maxVolume = 100;
		this.minVolume = 0;
	}
	
	public boolean getEnableSound(){ return this.enableSound;}
	
	public boolean getEnableSoundTrack(){ return this.enableSoundTrack;}
	
	public boolean getEnableMusic(){return this.enableMusic;}
	
	public int getVolume(){ return this.volume;}
	
	public void setEnableSound(boolean enable){ this.enableSound = enable;}
	
	public void setEnableSoundTrack(boolean enable){ this.enableSoundTrack = enable;}
	
	public void setEnableMusic(boolean enable){ this.enableMusic = enable;}
	
	public boolean setVolume(int volume){
		if(volume >= minVolume && volume <=maxVolume){
			this.volume = volume;
			return true;
		}
		return false;
	}
	
}
