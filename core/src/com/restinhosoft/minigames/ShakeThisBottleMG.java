package com.restinhosoft.minigames;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.restinho.exceptions.invalidGameNameException;

/**
 * @author Mailson
 *
 */
public class ShakeThisBottleMG implements MiniGamesIF{
		
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
	
	private final int defaultTime = 30;
	
	private Timer counterTimer;
	private TimerTask task;
	
	private final long second = 1000;
	
	//My miniGame Variables
	private int shake;
	private int minimumShakeMove;
	
	public ShakeThisBottleMG(int difficulty, int level, String language){
		this.id = 1;
		this.score = 0;
		this.bonus = 0;
		
		if(level >=1){
			this.level = level;
		}
		
		this.timer = defaultTime;
		this.difficulty = difficulty;
		
		
		if(language =="eng"){
			this.name = "SHAKE THIS BOTTLE";
			
			this.description = "In this minigame you have to shake the bottle to celebrate the new year,"
							+ " if the bottle not burst will be the end of the world,"
							+ " you have until the end of the count to save the world, good luck.";	
			
			this.language = language;
			this.congratsMessage = "Wow, you got it, Happy New Year.";
			this.gameOverMessage = "Too bad, it was not this time ... and died ...";
			
		}else if(language =="pt-br"){
			this.name = "Agite esta garrafa";

			this.description = "Neste minigame você tem que agitar a garrafa para celebrar o novo ano"
							 + "Se a garrafa não estourar será o fim do mundo,"
							 + "Você tem até o final da contagem para salvar o mundo, boa sorte.";
			this.language = language;
			this.congratsMessage = "Parabéns, você conseguiu, feliz ano novo.";
			this.gameOverMessage = "Que pena, não foi desta vez... E morreu...";
			
		}else{
			this.name = "SHAKE THIS BOTTLE";
			
			this.description = "In this minigame you have to shake the bottle to celebrate the new year,"
							+ " if the bottle not burst will be the end of the world,"
							+ " you have until the end of the count to save the world, good luck.";
			this.language = "eng";
			this.congratsMessage = "Wow, you got it, Happy New Year.";
			this.gameOverMessage = "Too bad, it was not this time ... and died ...";
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
			minimumShakeMove  = 30;
		}else if(difficulty == 2){
			minimumShakeMove  = 40;
		}else if(difficulty == 3){
			minimumShakeMove  = 45;
		}else{
			minimumShakeMove  = 30;//by default
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
			task.run();	//****************************GET IT
			if(timer > 0){
				if(this.shake == this.minimumShakeMove){
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
	
	public int getNumbersOfShake(){
		return this.shake;
	}




	
}
