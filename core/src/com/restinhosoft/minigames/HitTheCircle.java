package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class HitTheCircle  implements MiniGamesIF{

	private int id;
	private String name;
	private String description;
	
	private int difficulty;
	private int score;
	
	private String music;
	
	private ArrayList<String> soundTrack;
	
	private boolean congrats;
	private boolean gameOver;
	
	private int level;
	
	
	
	public HitTheCircle(int difficulty,int level) {
		//comum
		this.id = 3;
		this.name = "HIT THE CIRCLE";
		
		this.description = "A bomb was armed and to save the world you have to disarm it."
						+ " This is quite simple to turn the circles into squares before time runs out."
						+ " In this minigame you need to hit the circles on the screen to turn them into squares."
						+ " When only squares you win the minigame.";
		
		if(difficulty>=1 && difficulty<=3){
			this.difficulty = difficulty;
		}

		this.score = score;
		
		this.music = "none";
		this.soundTrack =  new ArrayList<String>();
		
		if(level >=1) this.level = level;
		
		this.gameOver = false;
		this.congrats = false;
	}
	
	@Override
	public int getGameID() {
		return this.id;
	}

	@Override
	public int getGameScore() {	
		return this.score;
	}

	@Override
	public int getGamelevel() {
		return this.level;
	}

	@Override
	public int getGameDifficulty() {
		return this.difficulty;
	}

	@Override
	public String getGameName() {
		return this.name;
	}

	@Override
	public String getGameDescription() {
		return this.description;
	}

	@Override
	public String getGameMusic() {
		return this.music;
	}

	@Override
	public ArrayList<String> getGameSoundTrack() {
		return this.soundTrack;
	}

	@Override
	public void setGameID(int id) {//ainda a pensar
		if(id >=0){ this.id = id;}
	}

	@Override
	public void setGameScore(int score) {
		if(score>=0) this.score = score;
	}

	@Override
	public void setGameLevel(int level) {
		if(level >=1) this.level = level;
	}

	@Override
	public void setGameDifficulty(int difficulty) {
		if(difficulty>=1 && difficulty<=2){
			this.difficulty = difficulty;
		}
	}

	@Override
	public void setGameName(String name) {
		this.name = name;
	}

	@Override
	public void setGameDescription(String description) {
		this.description = description;
	}

	@Override
	public void setGameMusic(String soundArchiveName) {
		this.music = soundArchiveName;
	}

	@Override
	public void addToGameSoundTrack(String soundArchiveName) {
		this.soundTrack.add(soundArchiveName);
	}

	@Override
	public boolean removeFromGameSoundTrack(String soundArchiveName) {
		if(this.soundTrack.remove(soundArchiveName))return true;
		else return false;
	}

	@Override
	public void game() {
		if(complete()) {
			congrats = true;
		}
		else if(!gameOver){
			//contadorRegressivo
			long second = 1000;   
		      
		    if (counterTimer == null) {  
		    	counterTimer = new Timer();  
		        TimerTask task = new TimerTask() {  
	                public void run() {  
	                    try { 
	                    	if(timer > 0) timer--;
	                    	if(timer == 0 && !complete() ) gameOver = true;
	                     } catch (Exception e) {  
	                          e.printStackTrace();  
	                     }  
	                }  
		       };  
		       counterTimer.scheduleAtFixedRate(task, second, second);  
		    }	
		}
	}
	
	@Override
	public void playing() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean congrats() {
		return this.congrats;
	}


	@Override
	public boolean gameOver() {
		return this.gameOver;
	}
	
	//hitthecircle minigame
	private Timer counterTimer;
	
	private final int defaultTime = 5;//segundos
	
	private int timer;
	
	private ArrayList<Boolean> isSquare;
	
	public int getTimer(){
		return this.timer;		
	}
	
	public void resetTimer(){
		this.timer = defaultTime;
	}
	
	private void buildGameSequence(){
		for(int i = 0;i <= 11;i++) {
			if(i<=5){ isSquare.add(true); }
			else	{ isSquare.add(false);}
		}
		Collections.shuffle(isSquare);
	}
	
	public boolean hitting(int position){
		if(position >= 0 && position <= 11){
			synchronized (this) {
				if(isSquare.get(position)== false){
					isSquare.set(position, true);
					return true;
				}else{ return false;}
			}
		}
		return false;
	}
		
		public boolean complete(){
			for(int i=0; i<isSquare.size();i++){if(isSquare.get(i)==false){ return false;} }// if there is a circle returns false
			return true;
		}
		
		public ArrayList<Boolean> getSequence(){ return isSquare;}
}
