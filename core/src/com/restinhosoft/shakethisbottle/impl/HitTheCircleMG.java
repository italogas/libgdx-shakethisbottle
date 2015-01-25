package com.restinhosoft.shakethisbottle.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import com.restinhosoft.shakethisbottle.exception.invalidGameNameException;

public class HitTheCircleMG  implements MiniGamesIF{

	private int id;
	private int score;
	private int bonus;
	private int level;//posicao no modo survival
	private int timer;
	private int difficulty;//The difficulty is 1 to easy; 2 to normal; and 3 to hard
	
	private String name;
	private String description;
	private String language;
	private String congratsMessage;
	private String gameOverMessage;
	private String music;
		
	private ArrayList<String> soundTrack;
	
	private boolean playing;
	private boolean congrats;
	private boolean gameOver;
	private boolean pause;
	private boolean resume;
	
	private int defaultTime;
	
	private Timer counterTimer;
	private TimerTask task;
	
	private final long second = 1000;
	
	//My miniGame Variables
	private ArrayList<Boolean> isQuare;
		
	//My private methods
	private void buildisQuareList(){
		for(int i = 0;i <= 11;i++) {
			if(i<=5){ isQuare.add(true); }
			else	{ isQuare.add(false);}
		}
		Collections.shuffle(isQuare);
	}
	
	
	public HitTheCircleMG(int difficulty, int level, String language){
		this.id = 1;
		this.score = 0;
		this.bonus = 0;
		
		if(level >=1){
			this.level = level;
		}
				
		if(difficulty>=1 && difficulty<=3){
			this.difficulty = difficulty;
		}
		
		if(difficulty == 1){
			this.defaultTime  = 10;
		}else if(difficulty == 2){
			this.defaultTime  = 8;
		}else if(difficulty == 3){
			this.defaultTime  = 6;
		}else{
			this.defaultTime  = 10;//by default
		}
		
		this.timer = defaultTime;
		
		if(language =="eng"){
			this.name = " HIT THE CIRCLE ";
			
			this.description = "A bomb was armed and to save the world you have to disarm it."
							+ " This is quite simple, you have to turn the circles into squares before time runs out."
							+ " In this minigame you need to hit the circles on the screen to turn them into squares."
							+ " When there are only squares in screen you win the minigame.";

			this.language = language;
				
			this.congratsMessage = "Wow, you did it, you are really fast";
			this.gameOverMessage = "Sorry but you are not fast enough";
			
		}else if(language =="pt-br"){
			this.name = "Acerte o circulo";

			this.description = "Uma bomba foi armada e para salvar o mundo é preciso desarmá-la."
			+ "Isso é muito simples, você tem que transformar os círculos em quadrados antes do tempo acabar."
			+ "Neste minigame você precisa acertar os círculos na tela para transformá-los em quadrados."
			+ "Quando restarem apenas quadrados na tela que você ganhar o minigame.";

			this.language = language;

			this.congratsMessage = "Uau, você fez isso, você é realmente rápido";
			this.gameOverMessage = "Desculpe, mas você não é rápido o suficiente";
			
		}else{
			this.name = " HIT THE CIRCLE ";
			
			this.description = "A bomb was armed and to save the world you have to disarm it."
							+ " This is quite simple, you have to turn the circles into squares before time runs out."
							+ " In this minigame you need to hit the circles on the screen to turn them into squares."
							+ " When there are only squares in screen you win the minigame.";

			this.language = language;
				
			this.congratsMessage = "Wow, you did it, you are really fast";
			this.gameOverMessage = "Sorry but you are not fast enough";
		}
		
		this.music = "none";
		this.soundTrack =  new ArrayList<String>();
		
		this.playing  = true;
		this.resume	  = true;
		
		this.gameOver = false;
		this.congrats = false;
		this.pause	  = false;
		
		//My miniGame variables
		this.isQuare = new ArrayList<Boolean>();
		
		buildisQuareList();
				
		
		
		counterTimer = new Timer();
		
		this.task = new TimerTask() {  
            public void run() {  
                try { 
                	if(timer > 0) timer--;
                 } catch (Exception e) {  
                      e.printStackTrace();  
                 }  
            }  
       };  
       
       counterTimer.scheduleAtFixedRate(task, second, second);
	}

	@Override
	public int getGameID() {
		return this.id;
	}

