package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import com.restinho.exceptions.invalidGameNameException;

/**
 * @author Mailson
 *
 */
public class MemorizeFastMG implements MiniGamesIF{
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
	private ArrayList<Integer> villainSequence;
	private ArrayList<Integer> playerSequence;
	
	//My private methods
	private void buildVillainSequence(){
		villainSequence.add(1);//red
		villainSequence.add(2);//green
		villainSequence.add(3);//yellow
		villainSequence.add(4);//blue
		
		Collections.shuffle(villainSequence);
	}
	
	
	public MemorizeFastMG(int difficulty, int level, String language){
		this.id = 1;
		this.score = 0;
		this.bonus = 0;
		
		if(level >=1){
			this.level = level;
		}
		
		this.timer = defaultTime;
		this.difficulty = difficulty;
		
		
		if(language =="eng"){
			this.name = "MEMORIZE FAST";
			
			this.description = "News !? the world is in danger and you once again have to save it."
							+ " This time a very ardilozo villain prepared a trap with missiles."
							+ " To stop him you have to record the color sequence given by him."
							+ " You have 10 seconds to set the color sequence if err it's game over.";

			this.language = language;
			
			this.congratsMessage = "Wow, you really have a great memory.";
			this.gameOverMessage = "Oh no, the missiles were shot ";
			
		}else if(language =="pt-br"){
			this.name = "MEMORIZE RÁPIDO";

			this.description = "Novidades !? o mundo está em perigo e mais uma vez tem que salvá-lo."
			+ "Desta vez um vilão muito ardilozo preparado uma armadilha com mísseis."
			+ "Para detê-lo você tem que gravar a sequência de cores dada por ele."
			+ "Você tem 10 segundos para definir a sequência de cores, se errar é game over.";

			this.language = language;
			
			this.congratsMessage = "Cara, você realmente tem uma boa memória.";
			this.gameOverMessage = "Oh não, os mísseis foram disparados";
			
		}else{
			this.name = "MEMORIZE FAST";
			
			this.description = "News !? the world is in danger and you once again have to save it."
							+ " This time a very ardilozo villain prepared a trap with missiles."
							+ " To stop him you have to record the color sequence given by him."
							+ " You have 10 seconds to set the color sequence if err it's game over.";

			this.language = language;
			
			this.congratsMessage = "Wow, you really have a great memory.";
			this.gameOverMessage = "Oh no, the missiles were shot ";
		}
		
		if(difficulty>=1 && difficulty<=3){
			this.difficulty = difficulty;
		}

		this.music = "none";
		this.soundTrack =  new ArrayList<String>();
		
		this.playing  = true;
		this.resume	  = true;
		
		this.gameOver = false;
		this.congrats = false;
		this.pause	  = false;
		
		//My miniGame variables
		villainSequence = new ArrayList<Integer>();
		playerSequence  = new ArrayList<Integer>();
		
		buildVillainSequence();
				
		if(difficulty == 1){
			this.defaultTime  = 10;
		}else if(difficulty == 2){
			this.defaultTime  = 8;
		}else if(difficulty == 3){
			this.defaultTime  = 6;
		}else{
			this.defaultTime  = 10;//by default
		}
		
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
			try {
				task.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}if(resume){
			task.run();	
			if(timer > 0){
				if(villainSequence.size()==playerSequence.size()){
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
	}

	@Override
	public void setResume() {
		this.pause  = false;
		this.resume = true;
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
	public boolean addPlayerSequence(int color){
		if(color<1||color>4){//is not a valid color 
			this.gameOver=true;
			this.congrats=false;
			return false;	
		}else if(villainSequence.get(playerSequence.size())!= color){// the sequences are different
			this.gameOver=true;
			this.congrats=false;
			return false;
		}else{
			playerSequence.add(color);
			return true;
		}
	}
	
}
