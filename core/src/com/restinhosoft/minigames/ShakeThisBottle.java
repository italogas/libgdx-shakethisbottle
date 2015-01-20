package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mailson
 *
 */
public class ShakeThisBottle implements MiniGamesIF{
	
	private int id;
	private String name;
	private String description;
	
	private int difficulty;
	private int score;
	
	private String music;
	
	private ArrayList<String> soundTrack;
	
	private int level;//posicao no modo survival
	
	public ShakeThisBottle(int difficulty, int level){
		this.id = 1;
		this.name = "SHAKE THIS BOTTLE";
		
		this.description = "In this minigame you have to shake the bottle to celebrate the new year,"
						+ " if the bottle not burst will be the end of the world,"
						+ " you have until the end of the count to save the world, good luck.";
		
		if(difficulty>=1 && difficulty<=3){
			this.difficulty = difficulty;
		}

		this.score = score;
		
		this.music = "none";
		this.soundTrack =  new ArrayList<String>();
		
		if(level >=1) this.level = level;
		
		//this game
		this.gameOver = false;
		this.congrats = false;
		this.timer = defaultTime;
		this.counterTimer = null;
		this.shake = 0;

		if(difficulty == 1) minimumShakeMove  = 30;
		if(difficulty == 2) minimumShakeMove  = 40;
		if(difficulty == 3) minimumShakeMove  = 45;
		
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
		if(shake >= minimumShakeMove){
			this.congrats = true;
			this.score = minimumShakeMove*timer;
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
	                    	if(timer == 0 && (shake<minimumShakeMove) ) gameOver = true;
	                     } catch (Exception e) {  
	                          e.printStackTrace();  
	                     }  
	                }  
		       };  
		       counterTimer.scheduleAtFixedRate(task, second, second);  
		    }	
		}
	}
	
	//this game methods
	
	private Timer counterTimer;
	private int shake;
	private int minimumShakeMove;
	
	private boolean congrats;
	private boolean gameOver;
	
	private final int defaultTime = 30;//segundos
	
	private int timer;
	
	public int getTimer(){
		return this.timer;		
	}
	
	public void resetTimer(){
		this.timer = defaultTime;
	}
	
	public void moved(){
		shake++;
	}
	
	public boolean getGameOver(){ return gameOver;}
	
	public boolean getCongrats(){
		return this.congrats;
	}
}
