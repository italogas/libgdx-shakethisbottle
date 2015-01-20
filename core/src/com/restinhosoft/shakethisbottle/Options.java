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
	private final int defaultVol;
	

	public Options(boolean enableSound, boolean enableSoundTrack, boolean enableMusic, int volume){
		this.enableSound = enableSound;
		this.enableSoundTrack = enableSoundTrack;
		this.enableMusic = enableMusic;
		
		this.volume = volume;
		
		this.maxVolume = 100;
		this.defaultVol = 50;
		this.minVolume = 0;
		
		if(!enableSound){
			this.enableSoundTrack = false;
			this.enableMusic = false;
		}
		
		if(this.volume < minVolume || this.volume > maxVolume){
			this.volume = defaultVol;
		}
	}
	
	public boolean getEnableSound(){ return this.enableSound;}
	
	public boolean getEnableSoundTrack(){ return this.enableSoundTrack;}
	
	public boolean getEnableMusic(){return this.enableMusic;}
	
	public int getVolume(){ return this.volume;}
	
	public void setEnableSound(boolean enable){ 
		this.enableSound = enable;
		if(!enable){
			this.enableSoundTrack = false;
			this.enableMusic = false;
		}
	}
	
	public void setEnableSoundTrack(boolean enable){
		if(enableSound){this.enableSoundTrack = enable;}
		else{enableSoundTrack = false;}
	}
	
	public void setEnableMusic(boolean enable){ 
		if(enableSound){this.enableMusic = enable;}
		else{enableMusic=false;}
	}
	
	public boolean setVolume(int volume){
		if(volume >= minVolume && volume <=maxVolume){
			this.volume = volume;
			return true;
		}
		return false;
	}
	
}