	@Override
	public int getGameScore() {
		return this.score;
	}

	@Override
	public int getGameBonus() {
		return this.bonus*this.level;
	}

	@Override
	public int getGamelevel() {
		return this.level;
	}

	@Override
	public int getGameTimer() {
		return this.timer;
	}

	@Override
	public int getGameDifficulty() {
		return this.difficulty;
	}

	@Override
	public String getGameName() {
		return this.name;
	}

	@Override
	public String getGameDescription() {
		return this.description;
	}

	@Override
	public String getGameMusic() {
		return this.music;
	}

	@Override
	public ArrayList<String> getGameSoundTrack() {
		return this.soundTrack;
	}

	@Override
	public void setGameID(int id) {
		if(id>0){
			this.id = id;
		}
	}

	@Override
	public void setGameScore(int score) {
		if(score>0){
			this.score = score;
		}
	}

	@Override
	public void setGameBonus(int bonus) {
		if(bonus>0){
			this.bonus = bonus;
		}
	}

	@Override
	public void setGameLevel(int level) {
		if(level>0){
			this.level = level;
		}
	}

	@Override
	public void setGameTimer(int timer) {
		if(timer > 5){
			this.timer = timer;
		}
	}

	@Override
	public void setGameDifficulty(int difficulty) {
		if(difficulty == 1 || difficulty == 2||difficulty == 3){
			this.difficulty = difficulty;
		}else{this.difficulty = 1;}
	}

	@Override
	public void setGameName(String name) throws Exception {
		if(name != null){
			this.name = name;	
		}else {
			throw new invalidGameNameException();  
		}
		
	}

	@Override
	public void setGameDescription(String description) throws Exception {
		if(description != null){
			this.description = description;	
		}else {
			throw new invalidGameNameException();  
		}		
	}

	@Override
	public void setGameMusic(String soundArchiveName) throws Exception {
		if(soundArchiveName != null){
			this.music = soundArchiveName;	
		}else {
			throw new invalidGameNameException();  
		}
		
	}

	@Override
	public boolean addToGameSoundTrack(String soundArchiveName) throws Exception {
		if(soundArchiveName != null){
			soundTrack.add(soundArchiveName);
			return true;
		}else {
			throw new invalidGameNameException();  
		}
	}

	@Override
	public boolean removeFromGameSoundTrack(String soundArchiveName) throws Exception {
		if(soundArchiveName != null){
			try{
				soundTrack.add(soundArchiveName);
				return true;	
			}catch (Exception e){
				throw e;
			}
		}else {
			throw new invalidGameNameException();  
		}
		
	}

	@Override
	public void theGame() {
		if(pause){
			/*try {
				task.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}if(resume){
			//task.run();	
			if(timer > 0){
				if(completeSequence()){
					this.gameOver = false;
					this.congrats = true;
				}
			}else{
				this.gameOver = true;
				this.congrats = false;
			}
		}
	}

	@Override
	public void resetTimer() {
		theGame();
		this.timer = defaultTime;
	}

	@Override
	public void timerCount() {
		counterTimer.scheduleAtFixedRate(task, second, second);
	}

	@Override
	public boolean playing() {
		return this.playing;
	}

	@Override
	public boolean congrats() {
		return this.congrats;
	}

	@Override
	public boolean gameOver() {
		return this.gameOver;
	}

	@Override
	public boolean gamePaused() {
		return this.pause;
	}

	@Override
	public boolean gameResume() {
		return this.resume;
	}
	
	@Override
	public void setPause() {
		this.pause  = true;
		this.resume = false;
		theGame();
	}

	@Override
	public void setResume() {
		this.pause  = false;
		this.resume = true;
		theGame();
	}


	@Override
	public String language() {
		return this.language;
	}
	
	@Override
	public String congratsMessage() {
		return congratsMessage;
	}

	@Override
	public String gameOverMessage() {
		return gameOverMessage;
	}
	 
	//My miniGame methods
	public boolean hittingCircles(int position){
		if(position>=0 && position<=11){
			if(!isQuare.get(position)){//not a square
				isQuare.set(position, true);
				theGame();
				return true;
			}
		}
		theGame();
		return false;
	}
	
	public boolean completeSequence(){
		for(int i=0; i < isQuare.size();i++){
			if(!isQuare.get(i)){
				return false;
			}
		}
		return true;
	}
	
}
