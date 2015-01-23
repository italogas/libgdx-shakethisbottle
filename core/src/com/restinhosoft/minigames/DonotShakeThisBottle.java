package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mailson
 *
 */
public class DonotShakeThisBottle implements MiniGamesIF{
	
	private int id;
	private String name;
	private String description;
	
	private int difficulty;
	private int score;
	
	private String music;
	
	private ArrayList<String> soundTrack;
	
	private boolean congrats;
	private boolean gameOver;
	
	private int level;//posicao no modo survival
	
	public DonotShakeThisBottle(int difficulty, int level){
		this.id = 2;
		this.name = "DO NOT SHAKE THIS BOTTLE";
		
		this.description = "In this mini game you can not move the bottle,"
						 + " because it contains an unstable liquid that can explode."
						 + " To save the world, you should deicar your smartphone stable during the countdown,"
						 + " and if the move recklessly device, it's game over.";
		
		if(difficulty>=1 && difficulty<=3){
			this.difficulty = difficulty;
		}

		this.score = score;
		
		this.music = "none";
		this.soundTrack =  new ArrayList<String>();
		
		if(level >=1) this.level = level;
		
		this.gameOver = false;
		this.congrats = false;
		this.timer = defaultTime*difficulty;
		
		this.counterTimer = null;
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
	public void theGame() {
		if(timer == 0){
			congrats = true;
			score = timer*difficulty;
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
	                    	if(timer == 0 || moved) gameOver = true;
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
	public void timerCount() {
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
	//this game methods
	
	private Timer counterTimer;
	
	private boolean moved;
	
	private final int defaultTime = 30;
	
	private int timer;
	
	public int getTimer(){
		return this.timer;		
	}
	
	public boolean getMoved(){
		return this.moved;
	}
	
	public void resetTimer(){
		this.timer = defaultTime*difficulty;
	}
	
	public void setMoved(boolean moved){
		this.moved = moved;
	}
}
