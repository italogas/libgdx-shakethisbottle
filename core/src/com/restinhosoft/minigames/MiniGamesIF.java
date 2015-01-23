package com.restinhosoft.minigames;

import java.util.ArrayList;

/**
 * @author Mailson
 *
 */
public interface MiniGamesIF {
	int getGameID();
	int getGameScore();
	int getGameBonus();
	int getGamelevel();
	int getGameTimer();
	
	/**
	 * The difficulty is 1 to easy; 2 to normal; and 3 to hard
	 * @return difficulty
	 */
	int getGameDifficulty();
	
	String getGameName();
	String getGameDescription();
	String getGameMusic();
	
	ArrayList<String> getGameSoundTrack();
	
	void setGameID(int id);
	void setGameScore(int score);
	void setGameBonus(int bonus);
	void setGameLevel(int level);
	void setGameTimer(int timer);
	void setGameDifficulty(int difficulty);
	void setGameName(String name);
	void setGameDescription(String description);
	void setGameMusic(String soundArchiveName);
	
	boolean addToGameSoundTrack(String soundArchiveName);
	boolean removeFromGameSoundTrack(String soundArchiveName);
	
	void theGame();
	void resetTimer();
	void timerCount();
	
	boolean playing();	
	boolean congrats();
	boolean gameOver();
	
	boolean gamePaused();
	boolean gameResume();
	

	
}
