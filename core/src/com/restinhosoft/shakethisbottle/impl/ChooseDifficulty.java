package com.restinhosoft.shakethisbottle.impl;

public class ChooseDifficulty {
	private enum choosedDifficulty{
		NONE, EASY, NORMAL,HARD, INSANE;
	}
	
	private choosedDifficulty difficulty;
	
	public ChooseDifficulty(int difficulty){
		setDifficulty(difficulty);
	}
	
	public choosedDifficulty getDifficulty(){
		return this.difficulty;
	}
	
	public boolean setDifficulty(int difficulty){
		if(difficulty<=0){ 
			this.difficulty = choosedDifficulty.NONE;
			return true;
		}else if(difficulty == 1){ 
			this.difficulty = choosedDifficulty.EASY;
			return true;
		}else if(difficulty == 2){ 
			this.difficulty = choosedDifficulty.NORMAL;
			return true;
		}else if(difficulty == 3){ 
			this.difficulty = choosedDifficulty.HARD;
			return true;
		}else{
			this.difficulty = choosedDifficulty.INSANE;
			return true;
		}
	}
}
