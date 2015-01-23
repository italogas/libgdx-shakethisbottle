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
	private int score;
	private int bonus;
	private int level;//posicao no modo survival
	private int timer;
	private int difficulty;//The difficulty is 1 to easy; 2 to normal; and 3 to hard
	
	private String name;
	private String description;
	private String music;
	
	private ArrayList<String> soundTrack;
	
	private boolean playing;
	private boolean congrats;
	private boolean gameOver;
	private boolean pause;
	private boolean resume;
	
	
	
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
		/*
		//this game
		this.gameOver = false;
		this.congrats = false;
		this.timer = defaultTime;
		this.counterTimer = null;
		this.shake = 0;

		if(difficulty == 1) minimumShakeMove  = 30;
		if(difficulty == 2) minimumShakeMove  = 40;
		if(difficulty == 3) minimumShakeMove  = 45;*/
		
	}

	@Override
	public int getGameID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGamelevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameTimer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameMusic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getGameSoundTrack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameID(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameScore(int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameBonus(int bonus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameLevel(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameTimer(int timer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameDifficulty(int difficulty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameMusic(String soundArchiveName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addToGameSoundTrack(String soundArchiveName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFromGameSoundTrack(String soundArchiveName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void theGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetTimer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timerCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean playing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean congrats() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gamePaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gameResume() {
		// TODO Auto-generated method stub
		return false;
	}
	 

	
}
