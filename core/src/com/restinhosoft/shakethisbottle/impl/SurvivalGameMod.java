package com.restinhosoft.shakethisbottle.impl;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Mailson
 *
 */
public class SurvivalGameMod {
	private ArrayList<Integer> miniGameIDList;
	
	private int difficulty, numberOfGames;
	
	public SurvivalGameMod(int difficulty, int numberOfGames){
		if(numberOfGames >1){
			this.numberOfGames = numberOfGames;
		}else{this.numberOfGames = 1;}
		if(difficulty>=1 && difficulty<=4){
			this.difficulty = difficulty;
		}else{this.difficulty = 2;}
		
		miniGameIDList = new ArrayList<Integer>();
		buildMiniGameList();
	}
	
	private void buildMiniGameList(){
		for(int i=0; i < numberOfGames;i++){
			miniGameIDList.add(i);
		}
		Collections.shuffle(miniGameIDList);
	}
	
	public ArrayList<Integer> getGameIDList(){ return miniGameIDList;}
}
