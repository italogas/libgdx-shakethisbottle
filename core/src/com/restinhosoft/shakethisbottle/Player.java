package com.restinhosoft.shakethisbottle;

import java.util.ArrayList;

import com.restinhosoft.shakethisbottle.remendogametest.gametest;

/**
 * Player Class
 * @author Mailson
 *
 */
public class Player {
	private String ID;
	private String name;
	
	private int numberOfAchievements;
	
	private Options option;
	
	private ArrayList<Achievements> listOfAchievements;
	private ArrayList<Scores> listOfHighScores;
	
	public Player(String name, String ID){
		this.name = name;
		this.ID = ID;
		
		this.listOfAchievements = new ArrayList<Achievements>();
		this.listOfHighScores   = new ArrayList<Scores>();
		
		this.numberOfAchievements = listOfAchievements.size();
		
		this.option = new Options(true,true,true,50);//ainda  a consertar 
		
	}
	
	public String getID(){return ID;}
	
	public String getName(){ return name;}
	
	public int getNumberOfAchievements(){ return numberOfAchievements;}
	
	public ArrayList<Achievements> getAllAchievements(){ return listOfAchievements;}
	
	public ArrayList<Scores> getHighScores(){return listOfHighScores;}
	
	public Options getPlayerOptions(){ return this.option;}
	
	public boolean setID(String ID){ 
		if(gametest.validIDSet(ID)){//classe teste enquanto nao eh implementado o banco de dados
			this.ID = ID;
			return true;
		}
		return false;
	}
	
	public void setName(String name){this.name = name;}
	
	public void addScore(Scores score){
		boolean updated = false;
		for(int i=0; i < listOfHighScores.size();i++){
			if(score.equals(listOfHighScores.get(i))){
				listOfHighScores.get(i).setScore(score.getScore());
				updated= true;
			}
		}
		if(!updated){ listOfHighScores.add(score);}
	}
	
	public void addAchievements(Achievements achievements){
		if(!listOfAchievements.contains(achievements)){
			listOfAchievements.add(achievements);
		}
	}
	
	public void setPlayerOptions(Options option){this.option = option;}
	
}
