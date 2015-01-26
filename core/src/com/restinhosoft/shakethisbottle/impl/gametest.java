package com.restinhosoft.shakethisbottle.impl;

import java.util.ArrayList;


public class gametest {
	private static ArrayList<String> playersIdList;
	private ArrayList<String> playersNameList;
	private ArrayList<Player> playersList;
	
	private ArrayList<Achievements> achievementsList;
	
	private ArrayList<Scores> scoresList;
	
	@SuppressWarnings("static-access")
	public gametest(){
		this.playersIdList  = new ArrayList<String>();
		this.playersNameList= new ArrayList<String>();
		this.playersList 	= new ArrayList<Player>();
		
		this.achievementsList = new ArrayList<Achievements>();
		this.scoresList = new ArrayList<Scores>();
	}

	
	//player------------------------------------------------------------------------*******************************
	public boolean addPlayer(Player player){
		if(!playersIdList.contains(player.getID()) && !playersNameList.contains(player.getName())){
			playersList.add(player);
			playersIdList.add(player.getID());
			playersNameList.add(player.getName());
			return true;
		}
		return false;
	}
	
	public boolean removePlayer(Player player){
		if(playersList.contains(player)){
			for(int i = 0; i < playersList.size();i++ ){
				if(playersList.get(i) == player){
					playersList.remove(i);
					playersIdList.remove(i);
					playersNameList.remove(i);
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean validIDSet(String id){
		if(!playersIdList.contains(id)){
			return true;
		}
		return false;
	}
	//player------------------------------------------------------------------------*******************************
	//****----****----****----****----****----****----****----****
	//achievements***********************************************-------------------------------------------------
	public boolean addAchievements(Achievements c){
		/*if(!achievementsList.contains(c)){
			System.out.println(achievementsList.contains(c));
			achievementsList.add(c);
			return true;
		}return false;
		*/
		
		for(int i=0; i<achievementsList.size();i++){
			if(c.getID()==achievementsList.get(i).getID()){
				return false;
			}
		}
		achievementsList.add(c);
		return true;
		
	}
	
	public boolean removeAchievements(Achievements c){
		if(achievementsList.contains(c)){
			achievementsList.remove(c);
			return true;
		}return false;
	}
	
	
	public ArrayList<Achievements> getAchievementsList(){ return achievementsList;} 
	
	//scores***********************************************-------------------------------------------------
	public boolean addScores(Scores s){
		if(!scoresList.contains(s)){
			scoresList.add(s);
			return true;
		}else{
			for(int i=0; i<scoresList.size();i++){
				if(s.equals(scoresList.get(i))){
					if(s.getScore()> scoresList.get(i).getScore()){
						scoresList.get(i).setScore(s.getScore());
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean removeScores(Scores s){
		if(scoresList.contains(s)){
			scoresList.remove(s);
			return true;
		}return false;
	}
	
	public ArrayList<Scores> getScoreList(){ return scoresList;}
}
