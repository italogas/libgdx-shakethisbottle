package com.restinhosoft.main;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager{
	private Music music;
	private ArrayList<Sound> soundTrack;
	
	private String musicFileName;
	private ArrayList<String> soundTrackFileNames;
	
	
	
	public AudioManager(String gameMusic){
		if(gameMusic!=null && gameMusic!=""){
			this.musicFileName = gameMusic;
			this.music = Gdx.audio.newMusic(Gdx.files.internal(gameMusic));	
		}
		this.soundTrack = new ArrayList<Sound>();
	}
	public Music getMusic(){ return music;}
	
	public ArrayList<Sound> getSoundtrack(){return soundTrack;}
	
	public String getMusicFileName(){ return musicFileName;}
	
	public ArrayList<String> getSoundtrackFileNames(){return soundTrackFileNames;}
	
	public void addToSoundTrack(String sound){
		if(sound!=null && sound!= ""){
			soundTrackFileNames.add(sound);
			soundTrack.add(Gdx.audio.newSound(Gdx.files.internal(sound)));
		}
	}
	
	public void playMusic(){
		music.setLooping(true);
		music.play();
	}
	
	public void stopMusic(){
		music.stop();
	}
	
	public void close(){
		music.dispose();
		for(int i=0;i<soundTrack.size();i++){
			soundTrack.get(i).dispose();
		}
	}
}
