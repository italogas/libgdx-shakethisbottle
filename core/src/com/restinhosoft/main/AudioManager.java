package com.restinhosoft.main;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class AudioManager{
	private Music music;
	private ArrayList<Sound> soundTrack;
	
	private String musicFileName;
	private ArrayList<String> soundTrackFileNames;
	
	private float volume;
	
	public AudioManager(String gameMusic){
		if(gameMusic!=null && gameMusic!=""){
			this.musicFileName = gameMusic;
			this.music = Gdx.audio.newMusic(Gdx.files.internal(gameMusic));	
		}
		this.soundTrack = new ArrayList<Sound>();
		getVolume();
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
		getVolume();
		music.setVolume(volume);
	}
	
	public void stopMusic(){
		music.stop();
	}
	
	public void close(){
		music.stop();
		music.dispose();
		for(int i=0;i<soundTrack.size();i++){
			soundTrack.get(i).dispose();
		}
	}
	
	public void setVolume(float volume){
		FileHandle local = Gdx.files.local(vol);
		try{
			if(volume>=0 && volume<=1){
				local.writeString(""+volume,false);	
			}
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	private final String vol = "volume.txt"; 
	
	public void getVolume(){
		FileHandle call = Gdx.files.local(vol);
		if(!call.exists()){call.writeString(""+(0.5),false);	}
		String readString = null;
		try {
			FileHandle local = Gdx.files.local(vol);
			readString = local.readString();
			if(readString.equals("")){
				readString = "0,5";
			}
		} catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
		volume = Float.parseFloat(readString);
	}
}
