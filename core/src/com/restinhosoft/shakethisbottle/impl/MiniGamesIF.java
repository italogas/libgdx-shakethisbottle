package com.restinhosoft.shakethisbottle.impl;

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
	
	/**
	 * the available language are brazilian's portuguese and english, english by default
	 * @return "pt-br" to portuguese, "eng" to english
	 */
	String language();//"pt-br" to portuguese, "eng" to english
	
	String congratsMessage();
	String gameOverMessage();
	
	ArrayList<String> getGameSoundTrack();
	
	void setGameID(int id);
	void setGameScore(int score);
	void setGameBonus(int bonus);
	void setGameLevel(int level);
	void setGameTimer(int timer);
	void setGameDifficulty(int difficulty);
	void setGameName(String name) throws Exception;
	void setGameDescription(String description) throws Exception;
	void setGameMusic(String soundArchiveName)	throws Exception;
	
	boolean addToGameSoundTrack(String soundArchiveName)throws Exception;
	boolean removeFromGameSoundTrack(String soundArchiveName)throws Exception;
	
	void theGame();
	void resetTimer();
	void timerCount();
	
	boolean playing();	
	boolean congrats();
	boolean gameOver();
	
	boolean gamePaused();
	boolean gameResume();
	
	void setPause();
	void setResume();
	
	
	
}
