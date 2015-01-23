package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mailson
 *
 */
public class MemorizeFast implements MiniGamesIF{
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
	
	public MemorizeFast(int difficulty,int level){
		this.id = 4;
		this.name = "MEMORIZE FAST";
		
		this.description = "News !? the world is in danger and you once again have to save it."
						+ " This time a very ardilozo villain prepared a trap with missiles."
						+ " To stop him you have to record the color sequence given by him."
						+ " You have 10 seconds to set the color sequence if err it's game over.";
		
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
	
	//memorize fast
	
	private int timer;
	private int next;//the next to add on chooseSequence
	
	private Timer counterTimer;
	
	private final int defaultTime = 10;//segundos

	
	private ArrayList<Integer> trueSequence, chooseSequence;
	//colors sequence 0-red; 1-yellow; 2-green; 3-blue
	
	
	
	public int getTimer(){
		return this.timer;		
	}
	
	public void resetTimer(){
		this.timer = defaultTime;
	}
	
	
	public int getNext(){
		return this.next;
	}
	
	private void buildGameSequence(){
		for(int i = 0;i < 4;i++){ trueSequence.add(i);}
		Collections.shuffle(trueSequence);
	}
	
	public boolean complete(){
		for(int i = 0;i < trueSequence.size();i++){
			if(trueSequence.get(i)!= chooseSequence.get(i)){
				return false;
			}
		}
		return true;
	}
	
	public boolean addColors(int color){
		if(color >=0 && color <= 3){
			if(color==trueSequence.get(next)){
				synchronized (this) {
					chooseSequence.add(color);
					next++;
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Integer> getSequence(){
		return trueSequence;
	}
}
