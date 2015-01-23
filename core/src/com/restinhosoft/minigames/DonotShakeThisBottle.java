package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.restinho.exceptions.invalidGameNameException;

/**
 * @author Mailson
 *
 */
public class DonotShakeThisBottle implements MiniGamesIF{
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
	private int shake;
	
	public DonotShakeThisBottle(int difficulty, int level, String language){
		this.id = 1;
		this.score = 0;
		this.bonus = 0;
		
		if(level >=1){
			this.level = level;
		}
		
		this.timer = defaultTime;
		this.difficulty = difficulty;
		
		
		if(language =="eng"){
			this.name = "DO NOT SHAKE THIS BOTTLE";
			
			this.description = "In this mini game you can not move the bottle,"
							 + " because it contains an unstable liquid that can explode."
							 + " To save the world, you should deicar your smartphone stable during the countdown,"
							 + " and if the move recklessly device, it's game over.";
			
			this.language = language;
			this.congratsMessage = "Wow, you did it, the world is safe, congrats.";
			this.gameOverMessage = "Boom ...";
			
		}else if(language =="pt-br"){
			this.name = "Não agite esta garrafa";

			this.description = "Neste mini jogo que você não pode mover a garrafa"
							 + ", Pois contém um líquido instável que pode explodir."
						     + "Para salvar o mundo, você deve deicar seu smartphone estável durante a contagem regressiva"
						     + "E se o movimento de forma imprudente dispositivo, é game over.";
			this.language = language;
			this.congratsMessage = "Uau, você conseguiu, salvou o mundo, parabéns.";
			this.gameOverMessage = "Kaboom...";
			
		}else{
			this.name = "DO NOT SHAKE THIS BOTTLE";
			
			this.description = "In this mini game you can not move the bottle,"
							 + " because it contains an unstable liquid that can explode."
							 + " To save the world, you should deicar your smartphone stable during the countdown,"
							 + " and if the move recklessly device, it's game over.";
			
			this.language = language;
			this.congratsMessage = "Wow, you did it, the world is safe, congrats.";
			this.gameOverMessage = "Boom ...";
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
		
		this.shake = 0;
		
		if(difficulty == 1){
			this.defaultTime  = 30;
		}else if(difficulty == 2){
			this.defaultTime  = 45;
		}else if(difficulty == 3){
			this.defaultTime  = 60;
		}else{
			this.defaultTime  = 30;//by default
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
				if(shake != 0){
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
	public void shake(){
		this.shake++;
	}
	
}
