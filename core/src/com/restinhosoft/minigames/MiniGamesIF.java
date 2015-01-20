package com.restinhosoft.minigames;

import java.util.ArrayList;

/**
 * @author Mailson
 *
 */
public interface MiniGamesIF {
	int getGameID();
	int getGameScore();
	int getGamelevel();
	
	/**
	 * The difficulty is 0 to easy; 1 to normal; and 2 to hard
	 * @return
	 */
	int getGameDifficulty();
	
	String getGameName();
	String getGameDescription();
	String getGameMusic();
	
	ArrayList<String> getGameSoundTrack();
	
	void setGameID(int id);
	void setGameScore(int score);
	void setGameLevel(int level);
	void setGameDifficulty(int difficulty);
	void setGameName(String name);
	void setGameDescription(String description);
	void setGameMusic(String soundArchiveName);
	
	void addToGameSoundTrack(String soundArchiveName);
	void removeFromGameSoundTrack(String soundArchiveName);
}
