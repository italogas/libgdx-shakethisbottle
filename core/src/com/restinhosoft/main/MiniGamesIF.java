package com.restinhosoft.main;

import com.badlogic.gdx.Screen;

public interface MiniGamesIF{
	
	//public void setName(String name);
	//public void setDescription(String desc);
	public void   setSurvival(boolean mode);
	public void setDifficulty(String difficulty);
	
	public void setScore(int score);
	public void setLevel(int level);
	public void setBonus(int bonus);
	
	public void setPause(boolean pause);
	
	public String getName();
	public String getDescription();
	public boolean getSurvival();
	public String getDifficulty();
	
	public int getScore();
	public int getLevel();
	public int getBonus();
	
	public boolean paused();
	public boolean saveTempGame();
	public boolean loadTempGame();
	
}
