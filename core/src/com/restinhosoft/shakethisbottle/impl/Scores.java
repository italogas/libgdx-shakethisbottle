package com.restinhosoft.shakethisbottle.impl;

/**
 * @author Mailson
 *
 */
public class Scores {
	private int score, gameID;
	private String id; // the score id is gameID + "&" + gameID
	
	
	public Scores(int score, int gameId){
		this.id     = gameId + "&" + gameId;
		this.score  = score;
		this.gameID = gameId;
	}
	
	public String getID(){ return this.id;}
	
	public int getScore(){return this.score;}
	
	public int getGameID(){return this.gameID;}
	
	public void setScore(int score){if(score>=0 && score>this.score) this.score = score;}
	
	public void setGameID(int gameId){
		this.gameID = gameId;
		this.id     = gameId + "&" + gameId;
	}
	
	public boolean equals(Scores score){
		if(this.id == score.getID()){return true;}
		return false;
	}
}
