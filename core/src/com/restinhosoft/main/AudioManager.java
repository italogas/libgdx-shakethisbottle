package com.restinhosoft.main;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.restinhosoft.ui.SoundOptionsScreen;

public class AudioManager{
	private Music music;
	private ArrayList<Sound> soundTrack;
	
	private String musicFileName;
	private ArrayList<String> soundTrackFileNames;
	
	private static String[] options;
	
	private float volume;
	
	private boolean enableSound;
	private boolean enableMusic;
	private boolean enableSoundtrack;
	
	public AudioManager(String gameMusic) throws InvalidParameterException {
		if(gameMusic!=null && gameMusic!=""){
			this.musicFileName = gameMusic;
			this.music = Gdx.audio.newMusic(Gdx.files.internal(gameMusic));	
		} else {
			throw new InvalidParameterException();
		}

		this.soundTrack = new ArrayList<Sound>();
		this.soundTrackFileNames = new ArrayList<String>();
		
		options = new SoundOptionsScreen().loadOptions().split(" ");
		loadOptions();
		
	}
	
	private void loadOptions(){
		enableSound = Boolean.parseBoolean(options[0]);
		enableMusic = Boolean.parseBoolean(options[1]);
		enableSoundtrack = Boolean.parseBoolean(options[2]);
		volume = Float.parseFloat(options[3]);
	}
		
	public Music getMusic(){ return music;}
	
	public ArrayList<Sound> getSoundtrack(){return soundTrack;}
	
	public String getMusicFileName(){ return musicFileName;}
	
	public ArrayList<String> getSoundtrackFileNames(){return soundTrackFileNames;}
	
	public void addToSoundTrack(String sound){
		if(sound!=null && sound!= "" ){
			soundTrackFileNames.add(sound);
			Sound s = Gdx.audio.newSound(Gdx.files.internal(sound));
			if(enableSoundtrack && enableSound){
				soundTrack.add(s);
			}else{
				soundTrack.add(Gdx.audio.newSound(Gdx.files.internal("mudo.ogg")));	
			}
			
		}
		
	}
	
	public void playMusic(){
		if(enableSound && enableMusic){
			music.setLooping(true);
			music.play();
			getVolume();
			music.setVolume(volume);	
		}
		
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
	
	/*public void setVolume(float volume){
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
	}*/
	
	public void getVolume(){
		loadOptions();
	}
}
